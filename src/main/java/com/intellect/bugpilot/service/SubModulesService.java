package com.intellect.bugpilot.service;

import java.util.List;

import com.intellect.bugpilot.service.dto.SubModulesRequestDTO;

public interface SubModulesService {

	SubModulesRequestDTO createsubModule(SubModulesRequestDTO subModulesRequestDTO);

	SubModulesRequestDTO findOne(Long subModuleId);

	List<SubModulesRequestDTO> findAll();

	void delete(Long subModuleId);

}
