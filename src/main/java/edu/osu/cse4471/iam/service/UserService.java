package edu.osu.cse4471.iam.service;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    public boolean createAccount(String fullname, String shortname, String password) {
        return true;
    }

    public boolean login(String shortname, String password) {
        return true;
    }

    public boolean authenticate(String shortname, String roleName) {
        return true;
    }
    
}
