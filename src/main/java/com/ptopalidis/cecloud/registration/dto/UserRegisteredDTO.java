package com.ptopalidis.cecloud.registration.dto;

import com.ptopalidis.cecloud.registration.entities.Authority;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class UserRegisteredDTO {


    @Override
    public String toString() {
        return "UserRegisteredDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    private BigInteger id;
    private String email;

    private List<Authority> authorities = new ArrayList<>();

}
