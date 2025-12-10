package com.intellect.bugpilot.web.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.bugpilot.service.IssuesService;
import com.intellect.bugpilot.service.dto.IssuesRequestDTO;
import com.intellect.bugpilot.service.dto.IssuesResponseDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/issues")
public class IssuesResources {

	@Autowired
	private IssuesService issuesService;

	@PostMapping("/create_issue")
	public ResponseEntity<IssuesRequestDTO> createIssue(@Valid @RequestBody IssuesRequestDTO issuesRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(issuesService.createIssue(issuesRequestDTO));
	}

	@GetMapping("/get_all")
	public ResponseEntity<?> getAllIssues() {
		IssuesResponseDTO issues = issuesService.getAllIssues();
		if (issues == null) {
			Map<String, String> response = new HashMap<>();
	        response.put("message", "There are no issues");
	        return ResponseEntity.ok(response);
		}
		return ResponseEntity.ok(issues);
	}

	@GetMapping("/get/{issueId}")
	public ResponseEntity<IssuesRequestDTO> getIssueById(@NotNull @PathVariable Long issueId) {
		return ResponseEntity.ok(issuesService.getIssueById(issueId));
	}

	@PutMapping("/update")
	public ResponseEntity<IssuesRequestDTO> updateIssue(@Valid @RequestBody IssuesRequestDTO issuesRequestDTO) {

		IssuesRequestDTO issue = issuesService.createIssue(issuesRequestDTO);

		if (issue != null) {
			return ResponseEntity.ok().body(issue);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{issueId}")
	public ResponseEntity<String> deleteIssue(@NotNull @PathVariable Long issueId) {
		issuesService.deleteIssue(issueId);
		return ResponseEntity.ok("Issue with ID " + issueId + " has been successfully deleted.");
	}
	
	@GetMapping("/get_issues_by_user_id/{raisedBy}")
	public ResponseEntity<IssuesResponseDTO> getIssueByRaisedById(@NotNull @PathVariable Long raisedBy) {
		return ResponseEntity.ok(issuesService.getIssueByRaisedById(raisedBy));
	}
	
	@GetMapping("/get_issues_by_raised_to/{raisedTo}")
	public ResponseEntity<IssuesResponseDTO> getIssueByRaisedTo(@NotNull @PathVariable Long raisedTo) {
		return ResponseEntity.ok(issuesService.getIssueByRaisedTo(raisedTo));
	}
	
	@GetMapping("/get_issues_by_assigned_to/{assignedTo}")
	public ResponseEntity<IssuesResponseDTO> getIssueByAssignedTo(@NotNull @PathVariable Long assignedTo) {
		return ResponseEntity.ok(issuesService.getIssueByAssignedTo(assignedTo));
	}
}
