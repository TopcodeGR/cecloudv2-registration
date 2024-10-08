package com.ptopalidis.cecloud.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import static java.lang.String.format;
public class AlreadyExistsException extends ResponseStatusException {


    public AlreadyExistsException(String message){
        super(HttpStatus.CONFLICT,message);
    }


    public AlreadyExistsException(String entity, String label){
        super(HttpStatus.CONFLICT,format("%s already exists with the same name [%s]",entity,label));

    }
}
