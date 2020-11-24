package edu.osu.cse4471.iam.model;

import java.util.List;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
public class Role {
    private String name;
    private String description;
    private String admin;
    private List<User> members;
}
