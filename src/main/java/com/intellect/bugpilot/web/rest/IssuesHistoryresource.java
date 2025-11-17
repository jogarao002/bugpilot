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

import com.intellect.bugpilot.service.IssuesHistoryService;
import com.intellect.bugpilot.service.dto.IssuesHistoryRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/issues_history")
public class IssuesHistoryresource {

	@Autowired
	private IssuesHistoryService issuesHistoryService;
	
	@PostMapping("/create_issue_history")
	public ResponseEntity<IssuesHistoryRequestDTO> createIssueHistory(@Valid @RequestBody IssuesHistoryRequestDTO issuesHistoryRequestDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(issuesHistoryService.createIssueHistory(issuesHistoryRequestDTO));
	}
	
    @GetMapping("/get/{historyId}")
    public ResponseEntity<IssuesHistoryRequestDTO> getIssueHistory(@NotNull @PathVariable Long historyId) {
    	return ResponseEntity.ok(issuesHistoryService.getHistoryById(historyId));
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllIssueHistory() {
    	List<IssuesHistoryRequestDTO> issuesHistory = issuesHistoryService.getAllIssuesHistory();
		if (issuesHistory.isEmpty()) {
			Map<String, String> response = new HashMap<>();
	        response.put("message", "There are no history");
	        return ResponseEntity.ok(response);
		}
		return ResponseEntity.ok(issuesHistory);
    }

    @PutMapping("/update")
    public ResponseEntity<IssuesHistoryRequestDTO> updateIssueHistory(
            @Valid @RequestBody IssuesHistoryRequestDTO issuesHistoryRequestDTO) {
    	IssuesHistoryRequestDTO updated = issuesHistoryService.createIssueHistory(issuesHistoryRequestDTO);

		if (updated != null) {
			return ResponseEntity.ok().body(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
    }
}
