package com.intellect.bugpilot.domain;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ACCOUNT_INFORMATION")
public class AccountInformation extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String password;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
	private Users users;
	
	
	public AccountInformation() {}

    private AccountInformation(AccountInformationBuilder builder) {
        this.id = builder.id;
        this.userName = builder.userName;
        this.password = builder.password;
        this.users = builder.users;
    }

    public static class AccountInformationBuilder extends Auditable implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private String userName;
        private String password;
        private Users users;

        public AccountInformationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AccountInformationBuilder userName(@NotBlank String userName) {
            this.userName = userName;
            return this;
        }

        public AccountInformationBuilder password(@NotBlank String password) {
            this.password = password;
            return this;
        }

        public AccountInformationBuilder users(Users users) {
            this.users = users;
            return this;
        }

        public AccountInformation build() {
            return new AccountInformation(this);
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

    public Users getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "AccountInformation [id=" + id + ", userName=" + userName +
               ", password=" + password + ", users=" + users + "]";
    }
	
	

}
