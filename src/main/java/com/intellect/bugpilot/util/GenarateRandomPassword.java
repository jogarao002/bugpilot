package com.intellect.bugpilot.util;

import org.apache.commons.lang3.RandomStringUtils;

public class GenarateRandomPassword {
	
	
	public static String generatePassword() {
		return RandomStringUtils.secureStrong().next(6, true, true);
	}

}
