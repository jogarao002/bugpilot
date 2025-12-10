package com.intellect.bugpilot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.Roles;
import com.intellect.bugpilot.service.dto.RoleEnum;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

	Roles findByRoleName(RoleEnum roleEnum);

	Roles findByRoleId(Integer intValue);

	List<Roles> findByStatus(Boolean status);
	
	List<Roles> findByStatusAndRoleNameNot(Boolean status, RoleEnum roleName);

	Roles findByRoleNameAndStatus(RoleEnum teamLead, Boolean status);


}
