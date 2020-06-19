package com.iwen.pqserver.service;

import com.iwen.pqserver.entity.PqUser;

/**
 * @author i wen
 */
public class UserDetails {

    private PqUser pqUser;

    public UserDetails(PqUser pqUser) {
        this.pqUser = pqUser;

    }

    public String getPassword() {
        return pqUser.getPassword();
    }


    public String getUsername() {
        return pqUser.getEmail();
    }

    public Integer getUID() {
        return pqUser.getId();
    }


}
