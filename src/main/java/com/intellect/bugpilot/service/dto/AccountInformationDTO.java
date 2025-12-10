package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

public class AccountInformationDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String userName;

	private String password;

	private Long userId;

	public AccountInformationDTO() {
	}

	private AccountInformationDTO(AccountInformationDTOBuilder builder) {
		this.id = builder.id;
		this.userName = builder.userName;
		this.password = builder.password;
		this.userId = builder.userId;
	}

	public static class AccountInformationDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;
		private Long id;
		private String userName;
		private String password;
		private Long userId;

		public AccountInformationDTOBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public AccountInformationDTOBuilder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public AccountInformationDTOBuilder password(String password) {
			this.password = password;
			return this;
		}

		public AccountInformationDTOBuilder userId(Long userId) {
			this.userId = userId;
			return this;
		}

		public AccountInformationDTO build() {
			return new AccountInformationDTO(this);
		}
	}

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "AccountInformationDTO [id=" + id + ", userName=" + userName + ", password=" + password + ", userId="
				+ userId + "]";
	}

}
