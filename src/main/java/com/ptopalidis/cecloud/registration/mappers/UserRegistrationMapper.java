package com.ptopalidis.cecloud.registration.mappers;


import com.ptopalidis.cecloud.registration.dto.UserRegistrationDTO;
import com.ptopalidis.cecloud.registration.entities.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {

    @Mapping(target = "email", source = "userRegistrationDTO.email")
    @Mapping(target =  "password", source = "userRegistrationDTO.password")
    public User toEntity(UserRegistrationDTO userRegistrationDTO);

    public UserRegistrationDTO toDTO(User user);
}
