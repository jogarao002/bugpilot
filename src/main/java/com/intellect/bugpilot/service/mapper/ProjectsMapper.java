package com.intellect.bugpilot.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.bugpilot.domain.Projects;
import com.intellect.bugpilot.service.dto.ProjectsDTO;

@Mapper(componentModel = "spring", uses = {})
public interface ProjectsMapper extends EntityMapper<ProjectsDTO, Projects> {

}
