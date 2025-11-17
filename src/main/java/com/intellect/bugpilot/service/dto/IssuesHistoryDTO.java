package com.intellect.bugpilot.service.dto;

import java.io.Serializable;
import java.util.Date;

import com.intellect.bugpilot.audit.Auditable;

public class IssuesHistoryDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long historyId;

	private IssuesDTO issues;

	private UsersDTO users;

	private IssueStatusEnum oldStatus;

	private IssueStatusEnum newStatus;

	private String remarks;

	private Date changedAt;

	public IssuesHistoryDTO() {
	}

	private IssuesHistoryDTO(IssuesHistoryDTOBuilder builder) {
		this.historyId = builder.historyId;
		this.issues = builder.issues;
		this.users = builder.users;
		this.oldStatus = builder.oldStatus;
		this.newStatus = builder.newStatus;
		this.remarks = builder.remarks;
		this.changedAt = builder.changedAt;
	}

	public static class IssuesHistoryDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long historyId;

		private IssuesDTO issues;

		private UsersDTO users;

		private IssueStatusEnum oldStatus;

		private IssueStatusEnum newStatus;

		private String remarks;

		private Date changedAt;

		public IssuesHistoryDTOBuilder historyId(Long historyId) {
			this.historyId = historyId;
			return this;
		}

		public IssuesHistoryDTOBuilder issues(IssuesDTO issues) {
			this.issues = issues;
			return this;
		}

		public IssuesHistoryDTOBuilder users(UsersDTO users) {
			this.users = users;
			return this;
		}

		public IssuesHistoryDTOBuilder oldStatus(IssueStatusEnum oldStatus) {
			this.oldStatus = oldStatus;
			return this;
		}

		public IssuesHistoryDTOBuilder newStatus(IssueStatusEnum newStatus) {
			this.newStatus = newStatus;
			return this;
		}

		public IssuesHistoryDTOBuilder remarks(String remarks) {
			this.remarks = remarks;
			return this;
		}

		public IssuesHistoryDTOBuilder changedAt(Date changedAt) {
			this.changedAt = changedAt;
			return this;
		}

		public IssuesHistoryDTO build() {
			return new IssuesHistoryDTO(this);
		}

	}

	public Long getHistoryId() {
		return historyId;
	}

	public IssuesDTO getIssues() {
		return issues;
	}

	public UsersDTO getUsers() {
		return users;
	}

	public IssueStatusEnum getOldStatus() {
		return oldStatus;
	}

	public IssueStatusEnum getNewStatus() {
		return newStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public Date getChangedAt() {
		return changedAt;
	}

	@Override
	public String toString() {
		return "IssuesHistoryDTO [historyId=" + historyId + ", users=" + users + ", oldStatus=" + oldStatus
				+ ", newStatus=" + newStatus + ", remarks=" + remarks + ", changedAt=" + changedAt + "]";
	}

	
	
}
