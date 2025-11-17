package com.intellect.bugpilot.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.bugpilot.domain.SubModules;
import com.intellect.bugpilot.service.dto.SubModulesDTO;

@Mapper(componentModel = "spring", uses = {})
public interface SubModulesMapper extends EntityMapper<SubModulesDTO, SubModules> {

}
