package com.intellect.bugpilot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.intellect.bugpilot.domain.Projects;
import com.intellect.bugpilot.exception.ResourceNotFoundException;
import com.intellect.bugpilot.repository.ProjectsRepository;
import com.intellect.bugpilot.service.ProjectService;
import com.intellect.bugpilot.service.dto.ProjectStatusEnum;
import com.intellect.bugpilot.service.dto.ProjectsDTO;

@Service
@Transactional
public class ProjectsServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectsRepository projectsRepository;

	@Override
	public ProjectsDTO createProject(ProjectsDTO projectsDTO) {
		
		Projects projects = new Projects.ProjectsBuilder()
					.projectId((projectsDTO.getProjectId() != null) ? projectsDTO.getProjectId() : null)
					.projectName(projectsDTO.getProjectName())
					.status(projectsDTO.getStatus())
					.build();
		
		projects = projectsRepository.save(projects);
		
		return new ProjectsDTO.ProjectsDTOBuilder()
						.projectId(projects.getProjectId())
						.projectName(projects.getProjectName())
						.status(projects.getStatus())
						.build();
	}

	@Override
	public ProjectsDTO findOne(Long projectId) {
		Projects projects = projectsRepository.findById(projectId.longValue())
				.orElseThrow(() -> new ResourceNotFoundException("Record Not Found with ID: " + projectId));
		return new ProjectsDTO.ProjectsDTOBuilder()
				.projectId(projects.getProjectId())
				.projectName(projects.getProjectName())
				.status(projects.getStatus())
				.build();
	}

	@Override
	public List<ProjectsDTO> findAll() {
		List<ProjectsDTO> projectsDTOList = new ArrayList<>();
		List<Projects> projectsList = projectsRepository.findAll();
		if(!CollectionUtils.isEmpty(projectsList)) {
			projectsList.forEach(projects -> {
				ProjectsDTO projectsDTO = new ProjectsDTO.ProjectsDTOBuilder()
														.projectId(projects.getProjectId())
														.projectName(projects.getProjectName())
														.status(projects.getStatus())
														.build();
				projectsDTOList.add(projectsDTO);
			});
		}
		return projectsDTOList;
	}

	@Override
	public void delete(Long projectId) {
		ProjectsDTO projectsDTO = findOne(projectId);
		Projects projects = new Projects.ProjectsBuilder()
				.projectId(projectsDTO.getProjectId())
				.projectName(projectsDTO.getProjectName())
				.status(ProjectStatusEnum.ONHOLD)
				.build();
		projectsRepository.save(projects);
	}

	@Override
	public Map<String, Long> getAllActiveProjects() {
		return projectsRepository.findAll().stream()
				.filter(project -> ProjectStatusEnum.COMPLETED.equals(project.getStatus()))
				.collect(Collectors.toMap(Projects::getProjectName, Projects::getProjectId));
	}

}
