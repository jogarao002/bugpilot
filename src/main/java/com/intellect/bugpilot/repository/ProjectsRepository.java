package com.intellect.bugpilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.Projects;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {

}
