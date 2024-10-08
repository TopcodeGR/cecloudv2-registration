package com.ptopalidis.cecloud.registration.controllers;


import com.ptopalidis.cecloud.registration.dto.UserRegisteredDTO;
import com.ptopalidis.cecloud.registration.dto.UserRegistrationDTO;
import com.ptopalidis.cecloud.registration.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {

    private UserService userService;


    public  UserRegistrationController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserRegisteredDTO> register(@RequestBody UserRegistrationDTO userRegistrationDTO){

        UserRegisteredDTO entity  = userService.save(userRegistrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
}
