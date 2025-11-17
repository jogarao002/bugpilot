package com.intellect.bugpilot.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.bugpilot.domain.Users;
import com.intellect.bugpilot.service.dto.UsersDTO;

@Mapper(componentModel = "spring", uses = {})
public interface UsersMapper extends EntityMapper<UsersDTO, Users> {

}
