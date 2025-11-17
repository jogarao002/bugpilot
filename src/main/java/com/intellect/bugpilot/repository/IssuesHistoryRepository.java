package com.intellect.bugpilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.IssuesHistory;

@Repository
public interface IssuesHistoryRepository extends JpaRepository<IssuesHistory, Long> {

}
