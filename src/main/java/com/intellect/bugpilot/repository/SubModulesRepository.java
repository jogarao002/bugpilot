package com.intellect.bugpilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.SubModules;

@Repository
public interface SubModulesRepository extends JpaRepository<SubModules, Long> {

}
