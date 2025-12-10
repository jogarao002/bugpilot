package com.intellect.bugpilot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.Roles;
import com.intellect.bugpilot.domain.Users;
import com.intellect.bugpilot.service.dto.UserStatusEnum;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	Users findByRoles(Roles roles);

	List<Users> findByRolesAndStatus(Roles roles, UserStatusEnum active);

}
