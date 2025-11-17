package com.intellect.bugpilot.domain;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;
import com.intellect.bugpilot.service.dto.ModuleAndSubModuleStatusEnum;

import jakarta.persistence.Column;
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
@Table(name = "MODULES")
public class Modules extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long moduleId;

	@NotBlank
	@Column(unique = true)
	private String moduleName;

	@ManyToOne
	@JoinColumn(name = "project_id", referencedColumnName = "projectId")
	private Projects projects;

	@NotNull
	private Boolean hasSubModule;

	@Enumerated(EnumType.STRING)
	private ModuleAndSubModuleStatusEnum status;

	public Modules() {

	}

	private Modules(ModulesBuilder builder) {
		this.moduleId = builder.moduleId;
		this.moduleName = builder.moduleName;
		this.projects = builder.projects;
		this.hasSubModule = builder.hasSubModule;
		this.status = builder.status;
	}

	public static class ModulesBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long moduleId;

		@NotBlank
		private String moduleName;

		private Projects projects;

		@NotBlank
		private Boolean hasSubModule;

		private ModuleAndSubModuleStatusEnum status;

		public ModulesBuilder moduleId(Long moduleId) {
			this.moduleId = moduleId;
			return this;
		}

		public ModulesBuilder moduleName(@NotBlank String moduleName) {
			this.moduleName = moduleName;
			return this;
		}

		public ModulesBuilder projects(Projects projects) {
			this.projects = projects;
			return this;
		}

		public ModulesBuilder hasSubModule(@NotBlank Boolean hasSubModule) {
			this.hasSubModule = hasSubModule;
			return this;
		}

		public ModulesBuilder status(ModuleAndSubModuleStatusEnum status) {
			this.status = status;
			return this;
		}

		public Modules build() {
			return new Modules(this);
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
		return "Modules [moduleId=" + moduleId + ", moduleName=" + moduleName + ", projects=" + projects
				+ ", hasSubModule=" + hasSubModule + ", status=" + status + "]";
	}

}
