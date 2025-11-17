package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ModulesRequestDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long moduleId;

	@NotBlank
	@Column(unique = true)
	private String moduleName;

	@NotNull
	private Long projectId;

	@NotNull
	private Boolean hasSubModule;

	private ModuleAndSubModuleStatusEnum status;

	public ModulesRequestDTO() {
	}

	private ModulesRequestDTO(ModulesRequestDTOBuilder builder) {
		this.moduleId = builder.moduleId;
		this.moduleName = builder.moduleName;
		this.projectId = builder.projectId;
		this.hasSubModule = builder.hasSubModule;
		this.status = builder.status;
	}

	public static class ModulesRequestDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long moduleId;
		private String moduleName;
		private Long projectId;
		private Boolean hasSubModule;
		private ModuleAndSubModuleStatusEnum status;

		public ModulesRequestDTOBuilder moduleId(Long moduleId) {
			this.moduleId = moduleId;
			return this;
		}

		public ModulesRequestDTOBuilder moduleName(@NotBlank String moduleName) {
			this.moduleName = moduleName;
			return this;
		}

		public ModulesRequestDTOBuilder projectId(@NotNull Long projectId) {
			this.projectId = projectId;
			return this;
		}

		public ModulesRequestDTOBuilder hasSubModule(@NotNull Boolean hasSubModule) {
			this.hasSubModule = hasSubModule;
			return this;
		}

		public ModulesRequestDTOBuilder status(ModuleAndSubModuleStatusEnum status) {
			this.status = status;
			return this;
		}

		public ModulesRequestDTO build() {
			return new ModulesRequestDTO(this);
		}
	}

	public Long getModuleId() {
		return moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public Long getProjectId() {
		return projectId;
	}

	public Boolean getHasSubModule() {
		return hasSubModule;
	}

	public ModuleAndSubModuleStatusEnum getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "ModulesRequestDTO [moduleId=" + moduleId + ", moduleName=" + moduleName + ", projectId=" + projectId
				+ ", hasSubModule=" + hasSubModule + ", status=" + status + "]";
	}
}
