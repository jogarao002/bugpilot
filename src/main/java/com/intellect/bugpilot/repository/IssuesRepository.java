package com.intellect.bugpilot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.Issues;
import com.intellect.bugpilot.domain.Users;

@Repository
public interface IssuesRepository extends JpaRepository<Issues, Long> {

	List<Issues> findByRaisedBy(Users users);

	List<Issues> findByRaisedTo(Users users);

	List<Issues> findByAssignedTo(Users users);

}
