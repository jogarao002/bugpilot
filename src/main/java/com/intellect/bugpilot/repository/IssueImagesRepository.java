package com.intellect.bugpilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.IssueImages;

@Repository
public interface IssueImagesRepository extends JpaRepository<IssueImages, Long> {

}
