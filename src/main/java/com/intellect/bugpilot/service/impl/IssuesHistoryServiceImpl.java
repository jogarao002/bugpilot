package com.intellect.bugpilot.service.impl;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.intellect.bugpilot.domain.IssueComments;
import com.intellect.bugpilot.domain.Issues;
import com.intellect.bugpilot.domain.IssuesHistory;
import com.intellect.bugpilot.domain.Users;
import com.intellect.bugpilot.exception.ResourceNotFoundException;
import com.intellect.bugpilot.repository.IssueCommentsRespository;
import com.intellect.bugpilot.repository.IssuesHistoryRepository;
import com.intellect.bugpilot.service.IssuesHistoryService;
import com.intellect.bugpilot.service.dto.IssueHistoryResponseDTO;
import com.intellect.bugpilot.service.dto.IssuesHistoryRequestDTO;

@Service
public class IssuesHistoryServiceImpl implements IssuesHistoryService {
	
	@Autowired
	private IssuesHistoryRepository issuesHistoryRepository;
	
	@Autowired
	private IssueCommentsRespository issueCommentsRespository;

	@Override
	public IssuesHistoryRequestDTO createIssueHistory(IssuesHistoryRequestDTO issuesHistoryRequestDTO) {
		Issues issues = new Issues.IssuesBuilder()
									.issueId(issuesHistoryRequestDTO.getIssueId())
									.build();
		Users users = new Users.UsersBuilder()
								.userId(issuesHistoryRequestDTO.getUserId())
								.build();
		
		IssuesHistory issuesHistory = new IssuesHistory.IssuesHistoryBuilder()
														.historyId(issuesHistoryRequestDTO.getHistoryId() != null ? issuesHistoryRequestDTO.getHistoryId() : null)
														.issues(issues)
														.users(users)
														.oldStatus(issuesHistoryRequestDTO.getOldStatus())
														.build();
		
		issuesHistory= issuesHistoryRepository.save(issuesHistory);
		
		return new IssuesHistoryRequestDTO.IssuesHistoryRequestDTOBuilder()
											.historyId(issuesHistory.getHistoryId())
											.issueId(issuesHistory.getIssues().getIssueId())
											.userId(issuesHistory.getUsers().getUserId())
											.oldStatus(issuesHistory.getOldStatus())
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
																					.build();
				issuesHistoryRequestDTOList.add(issuesHistoryRequestDTO);
			});
		}
		return issuesHistoryRequestDTOList;
	}

	@Override
	public List<IssueHistoryResponseDTO> getHistoryByIssueId(Long issueId) {

	    Issues issues = new Issues.IssuesBuilder()
	            .issueId(issueId)
	            .build();

	    List<IssuesHistory> issuesHistories = issuesHistoryRepository.findByIssues(issues);
	    List<IssueComments> issueComments = issueCommentsRespository.findByIssues(issues);

	    List<IssueHistoryResponseDTO> historyList = new ArrayList<>();

	    for (IssuesHistory history : issuesHistories) {

	        IssueComments matchingComment = issueComments.stream()
	                .filter(c -> isSameSecond(history.getCreatedAt(), c.getCreatedAt()))
	                .findFirst()
	                .orElse(null);

	        String commentText = matchingComment != null ? matchingComment.getCommentText() : null;

	        IssueHistoryResponseDTO dto = new IssueHistoryResponseDTO.IssueHistoryResponseDTOBuilder()
	                .issueName(history.getIssues().getIssueName())
	                .createdDate(history.getCreatedAt())
	                .userName(history.getUsers().getFirstName() + " " + history.getUsers().getLastName())
	                .oldStatus(history.getOldStatus())
	                .commentText(commentText)
	                .build();

	        historyList.add(dto);
	    }


	    return historyList;
	}

	
	private boolean isSameSecond(Date d1, Date d2) {
	    return d1.toInstant().truncatedTo(ChronoUnit.SECONDS)
	            .equals(d2.toInstant().truncatedTo(ChronoUnit.SECONDS));
	}




}
