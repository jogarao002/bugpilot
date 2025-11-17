package com.intellect.bugpilot.service;

import java.util.List;

import com.intellect.bugpilot.service.dto.ProjectsDTO;

public interface ProjectService {

	ProjectsDTO createUser(ProjectsDTO projectsDTO);

	ProjectsDTO findOne(Long projectId);

	List<ProjectsDTO> findAll();

	void delete(Long projectId);

}
