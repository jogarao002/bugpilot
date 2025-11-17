package com.intellect.bugpilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

}
