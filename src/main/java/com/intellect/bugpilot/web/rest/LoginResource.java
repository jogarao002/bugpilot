package com.intellect.bugpilot.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.bugpilot.service.LoginService;
import com.intellect.bugpilot.service.dto.AccountInformationDTO;
import com.intellect.bugpilot.service.dto.LoginRequestDTO;

@RestController
@RequestMapping("/login")
public class LoginResource {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/create_login")
	public ResponseEntity<AccountInformationDTO> createLogin(@RequestBody AccountInformationDTO accountInformationDTO){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(loginService.createLogin(accountInformationDTO));
	}

	@PostMapping("/check_login_status")
	public ResponseEntity<LoginRequestDTO> checkLoginStatus(@RequestBody LoginRequestDTO loginRequestDTO){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(loginService.checkLoginStatus(loginRequestDTO));
	}
}
