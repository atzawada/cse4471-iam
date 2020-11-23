package edu.osu.cse4471.iam.model;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("USER")
public class User {
    private String shortName;
    private String fullName;
}
