package com.intellect.bugpilot.domain;

import java.io.Serializable;

import com.intellect.bugpilot.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "issue_images")
public class IssueImages extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long issueImageId;
	
	@OneToOne
	@JoinColumn(name = "issue_id",referencedColumnName = "issueId")
	private Issues issues;
	
	@Column(nullable = false)
	private String filePath;
	
	public IssueImages() {}

	private IssueImages(IssueImagesBuilder builder) {
		this.issueImageId=builder.issueImageId;
		this.filePath=builder.filePath;
		this.issues=builder.issues;
	}
	
	public static class IssueImagesBuilder extends Auditable implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private Long issueImageId;
		
		private Issues issues;
		
		private String filePath;
		
		public IssueImagesBuilder issueImageId(Long issueImageId) {
			this.issueImageId = issueImageId;
			return this;
		}
		
		public IssueImagesBuilder issues(Issues issues) {
			this.issues = issues;
			return this;
		}
		
		public IssueImagesBuilder filePath(String filePath) {
			this.filePath= filePath;
			return this;
		}
		
		public IssueImages build() {
			return new IssueImages(this);
		}
		
	}

	public Long getIssueImageId() {
		return issueImageId;
	}

	public String getFilePath() {
		return filePath;
	}

	public Issues getIssues() {
		return issues;
	}

	@Override
	public String toString() {
		return "IssueImages [issueImageId=" + issueImageId + ", issues=" + issues + ", filePath=" + filePath + "]";
	}
	
}
