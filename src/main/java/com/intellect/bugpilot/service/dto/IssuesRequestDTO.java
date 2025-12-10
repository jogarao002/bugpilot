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
	
	private Long subModuleId;
	
	@NotBlank
	private String imageProof; // By using base64

	@NotNull
	private String description;

	@NotNull
	private Long raisedBy;
	
	@NotNull
	private Long raisedTo;

	
	private Long assignedTo;

	
	private Long assignedBy;

	private PriorityStatusEnum priority;

	private IssueStatusEnum status;
	
	private String projectName;
	
	private String moduleName;
	
	private String subModuleName;
	
	private String raisedByName;
	
	private String assignedToName;
	
	private String assignedByName;
	
	private String comment;
	
	private Long userId;

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
		this.raisedTo = builder.raisedTo;
		this.subModuleId= builder.subModuleId;
		this.assignedTo = builder.assignedTo;
		this.assignedBy = builder.assignedBy;
		this.priority = builder.priority;
		this.status = builder.status;
		this.projectName =builder.projectName;
		this.moduleName = builder.moduleName;
		this.subModuleName= builder.subModuleName;
		this.raisedByName= builder.raisedByName;
		this.assignedToName = builder.assignedToName;
		this.assignedByName= builder.assignedByName;
		this.comment = builder.comment;
		this.userId = builder.userId;
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
		
		private Long subModuleId;

		@NotNull
		private String description;
		
		@NotBlank
		private String imageProof; 

		@NotNull
		private Long raisedBy;
		
		@NotNull
		private Long raisedTo;

		@NotNull
		private Long assignedTo;

		@NotNull
		private Long assignedBy;

		private PriorityStatusEnum priority;

		private IssueStatusEnum status;
		
		private String projectName;
		
		private String moduleName;
		
		private String subModuleName;
		
		private String raisedByName;
		
		private String assignedToName;
		
		private String assignedByName;
		
		@NotBlank
		private String comment;
		
		@NotNull
		private Long userId;

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
		
		public IssuesRequestDTOBuilder subModuleId(Long subModuleId) {
			this.subModuleId =subModuleId;
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
		
		public IssuesRequestDTOBuilder raisedTo(@NotNull Long raisedTo) {
			this.raisedTo = raisedTo;
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
		
		public IssuesRequestDTOBuilder projectName(String projectName) {
		    this.projectName = projectName;
		    return this;
		}

		public IssuesRequestDTOBuilder moduleName(String moduleName) {
		    this.moduleName = moduleName;
		    return this;
		}

		public IssuesRequestDTOBuilder subModuleName(String subModuleName) {
		    this.subModuleName = subModuleName;
		    return this;
		}

		public IssuesRequestDTOBuilder raisedByName(String raisedByName) {
		    this.raisedByName = raisedByName;
		    return this;
		}

		public IssuesRequestDTOBuilder assignedToName(String assignedToName) {
		    this.assignedToName = assignedToName;
		    return this;
		}

		public IssuesRequestDTOBuilder assignedByName(String assignedByName) {
		    this.assignedByName = assignedByName;
		    return this;
		}
		
		public IssuesRequestDTOBuilder comment(String comment) {
		    this.comment = comment;
		    return this;
		}
		
		public IssuesRequestDTOBuilder userId(Long userId) {
		    this.userId = userId;
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

	public Long getSubModuleId() {
		return subModuleId;
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

	public String getImageProof() {
		return imageProof;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public String getRaisedByName() {
		return raisedByName;
	}

	public String getAssignedToName() {
		return assignedToName;
	}

	public String getAssignedByName() {
		return assignedByName;
	}

	public Long getRaisedTo() {
		return raisedTo;
	}

	public String getComment() {
		return comment;
	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "IssuesRequestDTO [issueId=" + issueId + ", issueName=" + issueName + ", projectId=" + projectId
				+ ", moduleId=" + moduleId + ", subModuleId=" + subModuleId + ", imageProof=" + imageProof
				+ ", description=" + description + ", raisedBy=" + raisedBy + ", raisedTo=" + raisedTo + ", assignedTo="
				+ assignedTo + ", assignedBy=" + assignedBy + ", priority=" + priority + ", status=" + status
				+ ", projectName=" + projectName + ", moduleName=" + moduleName + ", subModuleName=" + subModuleName
				+ ", raisedByName=" + raisedByName + ", assignedToName=" + assignedToName + ", assignedByName="
				+ assignedByName + ", comment=" + comment + ", userId=" + userId + "]";
	}
	
}
