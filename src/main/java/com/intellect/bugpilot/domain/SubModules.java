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

@Entity
@Table(name = "SUB_MODULES")
public class SubModules extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subModuleId;

	@NotBlank
	@Column(unique = true)
	private String subModuleName;

	@ManyToOne
	@JoinColumn(name = "module_id", referencedColumnName = "moduleId")
	private Modules modules;

	@Enumerated(EnumType.STRING)
	private ModuleAndSubModuleStatusEnum status;

	public SubModules() {
	}

	private SubModules(SubModulesBuilder builder) {
		this.subModuleId = builder.subModuleId;
		this.subModuleName = builder.subModuleName;
		this.modules = builder.modules;
		this.status = builder.status;
	}

	public static class SubModulesBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long subModuleId;

		@NotBlank
		private String subModuleName;

		private Modules modules;

		private ModuleAndSubModuleStatusEnum status;

		public SubModulesBuilder subModuleId(Long subModuleId) {
			this.subModuleId = subModuleId;
			return this;
		}

		public SubModulesBuilder subModuleName(@NotBlank String subModuleName) {
			this.subModuleName = subModuleName;
			return this;
		}

		public SubModulesBuilder modules(Modules modules) {
			this.modules = modules;
			return this;
		}

		public SubModulesBuilder status(ModuleAndSubModuleStatusEnum status) {
			this.status = status;
			return this;
		}

		public SubModules build() {
			return new SubModules(this);
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
		return "SubModules [subModuleId=" + subModuleId + ", subModuleName=" + subModuleName + ", modules=" + modules
				+ ", status=" + status + "]";
	}

}
