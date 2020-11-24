package edu.osu.cse4471.iam.model;

import lombok.Data;

@Data
public class User {
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    private String username;
    private String password;
    private String email;
}


