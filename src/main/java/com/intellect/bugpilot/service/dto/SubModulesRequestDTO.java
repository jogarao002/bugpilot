package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubModulesRequestDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long subModuleId;

	@NotBlank
	private String subModuleName;

	@NotNull
	private Long moduleId;
	
	private String moduleName;

	private ModuleAndSubModuleStatusEnum status;

	public SubModulesRequestDTO() {
	}

	private SubModulesRequestDTO(SubModulesRequestDTOBuilder builder) {
		this.subModuleId = builder.subModuleId;
		this.subModuleName = builder.subModuleName;
		this.moduleId = builder.moduleId;
		this.moduleName = builder.moduleName;
		this.status = builder.status;
	}

	public static class SubModulesRequestDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long subModuleId;
		private String subModuleName;
		private Long moduleId;
		private String moduleName;
		private ModuleAndSubModuleStatusEnum status;

		public SubModulesRequestDTOBuilder subModuleId(Long subModuleId) {
			this.subModuleId = subModuleId;
			return this;
		}

		public SubModulesRequestDTOBuilder subModuleName(@NotBlank String subModuleName) {
			this.subModuleName = subModuleName;
			return this;
		}

		public SubModulesRequestDTOBuilder moduleId(@NotNull Long moduleId) {
			this.moduleId = moduleId;
			return this;
		}
		
		public SubModulesRequestDTOBuilder moduleName(String moduleName) {
			this.moduleName = moduleName;
			return this;
		}

		public SubModulesRequestDTOBuilder status(ModuleAndSubModuleStatusEnum status) {
			this.status = status;
			return this;
		}

		public SubModulesRequestDTO build() {
			return new SubModulesRequestDTO(this);
		}
	}

	public Long getSubModuleId() {
		return subModuleId;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public ModuleAndSubModuleStatusEnum getStatus() {
		return status;
	}

	public String getModuleName() {
		return moduleName;
	}

	@Override
	public String toString() {
		return "SubModulesRequestDTO [subModuleId=" + subModuleId + ", subModuleName=" + subModuleName + ", moduleId="
				+ moduleId + ", moduleName=" + moduleName + ", status=" + status + "]";
	}
}
