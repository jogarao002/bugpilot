package com.intellect.bugpilot.domain;

import java.io.Serializable;
import java.util.Date;

import com.intellect.bugpilot.audit.Auditable;
import com.intellect.bugpilot.service.dto.IssueStatusEnum;

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

@Entity
@Table(name = "ISSUES_HISTORY")
public class IssuesHistory extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long historyId;

	@ManyToOne
	@JoinColumn(name = "issue_id", referencedColumnName = "issueId")
	private Issues issues;

	@ManyToOne
	@JoinColumn(name = "changed_id", referencedColumnName = "userId")
	private Users users;

	@Enumerated(EnumType.STRING)
	private IssueStatusEnum oldStatus;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private IssueStatusEnum newStatus;

	private String remarks;

	private Date changedAt;

	public IssuesHistory() {
	}

	private IssuesHistory(IssuesHistoryBuilder builder) {
		this.historyId = builder.historyId;
		this.issues = builder.issues;
		this.users = builder.users;
		this.oldStatus = builder.oldStatus;
		this.newStatus = builder.newStatus;
		this.remarks = builder.remarks;
		this.changedAt = builder.changedAt;
	}

	public static class IssuesHistoryBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long historyId;

		private Issues issues;

		private Users users;

		private IssueStatusEnum oldStatus;

		private IssueStatusEnum newStatus;

		private String remarks;

		private Date changedAt;

		public IssuesHistoryBuilder historyId(Long historyId) {
			this.historyId = historyId;
			return this;
		}

		public IssuesHistoryBuilder issues(Issues issues) {
			this.issues = issues;
			return this;
		}

		public IssuesHistoryBuilder users(Users users) {
			this.users = users;
			return this;
		}

		public IssuesHistoryBuilder oldStatus(IssueStatusEnum oldStatus) {
			this.oldStatus = oldStatus;
			return this;
		}

		public IssuesHistoryBuilder newStatus(IssueStatusEnum newStatus) {
			this.newStatus = newStatus;
			return this;
		}

		public IssuesHistoryBuilder remarks(String remarks) {
			this.remarks = remarks;
			return this;
		}

		public IssuesHistoryBuilder changedAt(Date changedAt) {
			this.changedAt = changedAt;
			return this;
		}

		public IssuesHistory build() {
			return new IssuesHistory(this);
		}

	}

	public Long getHistoryId() {
		return historyId;
	}

	public Issues getIssues() {
		return issues;
	}

	public Users getUsers() {
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
		return "IssuesHistory [historyId=" + historyId + ", issues=" + issues + ", users=" + users + ", oldStatus="
				+ oldStatus + ", newStatus=" + newStatus + ", remarks=" + remarks + ", changedAt=" + changedAt + "]";
	}
	
}
