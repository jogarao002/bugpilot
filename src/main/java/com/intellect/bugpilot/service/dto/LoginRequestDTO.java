package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

public class LoginRequestDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private String password;
	
	private String role;
	
	private Long userId;
	
	public LoginRequestDTO() {}

    private LoginRequestDTO(LoginRequestDTOBuilder builder) {
        this.userName = builder.userName;
        this.password = builder.password;
        this.role = builder.role;
        this.userId = builder.userId;
    }

    public static class LoginRequestDTOBuilder extends Auditable implements Serializable {

        private static final long serialVersionUID = 1L;

        private String userName;
        private String password;
        private String role;
        private Long userId;

        public LoginRequestDTOBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public LoginRequestDTOBuilder password(String password) {
            this.password = password;
            return this;
        }

        public LoginRequestDTOBuilder role(String role) {
            this.role = role;
            return this;
        }
        public LoginRequestDTOBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public LoginRequestDTO build() {
            return new LoginRequestDTO(this);
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "LoginRequestDTO [userName=" + userName + ", password=" + password + ", role=" + role + ", userId="
				+ userId + "]";
	}
}
