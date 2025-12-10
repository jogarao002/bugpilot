package com.intellect.bugpilot.service;

import com.intellect.bugpilot.service.dto.IssuesRequestDTO;
import com.intellect.bugpilot.service.dto.IssuesResponseDTO;

public interface IssuesService {

	IssuesRequestDTO createIssue(IssuesRequestDTO issuesRequestDTO);

	void deleteIssue(Long issueId);

	IssuesRequestDTO getIssueById(Long issueId);

	IssuesResponseDTO getAllIssues();

	IssuesResponseDTO getIssueByRaisedById(Long raisedBy);

	IssuesResponseDTO getIssueByRaisedTo(Long raisedTo);

	IssuesResponseDTO getIssueByAssignedTo(Long assignedTo);

}
