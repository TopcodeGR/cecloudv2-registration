package com.ptopalidis.cecloud.registration.mappers;


import com.ptopalidis.cecloud.registration.dto.UserRegisteredDTO;
import com.ptopalidis.cecloud.registration.entities.Authority;
import com.ptopalidis.cecloud.registration.entities.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserRegisteredMapper {


    public abstract UserRegisteredDTO toDTO(User user);


    public String authorityToString(Authority authority){
        return authority.getName();
    }
}
