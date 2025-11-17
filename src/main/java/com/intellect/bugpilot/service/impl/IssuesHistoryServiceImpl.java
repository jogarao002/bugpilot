package com.intellect.bugpilot.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.intellect.bugpilot.domain.Issues;
import com.intellect.bugpilot.domain.IssuesHistory;
import com.intellect.bugpilot.domain.Users;
import com.intellect.bugpilot.exception.ResourceNotFoundException;
import com.intellect.bugpilot.repository.IssuesHistoryRepository;
import com.intellect.bugpilot.service.IssuesHistoryService;
import com.intellect.bugpilot.service.dto.IssuesHistoryRequestDTO;
import com.intellect.bugpilot.service.dto.IssuesRequestDTO;
import com.intellect.bugpilot.service.dto.UsersRequestDTO;

@Service
public class IssuesHistoryServiceImpl implements IssuesHistoryService {
	
	@Autowired
	private IssuesHistoryRepository issuesHistoryRepository;
	
	@Autowired
	private issuesServiceImpl issuesServiceImpl;
	
	@Autowired
	private UsersServiceImpl usersServiceImpl;

	@Override
	public IssuesHistoryRequestDTO createIssueHistory(IssuesHistoryRequestDTO issuesHistoryRequestDTO) {
		IssuesRequestDTO issuesRequestDTO = issuesServiceImpl.getIssueById(issuesHistoryRequestDTO.getIssueId());
		Issues issues = new Issues.IssuesBuilder()
									.issueId(issuesRequestDTO.getIssueId())
									.build();
		UsersRequestDTO usersRequestDTO = usersServiceImpl.findOne(issuesHistoryRequestDTO.getUserId());
		Users users = new Users.UsersBuilder()
								.userId(usersRequestDTO.getUserId())
								.build();
		
		IssuesHistory issuesHistory = new IssuesHistory.IssuesHistoryBuilder()
														.historyId(issuesHistoryRequestDTO.getHistoryId() != null ? issuesHistoryRequestDTO.getHistoryId() : null)
														.issues(issues)
														.users(users)
														.oldStatus(issuesRequestDTO.getStatus() != null
														        ? issuesRequestDTO.getStatus()
														                : issuesHistoryRequestDTO.getOldStatus())
														.newStatus(issuesHistoryRequestDTO.getNewStatus())
														.remarks(issuesHistoryRequestDTO.getRemarks())
														.changedAt(Date.from(
																LocalDateTime.now()
																.atZone(ZoneId.systemDefault())
																.toInstant()
																))
														.build();
		
		issuesHistory= issuesHistoryRepository.save(issuesHistory);
		
		return new IssuesHistoryRequestDTO.IssuesHistoryRequestDTOBuilder()
											.historyId(issuesHistory.getHistoryId())
											.issueId(issuesHistory.getIssues().getIssueId())
											.userId(issuesHistory.getUsers().getUserId())
											.oldStatus(issuesHistory.getOldStatus())
											.newStatus(issuesHistory.getNewStatus())
											.remarks(issuesHistory.getRemarks())
											.changedAt(issuesHistory.getChangedAt())
											.build();
	}

	@Override
	public IssuesHistoryRequestDTO getHistoryById(Long historyId) {
		IssuesHistory issuesHistory = issuesHistoryRepository.findById(historyId)
				.orElseThrow(() -> new ResourceNotFoundException("Issue history Not Found with ID: " + historyId));
		return new IssuesHistoryRequestDTO.IssuesHistoryRequestDTOBuilder()
				.historyId(issuesHistory.getHistoryId())
				.issueId(issuesHistory.getIssues().getIssueId())
				.userId(issuesHistory.getUsers().getUserId())
				.oldStatus(issuesHistory.getOldStatus())
				.newStatus(issuesHistory.getNewStatus())
				.remarks(issuesHistory.getRemarks())
				.changedAt(issuesHistory.getChangedAt())
				.build();
	}

	@Override
	public List<IssuesHistoryRequestDTO> getAllIssuesHistory() {
		List<IssuesHistoryRequestDTO> issuesHistoryRequestDTOList = new ArrayList<>();
		List<IssuesHistory> issuesHistoriesList = issuesHistoryRepository.findAll();
		if(!CollectionUtils.isEmpty(issuesHistoriesList)) {
			issuesHistoriesList.forEach(issuesHistories -> {
				IssuesHistoryRequestDTO issuesHistoryRequestDTO = new IssuesHistoryRequestDTO.IssuesHistoryRequestDTOBuilder()
																					.historyId(issuesHistories.getHistoryId())
																					.issueId(issuesHistories.getIssues().getIssueId())
																					.userId(issuesHistories.getUsers().getUserId())
																					.oldStatus(issuesHistories.getOldStatus())
																					.newStatus(issuesHistories.getNewStatus())
																					.remarks(issuesHistories.getRemarks())
																					.changedAt(issuesHistories.getChangedAt())
																					.build();
				issuesHistoryRequestDTOList.add(issuesHistoryRequestDTO);
			});
		}
		return issuesHistoryRequestDTOList;
	}


}
