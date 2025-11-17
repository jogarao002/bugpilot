package com.intellect.bugpilot.service;

import java.util.List;

import com.intellect.bugpilot.service.dto.IssueCommentsRequestDTO;

public interface IssueCommentsService {

	IssueCommentsRequestDTO createIssueComment(IssueCommentsRequestDTO issueCommentsRequestDTO);

	List<IssueCommentsRequestDTO> getAllComments();

	IssueCommentsRequestDTO getCommentById(Long commentId);

}
