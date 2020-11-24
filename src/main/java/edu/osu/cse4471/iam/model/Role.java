package edu.osu.cse4471.iam.model;

import java.util.List;

import lombok.Data;

@Data
public class Role {
    public Role(String name, String description, String admin) {
        this.name = name;
        this.description = description;
        this.admin = admin;
    }

    private String name;
    private String description;
    private String admin;
    private List<User> members;
}
