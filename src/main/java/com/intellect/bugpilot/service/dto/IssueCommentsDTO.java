package com.intellect.bugpilot.service.dto;

import java.io.Serializable;
import java.util.Date;

import com.intellect.bugpilot.audit.Auditable;

public class IssueCommentsDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long commentId;

	private IssuesDTO issues;

	private UsersDTO users;

	private String commonetText;

	private Date createdAt;

	public IssueCommentsDTO() {
	}

	private IssueCommentsDTO(IssueCommentsDTOBuilder builder) {
		this.commentId = builder.commentId;
		this.issues = builder.issues;
		this.users = builder.users;
		this.commonetText = builder.commonetText;
		this.createdAt = builder.createdAt;
	}

	public static class IssueCommentsDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long commentId;
		private IssuesDTO issues;
		private UsersDTO users;
		private String commonetText;
		private Date createdAt;

		public IssueCommentsDTOBuilder commentId(Long commentId) {
			this.commentId = commentId;
			return this;
		}

		public IssueCommentsDTOBuilder issues(IssuesDTO issues) {
			this.issues = issues;
			return this;
		}

		public IssueCommentsDTOBuilder users(UsersDTO users) {
			this.users = users;
			return this;
		}

		public IssueCommentsDTOBuilder commonetText(String commonetText) {
			this.commonetText = commonetText;
			return this;
		}

		public IssueCommentsDTOBuilder createdAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public IssueCommentsDTO build() {
			return new IssueCommentsDTO(this);
		}
	}

	public Long getCommentId() {
		return commentId;
	}

	public IssuesDTO getIssues() {
		return issues;
	}

	public UsersDTO getUsers() {
		return users;
	}

	public String getCommonetText() {
		return commonetText;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "IssueCommentsDTO [commentId=" + commentId + ", issues=" + issues + ", users=" + users
				+ ", commonetText=" + commonetText + ", createdAt=" + createdAt + "]";
	}
}
