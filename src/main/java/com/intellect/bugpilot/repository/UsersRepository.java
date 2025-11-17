package com.intellect.bugpilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
