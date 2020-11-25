package edu.osu.cse4471.iam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.osu.cse4471.iam.dao.UserDao;
import edu.osu.cse4471.iam.model.User;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean createAccount(String fullname, String shortname, String password, String email) {
        try {
            userDao.createUser(shortname, password, email);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public User authenticate(String shortname, String password) {
        try {
            return userDao.authenticate(shortname, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
