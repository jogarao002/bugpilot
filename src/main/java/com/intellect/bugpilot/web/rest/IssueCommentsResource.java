package com.intellect.bugpilot.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.bugpilot.service.IssueCommentsService;
import com.intellect.bugpilot.service.dto.IssueCommentsRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/issue_comments")
public class IssueCommentsResource {
	
	@Autowired
	private IssueCommentsService issueCommentsService;
	
	@PostMapping("/create_issue_comment")
	public ResponseEntity<IssueCommentsRequestDTO> createIssueHistory(@Valid @RequestBody IssueCommentsRequestDTO issueCommentsRequestDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(issueCommentsService.createIssueComment(issueCommentsRequestDTO));
	}
	
	@GetMapping("/get/{commentId}")
    public ResponseEntity<IssueCommentsRequestDTO> getIssueHistory(@NotNull @PathVariable Long commentId) {
    	return ResponseEntity.ok(issueCommentsService.getCommentById(commentId));
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllIssueHistory() {
    	List<IssueCommentsRequestDTO> issuesComments = issueCommentsService.getAllComments();
		if (issuesComments.isEmpty()) {
			Map<String, String> response = new HashMap<>();
	        response.put("message", "There are no comments");
	        return ResponseEntity.ok(response);
		}
		return ResponseEntity.ok(issuesComments);
    }

    @PutMapping("/update")
    public ResponseEntity<IssueCommentsRequestDTO> updateIssueHistory(
            @Valid @RequestBody IssueCommentsRequestDTO issueCommentsRequestDTO) {
    	IssueCommentsRequestDTO updated = issueCommentsService.createIssueComment(issueCommentsRequestDTO);

		if (updated != null) {
			return ResponseEntity.ok().body(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
    }

}
