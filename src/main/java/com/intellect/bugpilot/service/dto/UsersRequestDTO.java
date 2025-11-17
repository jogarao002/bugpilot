package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsersRequestDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userId;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	private GenderEnum gender;

	@NotNull
	private Integer roleId;

	private UserStatusEnum status;
	
	public UsersRequestDTO() {
	}

	private UsersRequestDTO(UsersRequestDTOBuilder builder) {
		this.userId = builder.userId;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.gender = builder.gender;
		this.roleId = builder.roleId;
		this.status = builder.status;
	}

	public static class UsersRequestDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long userId;
		private String firstName;
		private String lastName;
		private GenderEnum gender;
		private Integer roleId;
		private UserStatusEnum status;

		public UsersRequestDTOBuilder userId(Long userId) {
			this.userId = userId;
			return this;
		}

		public UsersRequestDTOBuilder firstName(@NotBlank String firstName) {
			this.firstName = firstName;
			return this;
		}

		public UsersRequestDTOBuilder lastName(@NotBlank String lastName) {
			this.lastName = lastName;
			return this;
		}

		public UsersRequestDTOBuilder gender(GenderEnum gender) {
			this.gender = gender;
			return this;
		}

		public UsersRequestDTOBuilder roleId(@NotNull Integer roleId) {
			this.roleId = roleId;
			return this;
		}

		public UsersRequestDTOBuilder status(UserStatusEnum status) {
			this.status = status;
			return this;
		}

		public UsersRequestDTO build() {
			return new UsersRequestDTO(this);
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

	public Integer getRoleId() {
		return roleId;
	}

	public UserStatusEnum getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "UsersDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", roleId=" + roleId + ", status=" + status + "]";
	}

}
