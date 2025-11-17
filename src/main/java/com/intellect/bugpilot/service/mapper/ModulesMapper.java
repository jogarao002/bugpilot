package com.intellect.bugpilot.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.bugpilot.domain.Modules;
import com.intellect.bugpilot.service.dto.ModulesDTO;

@Mapper(componentModel = "spring", uses = {})
public interface ModulesMapper extends EntityMapper<ModulesDTO, Modules> {

}
