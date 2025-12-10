package com.intellect.bugpilot.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.intellect.bugpilot.domain.AccountInformation;
import com.intellect.bugpilot.domain.Roles;
import com.intellect.bugpilot.domain.Users;
import com.intellect.bugpilot.repository.LoginRepository;
import com.intellect.bugpilot.repository.RolesRepository;
import com.intellect.bugpilot.repository.UsersRepository;
import com.intellect.bugpilot.service.dto.GenderEnum;
import com.intellect.bugpilot.service.dto.RoleEnum;
import com.intellect.bugpilot.service.dto.UserStatusEnum;

@Service
public class DataLoaderServiceImpl {

	@Autowired
	private RolesRepository rolesRepo;

	@Autowired
	private UsersRepository usersRepo;

	@Autowired
	private LoginRepository accountRepo;

	private List<String> readFileFromClasspath(String path) throws Exception {
		ClassPathResource resource = new ClassPathResource(path);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			return reader.lines().collect(Collectors.toList());
		}
	}

	public void loadRoles(String filePath) throws Exception {
		List<String> lines = readFileFromClasspath(filePath);

	    for (String line : lines) {
	        String[] parts = line.split("#");
	        Roles.RolesBuilder builder = new Roles.RolesBuilder();
	        String roleNameStr = null;

	        for (String part : parts) {
	            String[] kv = part.split(":");
	            switch (kv[0].trim()) {
	                case "roleName" -> roleNameStr = kv[1];
	                case "status" -> builder.status(Boolean.parseBoolean(kv[1]));
	            }
	        }

	        if (roleNameStr == null) continue;

	        RoleEnum roleEnum = RoleEnum.valueOf(roleNameStr);
	        Roles existingRole = rolesRepo.findByRoleName(roleEnum);
	        if (existingRole == null) {
	            builder.roleName(roleEnum);
	            rolesRepo.save(builder.build());
	        }
	    }
	}

	public void loadUsers(String filePath) throws Exception {
		List<String> lines = readFileFromClasspath(filePath);
		for (String line : lines) {
			String[] parts = line.split("#");
			Long roleId = null;
			Users.UsersBuilder builder = new Users.UsersBuilder();
			for (String part : parts) {
				String[] kv = part.split(":");
				switch (kv[0].trim()) {
				case "userId" -> roleId = Long.parseLong(kv[1]);
				case "firstName" -> builder.firstName(kv[1]);
				case "lastName" -> builder.lastName(kv[1]);
				case "gender" -> builder.gender(GenderEnum.valueOf(kv[1]));
				case "roleid" -> builder.roles(rolesRepo.findById(Long.parseLong(kv[1])).orElse(null));
				case "status" -> builder.status(UserStatusEnum.valueOf(kv[1]));
				}
			}
			
			Roles roles = rolesRepo.findByRoleId(roleId.intValue());
			Users existingUsers = usersRepo.findByRoles(roles);
			if(existingUsers == null) {
				usersRepo.save(builder.build());
			}
		}
	}

	public void loadAccounts(String filePath) throws Exception {
		List<String> lines = readFileFromClasspath(filePath);

	    for (String line : lines) {
	        String[] parts = line.split("#");
	        AccountInformation.AccountInformationBuilder builder = new AccountInformation.AccountInformationBuilder();
	        Long userId = null;

	        for (String part : parts) {
	            String[] kv = part.split(":");
	            switch (kv[0].trim()) {
	                case "id" -> userId = Long.valueOf(kv[1]);
	                case "userName" -> builder.userName(kv[1]);
	                case "password" -> builder.password(kv[1]);
	                case "userid" -> builder.users(usersRepo.findById(Long.parseLong(kv[1])).orElse(null));
	            }
	        }

	        if (userId == null) continue;

	        Users users = usersRepo.findById(userId).orElseThrow();
	        AccountInformation existingAccount = accountRepo.findByUsers(users);
	        if (existingAccount == null) {
	            accountRepo.save(builder.build());
	        }
	    }
	}
}
