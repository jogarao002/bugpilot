package com.intellect.bugpilot.service.dto;

import java.io.Serializable;
import java.util.Date;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.validation.constraints.NotNull;

public class IssueCommentsRequestDTO extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long commentId;

	@NotNull
	private Long issueId;

	@NotNull
	private Long userId;

	private String commentText;

	private Date createdAt;

	public IssueCommentsRequestDTO() {
	}

	private IssueCommentsRequestDTO(IssueCommentsRequestDTOBuilder builder) {
		this.commentId = builder.commentId;
		this.issueId = builder.issueId;
		this.userId = builder.userId;
		this.commentText = builder.commentText;
		this.createdAt = builder.createdAt;
	}

	public static class IssueCommentsRequestDTOBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long commentId;
		private Long issueId;
		private Long userId;
		private String commentText;
		private Date createdAt;

		public IssueCommentsRequestDTOBuilder commentId(Long commentId) {
			this.commentId = commentId;
			return this;
		}

		public IssueCommentsRequestDTOBuilder issueId(@NotNull Long issueId) {
			this.issueId = issueId;
			return this;
		}

		public IssueCommentsRequestDTOBuilder userId(@NotNull Long userId) {
			this.userId = userId;
			return this;
		}

		public IssueCommentsRequestDTOBuilder commentText(String commentText) {
			this.commentText = commentText;
			return this;
		}

		public IssueCommentsRequestDTOBuilder createdAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public IssueCommentsRequestDTO build() {
			return new IssueCommentsRequestDTO(this);
		}
	}

	public Long getCommentId() {
		return commentId;
	}

	public Long getIssueId() {
		return issueId;
	}

	public Long getUserId() {
		return userId;
	}

	public String getCommentText() {
		return commentText;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public String toString() {
		return "IssueCommentsRequestDTO [commentId=" + commentId + ", issueId=" + issueId + ", userId=" + userId
				+ ", commentText=" + commentText + ", createdAt=" + createdAt + "]";
	}

	
}
