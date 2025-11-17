package com.intellect.bugpilot.service;

import java.util.List;

import com.intellect.bugpilot.service.dto.RolesDTO;

public interface RolesService {

	RolesDTO createRole(RolesDTO rolesDTO);

	RolesDTO findOne(Integer roleId);

	void delete(Integer roleId);

	List<RolesDTO> findAll();

}
