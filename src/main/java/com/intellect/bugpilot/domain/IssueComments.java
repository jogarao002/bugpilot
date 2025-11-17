package com.intellect.bugpilot.domain;

import java.io.Serializable;
import java.util.Date;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ISSUE_COMMENTS")
public class IssueComments extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;

	@ManyToOne
	@JoinColumn(name = "issue_id", referencedColumnName = "issueId")
	private Issues issues;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	private Users users;

	private String commentText;

	private Date createdAt;

	public IssueComments() {

	}

	public IssueComments(IssueCommentsBuilder builder) {
		this.commentId = builder.commentId;
		this.issues = builder.issues;
		this.users = builder.users;
		this.commentText = builder.commentText;
		this.createdAt = builder.createdAt;
	}

	public static class IssueCommentsBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long commentId;

		private Issues issues;

		private Users users;

		private String commentText;

		private Date createdAt;

		public IssueCommentsBuilder commentId(Long commentId) {
			this.commentId = commentId;
			return this;
		}

		public IssueCommentsBuilder issues(Issues issues) {
			this.issues = issues;
			return this;
		}

		public IssueCommentsBuilder users(Users users) {
			this.users = users;
			return this;
		}

		public IssueCommentsBuilder commentText(String commentText) {
			this.commentText = commentText;
			return this;
		}

		public IssueCommentsBuilder createdAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public IssueComments build() {
			return new IssueComments(this);
		}

	}

	public Long getCommentId() {
		return commentId;
	}

	public Issues getIssues() {
		return issues;
	}

	public Users getUsers() {
		return users;
	}

	public String getCommentText() {
		return commentText;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "IssueComments [commentId=" + commentId + ", issues=" + issues + ", users=" + users + ", commentText="
				+ commentText + ", createdAt=" + createdAt + "]";
	}

}
