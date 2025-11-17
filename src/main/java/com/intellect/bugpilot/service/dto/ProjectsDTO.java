package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.validation.constraints.NotBlank;

public class ProjectsDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long projectId;

	@NotBlank
	private String projectName;

	private ProjectStatusEnum status;

	public ProjectsDTO() {
	}

	private ProjectsDTO(ProjectsDTOBuilder builder) {
		this.projectId = builder.projectId;
		this.projectName = builder.projectName;
		this.status = builder.status;
	}

	public static class ProjectsDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long projectId;
		private String projectName;
		private ProjectStatusEnum status;

		public ProjectsDTOBuilder projectId(Long projectId) {
			this.projectId = projectId;
			return this;
		}

		public ProjectsDTOBuilder projectName(@NotBlank String projectName) {
			this.projectName = projectName;
			return this;
		}

		public ProjectsDTOBuilder status(ProjectStatusEnum status) {
			this.status = status;
			return this;
		}

		public ProjectsDTO build() {
			return new ProjectsDTO(this);
		}
	}

	public Long getProjectId() {
		return projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public ProjectStatusEnum getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "ProjectsDTO [projectId=" + projectId + ", projectName=" + projectName + ", status=" + status + "]";
	}
}
