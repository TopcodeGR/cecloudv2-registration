package com.ptopalidis.cecloud.registration.services;


import com.ptopalidis.cecloud.registration.dto.UserRegisteredDTO;
import com.ptopalidis.cecloud.registration.dto.UserRegistrationDTO;
import com.ptopalidis.cecloud.registration.entities.Authority;
import com.ptopalidis.cecloud.registration.entities.User;
import com.ptopalidis.cecloud.registration.exceptions.AlreadyExistsException;
import com.ptopalidis.cecloud.registration.mappers.UserRegisteredMapper;
import com.ptopalidis.cecloud.registration.mappers.UserRegistrationMapper;
import com.ptopalidis.cecloud.registration.repositories.AuthorityRepository;
import com.ptopalidis.cecloud.registration.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRegistrationMapper userRegistrationMapper;

    @Autowired
    private UserRegisteredMapper userRegisteredMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public UserRegisteredDTO save(UserRegistrationDTO userRegistrationDTO){
        if(userRepository.findUserByEmail(userRegistrationDTO.getEmail()).isPresent()){
            throw new AlreadyExistsException(format("Email %s is already used", userRegistrationDTO.getEmail()));
        }

        User user = userRegistrationMapper.toEntity(userRegistrationDTO);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        List<Authority> defaultAuthorities = new ArrayList<>();
        defaultAuthorities.add(authorityRepository.findAuthorityByName("simple-user"));

        user.getAuthorities().addAll(defaultAuthorities);

        user = userRepository.save(user);

        UserRegisteredDTO userRegisteredDTO = userRegisteredMapper.toDTO(user);

        return userRegisteredDTO;


    }



}
