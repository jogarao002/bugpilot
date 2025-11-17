package com.intellect.bugpilot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.intellect.bugpilot.domain.Roles;
import com.intellect.bugpilot.exception.ResourceNotFoundException;
import com.intellect.bugpilot.repository.RolesRepository;
import com.intellect.bugpilot.service.RolesService;
import com.intellect.bugpilot.service.dto.RolesDTO;

@Service
@Transactional
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private RolesRepository rolesRepository;

	@Override
	public RolesDTO createRole(RolesDTO rolesDTO) {
		Roles role = new Roles.RolesBuilder()
								.roleId((rolesDTO.getRoleId() != null) ? rolesDTO.getRoleId() : null)
								.roleName(rolesDTO.getRoleName())
								.status(rolesDTO.getStatus())
								.build();
		
		role = rolesRepository.save(role);

		return new RolesDTO.RolesDTOBuilder()
							.roleId(role.getRoleId())
							.roleName(role.getRoleName())
							.status(role.getStatus())
							.build();
		

	}

	@Override
	public RolesDTO findOne(Integer roleId) {
		Roles roles = rolesRepository.findById(roleId.longValue())
				.orElseThrow(() -> new ResourceNotFoundException("Record Not Found with ID: " + roleId));
		return new RolesDTO.RolesDTOBuilder()
										.roleId(roles.getRoleId())
										.roleName(roles.getRoleName())
										.status(roles.getStatus())
										.build();
	}

	@Override
	public void delete(Integer roleId) {
		RolesDTO rolesDTO = findOne(roleId);
		Roles role = new Roles.RolesBuilder()
								.roleId(rolesDTO.getRoleId())
								.roleName(rolesDTO.getRoleName())
								.status(false)
								.build();

		role = rolesRepository.save(role);
		
	}

	@Override
	public List<RolesDTO> findAll() {
		List<RolesDTO> rolesDTOList = new ArrayList<RolesDTO>();
		List<Roles> rolesList = rolesRepository.findAll();
		if(!CollectionUtils.isEmpty(rolesList)) {
			rolesList.forEach(roles -> {
				RolesDTO rolesDTO = new RolesDTO.RolesDTOBuilder()
												.roleId(roles.getRoleId())
												.roleName(roles.getRoleName())
												.status(roles.getStatus())
												.build();
				rolesDTOList.add(rolesDTO);
			});
		}
		return rolesDTOList;
	}

}
