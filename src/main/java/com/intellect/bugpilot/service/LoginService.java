package com.intellect.bugpilot.service;

import com.intellect.bugpilot.service.dto.AccountInformationDTO;
import com.intellect.bugpilot.service.dto.LoginRequestDTO;

public interface LoginService {


	LoginRequestDTO checkLoginStatus(LoginRequestDTO loginRequestDTO);

	AccountInformationDTO createLogin(AccountInformationDTO accountInformationDTO);

}
