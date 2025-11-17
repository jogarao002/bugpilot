package com.intellect.bugpilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.IssueComments;

@Repository
public interface IssueCommentsRespository extends JpaRepository<IssueComments, Long> {

}
