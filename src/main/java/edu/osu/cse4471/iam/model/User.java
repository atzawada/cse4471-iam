package edu.osu.cse4471.iam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("USER")
public class User {
    @Id
    @Column("username")
    private String username;

    @Column("password") 
    private String password;

}


