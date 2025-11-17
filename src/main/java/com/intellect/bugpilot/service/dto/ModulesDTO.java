package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;
import com.intellect.bugpilot.domain.Projects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ModulesDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long moduleId;

	@NotBlank
	private String moduleName;

	@NotNull
	private Projects projects;

	@NotNull
	private Boolean hasSubModule;

	private ModuleAndSubModuleStatusEnum status;

	public ModulesDTO() {
	}

	private ModulesDTO(ModulesDTOBuilder builder) {
		this.moduleId = builder.moduleId;
		this.moduleName = builder.moduleName;
		this.projects = builder.projects;
		this.hasSubModule = builder.hasSubModule;
		this.status = builder.status;
	}

	public static class ModulesDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long moduleId;
		private String moduleName;
		private Projects projects;
		private Boolean hasSubModule;
		private ModuleAndSubModuleStatusEnum status;

		public ModulesDTOBuilder moduleId(Long moduleId) {
			this.moduleId = moduleId;
			return this;
		}

		public ModulesDTOBuilder moduleName(@NotBlank String moduleName) {
			this.moduleName = moduleName;
			return this;
		}

		public ModulesDTOBuilder projects(@NotNull Projects projects) {
			this.projects = projects;
			return this;
		}

		public ModulesDTOBuilder hasSubModule(@NotNull Boolean hasSubModule) {
			this.hasSubModule = hasSubModule;
			return this;
		}

		public ModulesDTOBuilder status(ModuleAndSubModuleStatusEnum status) {
			this.status = status;
			return this;
		}

		public ModulesDTO build() {
			return new ModulesDTO(this);
		}
	}

	public Long getModuleId() {
		return moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public Projects getProjects() {
		return projects;
	}

	public Boolean getHasSubModule() {
		return hasSubModule;
	}

	public ModuleAndSubModuleStatusEnum getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "ModulesDTO [moduleId=" + moduleId + ", moduleName=" + moduleName + ", projects=" + projects
				+ ", hasSubModule=" + hasSubModule + ", status=" + status + "]";
	}

}
