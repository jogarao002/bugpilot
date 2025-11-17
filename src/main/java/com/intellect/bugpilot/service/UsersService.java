package com.intellect.bugpilot.service;

import java.util.List;

import com.intellect.bugpilot.service.dto.UsersRequestDTO;

public interface UsersService {

	UsersRequestDTO createUser(UsersRequestDTO usersRequestDTO);

	UsersRequestDTO findOne(Long userId);

	List<UsersRequestDTO> findAll();

	void delete(Long userId);

}
