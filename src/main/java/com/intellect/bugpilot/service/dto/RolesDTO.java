package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.validation.constraints.NotNull;

public class RolesDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer roleId;
	
	private RoleEnum roleName;
	
	private Boolean status;

	public RolesDTO() {}

    private RolesDTO(RolesDTOBuilder builder) {
        this.roleId = builder.roleId;
        this.roleName = builder.roleName;
        this.status = builder.status;
    }
	
	public static class RolesDTOBuilder extends Auditable implements Serializable {
		
        private static final long serialVersionUID = 1L;
		private Integer roleId;     
        private RoleEnum roleName;
        private Boolean status;

        public RolesDTOBuilder roleId(Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public RolesDTOBuilder roleName(RoleEnum roleName) {
            this.roleName = roleName;
            return this;
        }

        public RolesDTOBuilder status(@NotNull Boolean status) {
            this.status = status;
            return this;
        }

        public RolesDTO build() {
            return new RolesDTO(this);
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
		return "RolesDTO [roleId=" + roleId + ", roleName=" + roleName + ", status=" + status + "]";
	}

}
