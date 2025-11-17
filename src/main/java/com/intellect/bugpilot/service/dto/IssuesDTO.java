package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class IssuesDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long issueId;

	@NotBlank
	private String issueName;

	private ProjectsDTO projects;

	private ModulesDTO modules;

	private String description;

	private UsersDTO raisedBy;

	private UsersDTO assignedTo;

	private UsersDTO assignedBy;

	private PriorityStatusEnum priority;

	private IssueStatusEnum status;

	public IssuesDTO() {
	}

	private IssuesDTO(IssuesDTOBuilder builder) {
		this.issueId = builder.issueId;
		this.issueName = builder.issueName;
		this.projects = builder.projects;
		this.modules = builder.modules;
		this.description = builder.description;
		this.raisedBy = builder.raisedBy;
		this.assignedTo = builder.assignedTo;
		this.assignedBy = builder.assignedBy;
		this.priority = builder.priority;
		this.status = builder.status;
	}

	public static class IssuesDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long issueId;

		@NotNull
		private String issueName;

		private ProjectsDTO projects;

		private ModulesDTO modules;

		private String description;

		private UsersDTO raisedBy;

		private UsersDTO assignedTo;

		private UsersDTO assignedBy;

		private PriorityStatusEnum priority;

		private IssueStatusEnum status;

		public IssuesDTOBuilder issueId(Long issueId) {
			this.issueId = issueId;
			return this;
		}

		public IssuesDTOBuilder issueName(@NotBlank String issueName) {
			this.issueName = issueName;
			return this;
		}

		public IssuesDTOBuilder projects(ProjectsDTO projects) {
			this.projects = projects;
			return this;
		}

		public IssuesDTOBuilder modules(ModulesDTO modules) {
			this.modules = modules;
			return this;
		}

		public IssuesDTOBuilder description(String description) {
			this.description = description;
			return this;
		}

		public IssuesDTOBuilder raisedBy(UsersDTO raisedBy) {
			this.raisedBy = raisedBy;
			return this;
		}

		public IssuesDTOBuilder assignedTo(UsersDTO assignedTo) {
			this.assignedTo = assignedTo;
			return this;
		}

		public IssuesDTOBuilder assignedBy(UsersDTO assignedBy) {
			this.assignedBy = assignedBy;
			return this;
		}

		public IssuesDTOBuilder priority(PriorityStatusEnum priority) {
			this.priority = priority;
			return this;
		}

		public IssuesDTOBuilder status(IssueStatusEnum status) {
			this.status = status;
			return this;
		}

		public IssuesDTO build() {
			return new IssuesDTO(this);
		}
	}

	public Long getIssueId() {
		return issueId;
	}

	public String getIssueName() {
		return issueName;
	}

	public ProjectsDTO getProjects() {
		return projects;
	}

	public ModulesDTO getModules() {
		return modules;
	}

	public String getDescription() {
		return description;
	}

	public UsersDTO getRaisedBy() {
		return raisedBy;
	}

	public UsersDTO getAssignedTo() {
		return assignedTo;
	}

	public UsersDTO getAssignedBy() {
		return assignedBy;
	}

	public PriorityStatusEnum getPriority() {
		return priority;
	}

	public IssueStatusEnum getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "IssuesDTO [issueId=" + issueId + ", issueName=" + issueName + ", projects=" + projects + ", modules="
				+ modules + ", description=" + description + ", raisedBy=" + raisedBy + ", assignedTo=" + assignedTo
				+ ", assignedBy=" + assignedBy + ", priority=" + priority + ", status=" + status + "]";
	}
}
