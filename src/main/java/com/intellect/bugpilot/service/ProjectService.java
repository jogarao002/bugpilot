package com.intellect.bugpilot.service;

import java.util.List;
import java.util.Map;

import com.intellect.bugpilot.service.dto.ProjectsDTO;

public interface ProjectService {

	ProjectsDTO createProject(ProjectsDTO projectsDTO);

	ProjectsDTO findOne(Long projectId);

	List<ProjectsDTO> findAll();

	void delete(Long projectId);

	Map<String, Long> getAllActiveProjects();


}
