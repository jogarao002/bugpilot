package com.intellect.bugpilot.domain;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;
import com.intellect.bugpilot.service.dto.ProjectStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "PROJECTS")
public class Projects extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectId;

	@NotBlank
	@Column(unique = true)
	private String projectName;

	@Enumerated(EnumType.STRING)
	private ProjectStatusEnum status;

	public Projects() {
	}

	private Projects(ProjectsBuilder builder) {
		this.projectId = builder.projectId;
		this.projectName = builder.projectName;
		this.status = builder.status;
	}

	public static class ProjectsBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long projectId;

		@NotBlank
		private String projectName;

		private ProjectStatusEnum status;

		public ProjectsBuilder projectId(Long projectId) {
			this.projectId = projectId;
			return this;
		}

		public ProjectsBuilder projectName(@NotBlank String projectName) {
			this.projectName = projectName;
			return this;
		}

		public ProjectsBuilder status(ProjectStatusEnum status) {
			this.status = status;
			return this;
		}

		public Projects build() {
			return new Projects(this);
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
		return "Projects [projectId=" + projectId + ", projectName=" + projectName + ", status=" + status + "]";
	}
}
