package com.intellect.bugpilot.service;

import java.util.List;
import java.util.Map;

import com.intellect.bugpilot.service.dto.UsersRequestDTO;

public interface UsersService {

	UsersRequestDTO createUser(UsersRequestDTO usersRequestDTO);

	UsersRequestDTO findOne(Long userId);

	List<UsersRequestDTO> findAll();

	void delete(Long userId);

	Map<String, Long> getAllActiveUsers();

	Map<String, Long> getAllActiveTeamLeadUsers();

}
