package com.intellect.bugpilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.AccountInformation;
import com.intellect.bugpilot.domain.Users;

@Repository
public interface LoginRepository extends JpaRepository<AccountInformation, Long> {

	AccountInformation findByUserNameAndPassword(String userName, String password);

	AccountInformation findByUsers(Users user);

}
