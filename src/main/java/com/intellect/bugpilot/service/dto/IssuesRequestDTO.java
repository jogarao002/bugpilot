package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class IssuesRequestDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long issueId;

	@NotBlank
	private String issueName;

	@NotNull
	private Long projectId;

	@NotNull
	private Long moduleId;
	
	@NotBlank
	private String imageProof; // By using base64

	@NotNull
	private String description;

	@NotNull
	private Long raisedBy;

	
	private Long assignedTo;

	
	private Long assignedBy;

	private PriorityStatusEnum priority;

	private IssueStatusEnum status;

	public IssuesRequestDTO() {
	}

	private IssuesRequestDTO(IssuesRequestDTOBuilder builder) {
		this.issueId = builder.issueId;
		this.issueName = builder.issueName;
		this.projectId = builder.projectId;
		this.moduleId = builder.moduleId;
		this.description = builder.description;
		this.imageProof= builder.imageProof;
		this.raisedBy = builder.raisedBy;
		this.assignedTo = builder.assignedTo;
		this.assignedBy = builder.assignedBy;
		this.priority = builder.priority;
		this.status = builder.status;
	}

	public static class IssuesRequestDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long issueId;

		@NotBlank
		private String issueName;

		@NotNull
		private Long projectId;

		@NotNull
		private Long moduleId;

		@NotNull
		private String description;
		
		@NotBlank
		private String imageProof; 

		@NotNull
		private Long raisedBy;

		@NotNull
		private Long assignedTo;

		@NotNull
		private Long assignedBy;

		private PriorityStatusEnum priority;

		private IssueStatusEnum status;

		public IssuesRequestDTOBuilder issueId(Long issueId) {
			this.issueId = issueId;
			return this;
		}

		public IssuesRequestDTOBuilder issueName(@NotBlank String issueName) {
			this.issueName = issueName;
			return this;
		}

		public IssuesRequestDTOBuilder projectId(@NotNull Long projectId) {
			this.projectId = projectId;
			return this;
		}

		public IssuesRequestDTOBuilder moduleId(@NotNull Long moduleId) {
			this.moduleId = moduleId;
			return this;
		}

		public IssuesRequestDTOBuilder description(@NotNull String description) {
			this.description = description;
			return this;
		}
		
		public IssuesRequestDTOBuilder imageProof(@NotBlank String imageProof) {
			this.imageProof = imageProof;
			return this;
		}

		public IssuesRequestDTOBuilder raisedBy(@NotNull Long raisedBy) {
			this.raisedBy = raisedBy;
			return this;
		}

		public IssuesRequestDTOBuilder assignedTo(Long assignedTo) {
			this.assignedTo = assignedTo;
			return this;
		}

		public IssuesRequestDTOBuilder assignedBy(Long assignedBy) {
			this.assignedBy = assignedBy;
			return this;
		}

		public IssuesRequestDTOBuilder priority(PriorityStatusEnum priority) {
			this.priority = priority;
			return this;
		}

		public IssuesRequestDTOBuilder status(IssueStatusEnum status) {
			this.status = status;
			return this;
		}

		public IssuesRequestDTO build() {
			return new IssuesRequestDTO(this);
		}
	}

	public Long getIssueId() {
		return issueId;
	}

	public String getIssueName() {
		return issueName;
	}

	public Long getProjectId() {
		return projectId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public String getDescription() {
		return description;
	}

	public Long getRaisedBy() {
		return raisedBy;
	}

	public Long getAssignedTo() {
		return assignedTo;
	}

	public Long getAssignedBy() {
		return assignedBy;
	}

	public PriorityStatusEnum getPriority() {
		return priority;
	}

	public IssueStatusEnum getStatus() {
		return status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getImageProof() {
		return imageProof;
	}

	@Override
	public String toString() {
		return "IssuesRequestDTO [issueId=" + issueId + ", issueName=" + issueName + ", projectId=" + projectId
				+ ", moduleId=" + moduleId + ", imageProof=" + imageProof + ", description=" + description
				+ ", raisedBy=" + raisedBy + ", assignedTo=" + assignedTo + ", assignedBy=" + assignedBy + ", priority="
				+ priority + ", status=" + status + "]";
	}

}
