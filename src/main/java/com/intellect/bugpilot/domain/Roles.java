package com.intellect.bugpilot.domain;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;
import com.intellect.bugpilot.service.dto.RoleEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ROLES")
public class Roles extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private RoleEnum roleName;
	
	@NotNull
	private Boolean status;
	
	public Roles() {}

    private Roles(RolesBuilder builder) {
        this.roleId = builder.roleId;
        this.roleName = builder.roleName;
        this.status = builder.status;
    }
	
	public static class RolesBuilder extends Auditable implements Serializable {
		
        private static final long serialVersionUID = 1L;
		private Integer roleId;     
        private RoleEnum roleName;
        private Boolean status;

        public RolesBuilder roleId(Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public RolesBuilder roleName(RoleEnum roleName) {
            this.roleName = roleName;
            return this;
        }

        public RolesBuilder status(@NotNull Boolean status) {
            this.status = status;
            return this;
        }

        public Roles build() {
            return new Roles(this);
        }
    }

	public Integer getRoleId() {
		return roleId;
	}

	public RoleEnum getRoleName() {
		return roleName;
	}

	public Boolean getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Roles [roleId=" + roleId + ", roleName=" + roleName + ", status=" + status + "]";
	}
	
}
