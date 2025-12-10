package com.intellect.bugpilot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.intellect.bugpilot.domain.Modules;
import com.intellect.bugpilot.domain.SubModules;
import com.intellect.bugpilot.exception.ResourceNotFoundException;
import com.intellect.bugpilot.repository.SubModulesRepository;
import com.intellect.bugpilot.service.SubModulesService;
import com.intellect.bugpilot.service.dto.ModuleAndSubModuleStatusEnum;
import com.intellect.bugpilot.service.dto.ModulesRequestDTO;
import com.intellect.bugpilot.service.dto.SubModulesRequestDTO;

@Service
@Transactional
public class SubModulesServiceImpl implements SubModulesService {
	
	@Autowired
	private ModulesServiceImpl modulesServiceImpl;
	
	@Autowired
	private SubModulesRepository subModulesRepository;

	@Override
	public SubModulesRequestDTO createsubModule(SubModulesRequestDTO subModulesRequestDTO) {
		ModulesRequestDTO modulesRequestDTO = modulesServiceImpl.findOne(subModulesRequestDTO.getModuleId());
		Modules modules = new Modules.ModulesBuilder()
										.moduleId(modulesRequestDTO.getModuleId())
										.build();
		
		SubModules subModules = new SubModules.SubModulesBuilder()
												.subModuleId((subModulesRequestDTO.getSubModuleId() != null) ? subModulesRequestDTO.getSubModuleId() : null)
												.subModuleName(subModulesRequestDTO.getSubModuleName())
												.modules(modules)
												.status(subModulesRequestDTO.getStatus())
												.build();
		subModules = subModulesRepository.save(subModules);
		
		return new SubModulesRequestDTO.SubModulesRequestDTOBuilder()
										.subModuleId(subModules.getSubModuleId())
										.subModuleName(subModules.getSubModuleName())
										.moduleId(subModules.getModules().getModuleId())
										.status(subModules.getStatus())
										.build();
		
	}

	@Override
	public SubModulesRequestDTO findOne(Long subModuleId) {
		SubModules subModules = subModulesRepository.findById(subModuleId)
				.orElseThrow(() -> new ResourceNotFoundException("Sub-Module Not Found with ID: " + subModuleId));
		return new SubModulesRequestDTO.SubModulesRequestDTOBuilder()
				.subModuleId(subModules.getSubModuleId())
				.subModuleName(subModules.getSubModuleName())
				.moduleId(subModules.getModules().getModuleId())
				.status(subModules.getStatus())
				.build();
	}

	@Override
	public List<SubModulesRequestDTO> findAll() {
		List<SubModulesRequestDTO> subModulesRequestDTOList = new ArrayList<>();
		List<SubModules> subModulesList = subModulesRepository.findAll();
		if(!CollectionUtils.isEmpty(subModulesList)) {
			subModulesList.forEach(subModules -> {
				ModulesRequestDTO modulesRequestDTO = modulesServiceImpl.findOne(subModules.getModules().getModuleId());
				SubModulesRequestDTO subModulesRequestDTO = new SubModulesRequestDTO.SubModulesRequestDTOBuilder()
																			.subModuleId(subModules.getSubModuleId())
																			.subModuleName(subModules.getSubModuleName())
																			.moduleId(subModules.getModules().getModuleId())
																			.moduleName(modulesRequestDTO.getModuleName())
																			.status(subModules.getStatus())
																			.build();
				subModulesRequestDTOList.add(subModulesRequestDTO);
			});
		}
		return subModulesRequestDTOList;
	}

	@Override
	public void delete(Long subModuleId) {
		SubModulesRequestDTO subModulesRequestDTO = findOne(subModuleId);
		Modules modules = new Modules.ModulesBuilder()
				.moduleId(subModulesRequestDTO.getModuleId())
				.build();
		SubModules subModules = new SubModules.SubModulesBuilder()
				.subModuleId(subModulesRequestDTO.getSubModuleId())
				.subModuleName(subModulesRequestDTO.getSubModuleName())
				.modules(modules)
				.status(ModuleAndSubModuleStatusEnum.INPROGRESS)
				.build();
		subModulesRepository.save(subModules);
	}

	@Override
	public Map<String, Long> getAllSubModulesByModuleId(Long moduleId) {
		Modules modules = new Modules.ModulesBuilder()
							.moduleId(moduleId)
							.build();
		return subModulesRepository
	            .findByModulesAndStatus(modules, ModuleAndSubModuleStatusEnum.COMPLETED)
	            .stream()
	            .collect(Collectors.toMap(
	                    SubModules::getSubModuleName,
	                    SubModules::getSubModuleId
	            ));
	}

}
