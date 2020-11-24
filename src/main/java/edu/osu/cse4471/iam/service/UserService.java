package edu.osu.cse4471.iam.service;

import org.springframework.stereotype.Component;

import edu.osu.cse4471.iam.model.User;

@Component
public class UserService {

    public boolean createAccount(String fullname, String shortname, String password) {
        return true;
    }

    public User login(String shortname, String password) {
        return null;
    }

    public boolean authenticate(String shortname, String roleName) {
        return true;
    }

    public User checkToken(String token) {
        return null;
    }
    
}
