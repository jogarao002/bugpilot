package com.intellect.bugpilot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.intellect.bugpilot.domain.AccountInformation;
import com.intellect.bugpilot.domain.Users;
import com.intellect.bugpilot.exception.ResourceNotFoundException;
import com.intellect.bugpilot.repository.LoginRepository;
import com.intellect.bugpilot.repository.UsersRepository;
import com.intellect.bugpilot.service.LoginService;
import com.intellect.bugpilot.service.dto.AccountInformationDTO;
import com.intellect.bugpilot.service.dto.LoginRequestDTO;
import com.intellect.bugpilot.util.GenarateRandomPassword;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public LoginRequestDTO checkLoginStatus(LoginRequestDTO loginRequestDTO) {
		
		AccountInformation accountInformation = loginRepository.findByUserNameAndPassword(loginRequestDTO.getUserName(), loginRequestDTO.getPassword());
		if(!ObjectUtils.isEmpty(accountInformation)) {
			return new LoginRequestDTO.LoginRequestDTOBuilder()
					.userName(accountInformation.getUserName())
					.role(accountInformation.getUsers().getRoles().getRoleName().toString())
					.userId(accountInformation.getUsers().getUserId())
					.build();
		}
		throw new ResourceNotFoundException("User Not Found With User Name or Password");
	}

	@Override
	public AccountInformationDTO createLogin(AccountInformationDTO accountInformationDTO) {
		
		Users users = usersRepository.findById(accountInformationDTO.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("User Not Found with ID: " + accountInformationDTO.getUserId()));		
		
		AccountInformation accountInformation = new AccountInformation.AccountInformationBuilder()
//				.id(accountInformationDTO.getId() != null ? accountInformationDTO.getId() : null)
				.userName(accountInformationDTO.getUserName())
				.password(GenarateRandomPassword.generatePassword())
				.users(users)
				.build();
		
		accountInformation = loginRepository.save(accountInformation);
		
		return new AccountInformationDTO.AccountInformationDTOBuilder()
				.id(accountInformation.getId())
				.userName(accountInformationDTO.getUserName())
				.password(accountInformation.getPassword())
				.userId(accountInformation.getUsers().getUserId())
				.build();
	}

}
