package com.intellect.bugpilot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.intellect.bugpilot.domain.Modules;
import com.intellect.bugpilot.domain.Projects;
import com.intellect.bugpilot.exception.ResourceNotFoundException;
import com.intellect.bugpilot.repository.ModulesRepository;
import com.intellect.bugpilot.service.ModulesService;
import com.intellect.bugpilot.service.dto.ModuleAndSubModuleStatusEnum;
import com.intellect.bugpilot.service.dto.ModulesRequestDTO;
import com.intellect.bugpilot.service.dto.ProjectsDTO;

@Service
@Transactional
public class ModulesServiceImpl implements ModulesService {
	
	@Autowired
	private ProjectsServiceImpl projectsServiceImpl;
	
	@Autowired
	private ModulesRepository modulesRepository;

	@Override
	public ModulesRequestDTO createUser(ModulesRequestDTO modulesRequestDTO) {
		
		ProjectsDTO projectsDTO = projectsServiceImpl.findOne(modulesRequestDTO.getProjectId());
		if(!ObjectUtils.isEmpty(projectsDTO)) {
			Projects projects = new Projects.ProjectsBuilder()
											.projectId(projectsDTO.getProjectId())
											.build();
			Modules modules = new Modules.ModulesBuilder()
					.moduleId((modulesRequestDTO.getModuleId() != null) ? modulesRequestDTO.getModuleId():null)
					.moduleName(modulesRequestDTO.getModuleName())
					.projects(projects)
					.hasSubModule(modulesRequestDTO.getHasSubModule())
					.status(modulesRequestDTO.getStatus())
					.build();
			modules = modulesRepository.save(modules);
			
			modulesRequestDTO = new ModulesRequestDTO.ModulesRequestDTOBuilder()
													.moduleId(modules.getModuleId())
													.moduleName(modules.getModuleName())
													.projectId(projects.getProjectId())
													.hasSubModule(modules.getHasSubModule())
													.status(modules.getStatus())
													.build();
		}
		
		return modulesRequestDTO;
	}

	@Override
	public ModulesRequestDTO findOne(Long moduleId) {
		Modules modules = modulesRepository.findById(moduleId).orElseThrow(() -> new ResourceNotFoundException("Module Not Found with ID: " + moduleId));
		return new ModulesRequestDTO.ModulesRequestDTOBuilder()
				.moduleId(modules.getModuleId())
				.moduleName(modules.getModuleName())
				.projectId(modules.getProjects().getProjectId())
				.hasSubModule(modules.getHasSubModule())
				.status(modules.getStatus())
				.build();
		
	}

	@Override
	public List<ModulesRequestDTO> findAll() {
		List<ModulesRequestDTO> modulesRequestDTOList = new ArrayList<>();
		List<Modules> modulesList = modulesRepository.findAll();
		if(!CollectionUtils.isEmpty(modulesList)) {
			modulesList.forEach(modules -> {
				ProjectsDTO projectsDTO = projectsServiceImpl.findOne(modules.getProjects().getProjectId());
				ModulesRequestDTO modulesRequestDTO = new ModulesRequestDTO.ModulesRequestDTOBuilder()
																		.moduleId(modules.getModuleId())
																		.moduleName(modules.getModuleName())
																		.projectId(modules.getProjects().getProjectId())
																		.projectName(projectsDTO.getProjectName())
																		.hasSubModule(modules.getHasSubModule())
																		.status(modules.getStatus())
																		.build();
				modulesRequestDTOList.add(modulesRequestDTO);
			});
		}
		return modulesRequestDTOList;
	}

	@Override
	public void delete(Long moduleId) {
		ModulesRequestDTO modulesRequestDTO = findOne(moduleId);
		Projects projects = new Projects.ProjectsBuilder()
				.projectId(modulesRequestDTO.getProjectId())
				.build();
		Modules modules = new Modules.ModulesBuilder()
				.moduleId(modulesRequestDTO.getModuleId())
				.moduleName(modulesRequestDTO.getModuleName())
				.projects(projects)
				.hasSubModule(modulesRequestDTO.getHasSubModule())
				.status(ModuleAndSubModuleStatusEnum.INPROGRESS)
				.build();
		modulesRepository.save(modules);
	}

	@Override
	public Map<String, Long> getAllModules() {
		return modulesRepository.findByHasSubModule(true).stream()
				.filter(module -> ModuleAndSubModuleStatusEnum.COMPLETED.equals(module.getStatus()))
				.collect(Collectors.toMap(Modules::getModuleName, Modules::getModuleId));
	}

	@Override
	public Map<String, Long> getAllModulesByProjectId(Long projectId) {
		Projects projects = new Projects.ProjectsBuilder()
									.projectId(projectId)
									.build();
		return modulesRepository.findByProjectsAndStatus(projects, ModuleAndSubModuleStatusEnum.COMPLETED).stream()
				.collect(Collectors.toMap(Modules::getModuleName, Modules::getModuleId));
	}

}
