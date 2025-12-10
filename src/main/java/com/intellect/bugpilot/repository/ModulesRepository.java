package com.intellect.bugpilot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.Modules;
import com.intellect.bugpilot.domain.Projects;
import com.intellect.bugpilot.service.dto.ModuleAndSubModuleStatusEnum;

@Repository
public interface ModulesRepository extends JpaRepository<Modules, Long> {

	List<Modules> findByHasSubModule(Boolean status);

	List<Modules> findByProjectsAndStatus(Projects projects, ModuleAndSubModuleStatusEnum completed);


}
