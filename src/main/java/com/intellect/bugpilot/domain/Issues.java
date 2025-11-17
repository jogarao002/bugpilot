package com.intellect.bugpilot.domain;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;
import com.intellect.bugpilot.service.dto.IssueStatusEnum;
import com.intellect.bugpilot.service.dto.PriorityStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ISSUES")
public class Issues extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long issueId;

	@NotBlank
	private String issueName;

	@ManyToOne
	@JoinColumn(name = "project_id", referencedColumnName = "projectId")
	private Projects projects;

	@ManyToOne
	@JoinColumn(name = "module_id", referencedColumnName = "moduleId")
	private Modules modules;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	@JoinColumn(name = "raised_by", referencedColumnName = "userId")
	private Users raisedBy;

	@ManyToOne
	@JoinColumn(name = "assiged_to", referencedColumnName = "userId")
	private Users assignedTo;

	@ManyToOne
	@JoinColumn(name = "assiged_by", referencedColumnName = "userId")
	private Users assignedBy;

	@Enumerated(EnumType.STRING)
	private PriorityStatusEnum priority;

	@Enumerated(EnumType.STRING)
	private IssueStatusEnum status;

	public Issues() {
	}

	public Issues(IssuesBuilder builder) {
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

	public static class IssuesBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long issueId;

		@NotNull
		private String issueName;

		private Projects projects;

		private Modules modules;

		private String description;

		private Users raisedBy;

		private Users assignedTo;

		private Users assignedBy;

		private PriorityStatusEnum priority;

		private IssueStatusEnum status;

		public IssuesBuilder issueId(Long issueId) {
			this.issueId = issueId;
			return this;
		}

		public IssuesBuilder issueName(@NotBlank String issueName) {
			this.issueName = issueName;
			return this;
		}

		public IssuesBuilder projects(Projects projects) {
			this.projects = projects;
			return this;
		}

		public IssuesBuilder modules(Modules modules) {
			this.modules = modules;
			return this;
		}

		public IssuesBuilder description(String description) {
			this.description = description;
			return this;
		}

		public IssuesBuilder raisedBy(Users raisedBy) {
			this.raisedBy = raisedBy;
			return this;
		}

		public IssuesBuilder assignedTo(Users assignedTo) {
			this.assignedTo = assignedTo;
			return this;
		}

		public IssuesBuilder assignedBy(Users assignedBy) {
			this.assignedBy = assignedBy;
			return this;
		}

		public IssuesBuilder priority(PriorityStatusEnum priority) {
			this.priority = priority;
			return this;
		}

		public IssuesBuilder status(IssueStatusEnum status) {
			this.status = status;
			return this;
		}

		public Issues build() {
			return new Issues(this);
		}

	}

	public Long getIssueId() {
		return issueId;
	}

	public String getIssueName() {
		return issueName;
	}

	public Projects getProjects() {
		return projects;
	}

	public Modules getModules() {
		return modules;
	}

	public String getDescription() {
		return description;
	}

	public Users getRaisedBy() {
		return raisedBy;
	}

	public Users getAssignedTo() {
		return assignedTo;
	}

	public Users getAssignedBy() {
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
		return "Issues [issueId=" + issueId + ", issueName=" + issueName + ", projects=" + projects + ", modules="
				+ modules + ", description=" + description + ", raisedBy=" + raisedBy + ", assignedTo=" + assignedTo
				+ ", assignedBy=" + assignedBy + ", priority=" + priority + ", status=" + status + "]";
	}

}
