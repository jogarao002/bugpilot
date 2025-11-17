package com.intellect.bugpilot.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.intellect.bugpilot.domain.IssueComments;
import com.intellect.bugpilot.domain.Issues;
import com.intellect.bugpilot.domain.Users;
import com.intellect.bugpilot.exception.ResourceNotFoundException;
import com.intellect.bugpilot.repository.IssueCommentsRespository;
import com.intellect.bugpilot.service.IssueCommentsService;
import com.intellect.bugpilot.service.dto.IssueCommentsRequestDTO;
import com.intellect.bugpilot.service.dto.IssuesRequestDTO;
import com.intellect.bugpilot.service.dto.UsersRequestDTO;

@Service
public class IssueCoomentsServiceImpl implements IssueCommentsService {
	
	@Autowired
	private issuesServiceImpl issuesServiceImpl;
	
	@Autowired
	private UsersServiceImpl usersServiceImpl;
	
	@Autowired
	private IssueCommentsRespository issueCommentsRespository;

	@Override
	public IssueCommentsRequestDTO createIssueComment(IssueCommentsRequestDTO issueCommentsRequestDTO) {
		IssuesRequestDTO issuesRequestDTO = issuesServiceImpl.getIssueById(issueCommentsRequestDTO.getIssueId());
		
		UsersRequestDTO usersRequestDTO = usersServiceImpl.findOne(issueCommentsRequestDTO.getUserId());
		
		Issues issues = new Issues.IssuesBuilder()
							.issueId(issuesRequestDTO.getIssueId())
							.build();
		
		Users users = new Users.UsersBuilder()
						.userId(usersRequestDTO.getUserId())
						.build();
		
		IssueComments issueComments = new IssueComments.IssueCommentsBuilder()
										.commentId(issueCommentsRequestDTO.getCommentId() != null ? issueCommentsRequestDTO.getCommentId() : null)
										.issues(issues)
										.users(users)
										.commentText(issueCommentsRequestDTO.getCommentText())
										.createdAt(Date.from(LocalDateTime.now()
												.atZone(ZoneId.systemDefault())
												.toInstant()))
										.build();
		
		issueComments = issueCommentsRespository.save(issueComments);
		
		return new IssueCommentsRequestDTO.IssueCommentsRequestDTOBuilder()
				.commentId(issueComments.getCommentId())
				.issueId(issueCommentsRequestDTO.getIssueId())
				.userId(issueCommentsRequestDTO.getUserId())
				.commentText(issueCommentsRequestDTO.getCommentText())
				.createdAt(issueComments.getCreatedAt())
				.build();
				
	}

	@Override
	public List<IssueCommentsRequestDTO> getAllComments() {
		List<IssueCommentsRequestDTO> issueCommentsRequestDTOList = new ArrayList<>();
		List<IssueComments> issueCommentsList = issueCommentsRespository.findAll();
		if(!CollectionUtils.isEmpty(issueCommentsList)) {
			issueCommentsList.forEach(issueComments -> {
				IssueCommentsRequestDTO issueCommentsRequestDTO = new IssueCommentsRequestDTO.IssueCommentsRequestDTOBuilder()
																		.commentId(issueComments.getCommentId())
																		.issueId(issueComments.getIssues().getIssueId())
																		.userId(issueComments.getUsers().getUserId())
																		.commentText(issueComments.getCommentText())
																		.createdAt(issueComments.getCreatedAt())
																		.build();
				issueCommentsRequestDTOList.add(issueCommentsRequestDTO);
			});
		}
		return issueCommentsRequestDTOList;
	}

	@Override
	public IssueCommentsRequestDTO getCommentById(Long commentId) {
		IssueComments issueComments = issueCommentsRespository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment Not Found with ID: " + commentId));
		
		return  new IssueCommentsRequestDTO.IssueCommentsRequestDTOBuilder()
				.commentId(issueComments.getCommentId())
				.issueId(issueComments.getIssues().getIssueId())
				.userId(issueComments.getUsers().getUserId())
				.commentText(issueComments.getCommentText())
				.createdAt(issueComments.getCreatedAt())
				.build();
	}

}
