package com.intellect.bugpilot.service.dto;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

public class IssuesHistoryDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long historyId;

	private IssuesDTO issues;

	private UsersDTO users;

	private IssueStatusEnum oldStatus;

	public IssuesHistoryDTO() {
	}

	private IssuesHistoryDTO(IssuesHistoryDTOBuilder builder) {
		this.historyId = builder.historyId;
		this.issues = builder.issues;
		this.users = builder.users;
		this.oldStatus = builder.oldStatus;
	}

	public static class IssuesHistoryDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long historyId;

		private IssuesDTO issues;

		private UsersDTO users;

		private IssueStatusEnum oldStatus;

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

	@Override
	public String toString() {
		return "IssuesHistoryDTO [historyId=" + historyId + ", issues=" + issues + ", users=" + users + ", oldStatus="
				+ oldStatus + "]";
	}


}
