package edu.osu.cse4471.iam.model;

import java.util.List;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("ROLE")
public class Role {
    private String name;
    private String description;
    private User admin;
    private List<User> members;
}
