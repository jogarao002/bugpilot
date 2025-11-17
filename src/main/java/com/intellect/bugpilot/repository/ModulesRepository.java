package com.intellect.bugpilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.Modules;

@Repository
public interface ModulesRepository extends JpaRepository<Modules, Long> {

}
