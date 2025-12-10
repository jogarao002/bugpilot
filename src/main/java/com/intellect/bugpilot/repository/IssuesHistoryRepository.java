package com.intellect.bugpilot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.Issues;
import com.intellect.bugpilot.domain.IssuesHistory;

@Repository
public interface IssuesHistoryRepository extends JpaRepository<IssuesHistory, Long> {

	List<IssuesHistory> findByIssues(Issues issues);

}
