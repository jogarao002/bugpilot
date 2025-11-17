package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;
import com.intellect.bugpilot.domain.Modules;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubModulesDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long subModuleId;

	@NotBlank
	private String subModuleName;

	@NotNull
	private Modules modules;

	private ModuleAndSubModuleStatusEnum status;

	public SubModulesDTO() {
	}

	private SubModulesDTO(SubModulesDTOBuilder builder) {
		this.subModuleId = builder.subModuleId;
		this.subModuleName = builder.subModuleName;
		this.modules = builder.modules;
		this.status = builder.status;
	}

	public static class SubModulesDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long subModuleId;
		private String subModuleName;
		private Modules modules;
		private ModuleAndSubModuleStatusEnum status;

		public SubModulesDTOBuilder subModuleId(Long subModuleId) {
			this.subModuleId = subModuleId;
			return this;
		}

		public SubModulesDTOBuilder subModuleName(@NotBlank String subModuleName) {
			this.subModuleName = subModuleName;
			return this;
		}

		public SubModulesDTOBuilder modules(@NotNull Modules modules) {
			this.modules = modules;
			return this;
		}

		public SubModulesDTOBuilder status(ModuleAndSubModuleStatusEnum status) {
			this.status = status;
			return this;
		}

		public SubModulesDTO build() {
			return new SubModulesDTO(this);
		}
	}

	public Long getSubModuleId() {
		return subModuleId;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public Modules getModules() {
		return modules;
	}

	public ModuleAndSubModuleStatusEnum getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "SubModulesDTO [subModuleId=" + subModuleId + ", subModuleName=" + subModuleName + ", modules=" + modules
				+ ", status=" + status + "]";
	}
}
