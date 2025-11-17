package com.intellect.bugpilot.domain;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;
import com.intellect.bugpilot.service.dto.GenderEnum;
import com.intellect.bugpilot.service.dto.UserStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="USERS")
public class Users extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	
	@ManyToOne
    @JoinColumn(name="role_id", referencedColumnName = "roleId")
	private Roles roles;
	
	@Enumerated(EnumType.STRING)
	private UserStatusEnum status;
	
    public Users() {}
	
	private Users(UsersBuilder builder) {
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.gender = builder.gender;
        this.roles = builder.roles;
        this.status = builder.status;
    }

    public static class UsersBuilder extends Auditable implements Serializable {

    	private static final long serialVersionUID = 1L;
        
		private Long userId;
        private String firstName;
        private String lastName;
        private GenderEnum gender;
        private Roles roles;
        private UserStatusEnum status;

        public UsersBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UsersBuilder firstName(@NotBlank String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UsersBuilder lastName(@NotBlank String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UsersBuilder gender(GenderEnum gender) {
            this.gender = gender;
            return this;
        }

        public UsersBuilder roles(@NotNull Roles roles) {
            this.roles = roles;
            return this;
        }

        public UsersBuilder status(UserStatusEnum status) {
            this.status = status;
            return this;
        }
        

        public Users build() {
            return new Users(this);
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

	public Roles getRoles() {
		return roles;
	}

	public UserStatusEnum getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", roles=" + roles + ", status=" + status + "]";
	}
    

}
