package com.intellect.bugpilot.service.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import com.intellect.bugpilot.domain.Roles;
import com.intellect.bugpilot.service.dto.RolesDTO;

@Mapper(componentModel = "spring", uses = {}, builder = @Builder(disableBuilder = false))
public interface RolesMapper extends EntityMapper<RolesDTO, Roles> {

}
