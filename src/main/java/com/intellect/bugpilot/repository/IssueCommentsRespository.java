package com.intellect.bugpilot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.IssueComments;
import com.intellect.bugpilot.domain.Issues;

@Repository
public interface IssueCommentsRespository extends JpaRepository<IssueComments, Long> {

	List<IssueComments> findByIssues(Issues issues);

}
