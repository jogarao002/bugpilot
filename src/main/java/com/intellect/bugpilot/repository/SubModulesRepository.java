package com.intellect.bugpilot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.bugpilot.domain.Modules;
import com.intellect.bugpilot.domain.SubModules;
import com.intellect.bugpilot.service.dto.ModuleAndSubModuleStatusEnum;

@Repository
public interface SubModulesRepository extends JpaRepository<SubModules, Long> {

	List<SubModules> findByModulesAndStatus(Modules modules, ModuleAndSubModuleStatusEnum status);

}
