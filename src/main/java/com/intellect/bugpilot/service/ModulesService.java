package com.intellect.bugpilot.service;

import java.util.List;

import com.intellect.bugpilot.service.dto.ModulesRequestDTO;

public interface ModulesService {

	ModulesRequestDTO createUser(ModulesRequestDTO modulesRequestDTO);

	ModulesRequestDTO findOne(Long moduleId);

	List<ModulesRequestDTO> findAll();

	void delete(Long moduleId);

}
