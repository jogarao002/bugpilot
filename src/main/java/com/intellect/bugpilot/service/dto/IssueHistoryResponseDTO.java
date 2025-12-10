package com.intellect.bugpilot.service.dto;

import java.io.Serializable;
import java.util.Date;

public class IssueHistoryResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String issueName;
	
	private Date createdDate;
	
	private String userName;
	
	private IssueStatusEnum oldStatus;

	private String commentText;	
	
	public IssueHistoryResponseDTO() {

    }

    private IssueHistoryResponseDTO(IssueHistoryResponseDTOBuilder builder) {
        this.issueName = builder.issueName;
        this.createdDate = builder.createdDate;
        this.userName = builder.userName;
        this.oldStatus = builder.oldStatus;
        this.commentText = builder.commentText;
    }

    public static class IssueHistoryResponseDTOBuilder implements Serializable {

        private static final long serialVersionUID = 1L;

        private String issueName;

        private Date createdDate;

        private String userName;

        private IssueStatusEnum oldStatus;

        private String commentText;

        public IssueHistoryResponseDTOBuilder issueName(String issueName) {
            this.issueName = issueName;
            return this;
        }

        public IssueHistoryResponseDTOBuilder createdDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public IssueHistoryResponseDTOBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public IssueHistoryResponseDTOBuilder oldStatus(IssueStatusEnum oldStatus) {
            this.oldStatus = oldStatus;
            return this;
        }

        public IssueHistoryResponseDTOBuilder commentText(String commentText) {
            this.commentText = commentText;
            return this;
        }

        public IssueHistoryResponseDTO build() {
            return new IssueHistoryResponseDTO(this);
        }
    }

    public String getIssueName() {
        return issueName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getUserName() {
        return userName;
    }

    public IssueStatusEnum getOldStatus() {
        return oldStatus;
    }

    public String getCommentText() {
        return commentText;
    }

	@Override
	public String toString() {
		return "IssueHistoryResponseDTO [issueName=" + issueName + ", createdDate=" + createdDate + ", userName="
				+ userName + ", oldStatus=" + oldStatus + ", commentText=" + commentText + "]";
	}

}
