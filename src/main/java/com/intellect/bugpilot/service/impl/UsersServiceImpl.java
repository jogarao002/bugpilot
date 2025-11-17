package com.intellect.bugpilot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.intellect.bugpilot.domain.Roles;
import com.intellect.bugpilot.domain.Users;
import com.intellect.bugpilot.exception.ResourceNotFoundException;
import com.intellect.bugpilot.repository.UsersRepository;
import com.intellect.bugpilot.service.UsersService;
import com.intellect.bugpilot.service.dto.RolesDTO;
import com.intellect.bugpilot.service.dto.UserStatusEnum;
import com.intellect.bugpilot.service.dto.UsersRequestDTO;


@Service
@Transactional
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private RolesServiceImpl rolesServiceImpl;
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UsersRequestDTO createUser(UsersRequestDTO usersRequestDTO) {
		RolesDTO rolesDTO = null;
		if(usersRequestDTO.getRoleId() != null) {
			rolesDTO = rolesServiceImpl.findOne(usersRequestDTO.getRoleId());
			
			if(rolesDTO != null) {
				Roles roles = new Roles.RolesBuilder()
										.roleId(rolesDTO.getRoleId())
										.build();
				Users users = new Users.UsersBuilder()
							.userId((usersRequestDTO.getUserId() != null) ? usersRequestDTO.getUserId() : null)
							.firstName(usersRequestDTO.getFirstName())
							.lastName(usersRequestDTO.getLastName())
							.gender(usersRequestDTO.getGender())
							.roles(roles)
							.status(usersRequestDTO.getStatus())
							.build();
				users = usersRepository.save(users);
				
				usersRequestDTO = new UsersRequestDTO.UsersRequestDTOBuilder()
						.userId(users.getUserId())
						.firstName(users.getFirstName())
						.lastName(users.getLastName())
						.gender(users.getGender())
						.roleId(roles.getRoleId())
						.status(users.getStatus())
						.build();
				
			}
				
		}
		return usersRequestDTO;
	}

	@Override
	public UsersRequestDTO findOne(Long userId) {
		Users user = usersRepository.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User Not Found with ID: " + userId));
	    
	    return new UsersRequestDTO.UsersRequestDTOBuilder()
	            .userId(user.getUserId())
	            .firstName(user.getFirstName())
	            .lastName(user.getLastName())
	            .gender(user.getGender())
	            .status(user.getStatus())
	            .roleId(user.getRoles() != null ? user.getRoles().getRoleId() : null)
	            .build();
	}

	@Override
	public List<UsersRequestDTO> findAll() {
		List<UsersRequestDTO> usersRequestDTOlist = new ArrayList<>();
		List<Users> usersList = usersRepository.findAll();
		if(!CollectionUtils.isEmpty(usersList)) {
			usersList.forEach(users -> {
				UsersRequestDTO usersRequestDTO = new UsersRequestDTO.UsersRequestDTOBuilder()
						.userId(users.getUserId())
						.firstName(users.getFirstName())
						.lastName(users.getLastName())
						.gender(users.getGender())
						.roleId(users.getRoles().getRoleId())
						.status(users.getStatus())
						.build();
				usersRequestDTOlist.add(usersRequestDTO);
			});
		}
		
		return usersRequestDTOlist;
	}

	@Override
	public void delete(Long userId) {
		UsersRequestDTO usersRequestDTO = findOne(userId);
		
		if(!ObjectUtils.isEmpty(usersRequestDTO)) {
			RolesDTO rolesDTO = rolesServiceImpl.findOne(usersRequestDTO.getRoleId());
			Roles roles = new Roles.RolesBuilder()
					.roleId(rolesDTO.getRoleId())
					.build();
			Users users = new Users.UsersBuilder()
					.userId(usersRequestDTO.getUserId())
					.firstName(usersRequestDTO.getFirstName())
					.lastName(usersRequestDTO.getLastName())
					.gender(usersRequestDTO.getGender())
					.roles(roles)
					.status(UserStatusEnum.TERMINATED)
					.build();
			usersRepository.save(users);
		}
		
	}

}
