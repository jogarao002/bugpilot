package com.intellect.bugpilot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.intellect.bugpilot.service.impl.DataLoaderServiceImpl;

@Component
public class StartupDataLoader implements CommandLineRunner {

	private final DataLoaderServiceImpl service;

	public StartupDataLoader(DataLoaderServiceImpl service) {
		this.service = service;
	}

	@Override
	public void run(String... args) throws Exception {

		// Default file paths
		String roleFile = "static/role.txt";
		String userFile = "static/user.txt";
		String accountFile = "static/account_information.txt";

		// Load data
		service.loadRoles(roleFile);
		service.loadUsers(userFile);
		service.loadAccounts(accountFile);

		System.out.println("Data loaded successfully!");
	}

}
