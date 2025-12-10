package com.intellect.bugpilot.service.dto;

import java.io.Serializable;
import java.util.List;

public class IssuesResponseDTO implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private long raisedIssues;
    private long resolvedIssues;
    private long assignedIssues;
    private long pendingIssues;

    private List<IssuesRequestDTO> issues;	
    
    public IssuesResponseDTO() {
    }

    private IssuesResponseDTO(IssuesResponseDTOBuilder builder) {
        this.raisedIssues = builder.raisedIssues;
        this.resolvedIssues = builder.resolvedIssues;
        this.assignedIssues = builder.assignedIssues;
        this.pendingIssues = builder.pendingIssues;
        this.issues = builder.issues;
    }

    public static class IssuesResponseDTOBuilder implements Serializable {

        private static final long serialVersionUID = 1L;

        private long raisedIssues;
        private long resolvedIssues;
        private long assignedIssues;
        private long pendingIssues;

        private List<IssuesRequestDTO> issues;

        public IssuesResponseDTOBuilder raisedIssues(long raisedIssues) {
            this.raisedIssues = raisedIssues;
            return this;
        }

        public IssuesResponseDTOBuilder resolvedIssues(long resolvedIssues) {
            this.resolvedIssues = resolvedIssues;
            return this;
        }

        public IssuesResponseDTOBuilder assignedIssues(long assignedIssues) {
            this.assignedIssues = assignedIssues;
            return this;
        }

        public IssuesResponseDTOBuilder pendingIssues(long pendingIssues) {
            this.pendingIssues = pendingIssues;
            return this;
        }

        public IssuesResponseDTOBuilder issues(List<IssuesRequestDTO> issues) {
            this.issues = issues;
            return this;
        }

        public IssuesResponseDTO build() {
            return new IssuesResponseDTO(this);
        }
    }

    public long getRaisedIssues() {
        return raisedIssues;
    }

    public long getResolvedIssues() {
        return resolvedIssues;
    }

    public long getAssignedIssues() {
        return assignedIssues;
    }

    public long getPendingIssues() {
        return pendingIssues;
    }

    public List<IssuesRequestDTO> getIssues() {
        return issues;
    }

    @Override
    public String toString() {
        return "IssuesResponseDTO [raisedIssues=" + raisedIssues 
                + ", resolvedIssues=" + resolvedIssues 
                + ", assignedIssues=" + assignedIssues 
                + ", pendingIssues=" + pendingIssues 
                + ", issues=" + issues + "]";
    }

}
