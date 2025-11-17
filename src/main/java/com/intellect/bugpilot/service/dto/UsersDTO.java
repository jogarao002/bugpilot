package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsersDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	private GenderEnum gender;

	@NotNull 
	private RolesDTO jobTitle;

	private UserStatusEnum status;

	public UsersDTO() {
	}

	private UsersDTO(UsersDTOBuilder builder) {
		this.userId = builder.userId;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.gender = builder.gender;
		this.jobTitle = builder.jobTitle;
		this.status = builder.status;
	}

	public static class UsersDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long userId;
		private String firstName;
		private String lastName;
		private GenderEnum gender;
		private RolesDTO jobTitle;
		private UserStatusEnum status;

		public UsersDTOBuilder userId(Long userId) {
			this.userId = userId;
			return this;
		}

		public UsersDTOBuilder firstName(@NotBlank String firstName) {
			this.firstName = firstName;
			return this;
		}

		public UsersDTOBuilder lastName(@NotBlank String lastName) {
			this.lastName = lastName;
			return this;
		}

		public UsersDTOBuilder gender(GenderEnum gender) {
			this.gender = gender;
			return this;
		}

		public UsersDTOBuilder jobTitle(@NotNull RolesDTO jobTitle) {
			this.jobTitle = jobTitle;
			return this;
		}

		public UsersDTOBuilder status(UserStatusEnum status) {
			this.status = status;
			return this;
		}

		public UsersDTO build() {
			return new UsersDTO(this);
		}
	}

	public Long getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public RolesDTO getJobTitle() {
		return jobTitle;
	}

	public UserStatusEnum getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "UsersDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", jobTitle=" + jobTitle + ", status=" + status + "]";
	}

}
