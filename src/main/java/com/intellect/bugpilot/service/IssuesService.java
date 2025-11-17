package com.intellect.bugpilot.service;

import java.util.List;

import com.intellect.bugpilot.service.dto.IssuesRequestDTO;

public interface IssuesService {

	IssuesRequestDTO createIssue(IssuesRequestDTO issuesRequestDTO);

	void deleteIssue(Long issueId);

	IssuesRequestDTO getIssueById(Long issueId);

	List<IssuesRequestDTO> getAllIssues();

}
