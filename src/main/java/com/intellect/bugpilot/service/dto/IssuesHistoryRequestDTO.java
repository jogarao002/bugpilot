package com.intellect.bugpilot.service.dto;

import java.io.Serializable;
import java.util.Date;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.validation.constraints.NotNull;

public class IssuesHistoryRequestDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long historyId;

	@NotNull
	private Long issueId;

	@NotNull
	private Long userId;

	private IssueStatusEnum oldStatus;

	private IssueStatusEnum newStatus;

	private String remarks;

	private Date changedAt;

	public IssuesHistoryRequestDTO() {
	}

	private IssuesHistoryRequestDTO(IssuesHistoryRequestDTOBuilder builder) {
		this.historyId = builder.historyId;
		this.issueId = builder.issueId;
		this.userId = builder.userId;
		this.oldStatus = builder.oldStatus;
		this.newStatus = builder.newStatus;
		this.remarks = builder.remarks;
		this.changedAt = builder.changedAt;
	}

	public static class IssuesHistoryRequestDTOBuilder implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long historyId;
		private Long issueId;
		private Long userId;
		private IssueStatusEnum oldStatus;
		private IssueStatusEnum newStatus;
		private String remarks;
		private Date changedAt;

		public IssuesHistoryRequestDTOBuilder historyId(Long historyId) {
			this.historyId = historyId;
			return this;
		}

		public IssuesHistoryRequestDTOBuilder issueId(@NotNull Long issueId) {
			this.issueId = issueId;
			return this;
		}

		public IssuesHistoryRequestDTOBuilder userId(@NotNull Long userId) {
			this.userId = userId;
			return this;
		}

		public IssuesHistoryRequestDTOBuilder oldStatus(IssueStatusEnum oldStatus) {
			this.oldStatus = oldStatus;
			return this;
		}

		public IssuesHistoryRequestDTOBuilder newStatus(IssueStatusEnum newStatus) {
			this.newStatus = newStatus;
			return this;
		}

		public IssuesHistoryRequestDTOBuilder remarks(String remarks) {
			this.remarks = remarks;
			return this;
		}

		public IssuesHistoryRequestDTOBuilder changedAt(Date changedAt) {
			this.changedAt = changedAt;
			return this;
		}

		public IssuesHistoryRequestDTO build() {
			return new IssuesHistoryRequestDTO(this);
		}
	}

	public Long getHistoryId() {
		return historyId;
	}

	public Long getIssueId() {
		return issueId;
	}

	public Long getUserId() {
		return userId;
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
		return "IssuesHistoryRequestDTO{" + "historyId=" + historyId + ", issueId=" + issueId + ", userId=" + userId
				+ ", oldStatus=" + oldStatus + ", newStatus=" + newStatus + ", remarks='" + remarks + '\''
				+ ", changedAt=" + changedAt + '}';
	}

}
