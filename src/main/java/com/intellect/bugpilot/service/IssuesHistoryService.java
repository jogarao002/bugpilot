package com.intellect.bugpilot.service;

import java.util.List;

import com.intellect.bugpilot.service.dto.IssuesHistoryRequestDTO;

public interface IssuesHistoryService {

	IssuesHistoryRequestDTO createIssueHistory(IssuesHistoryRequestDTO issuesHistoryRequestDTO);

	IssuesHistoryRequestDTO getHistoryById(Long historyId);

	List<IssuesHistoryRequestDTO> getAllIssuesHistory();

}
