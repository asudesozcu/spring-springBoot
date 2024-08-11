package com.example.firstapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean isValid(String username, String password) {
        boolean isvalidusername = username.equalsIgnoreCase("as");
        boolean isvalidPassword = password.equalsIgnoreCase("as");

        return isvalidPassword && isvalidusername;

    }

}
