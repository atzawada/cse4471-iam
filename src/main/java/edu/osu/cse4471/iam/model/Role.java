package edu.osu.cse4471.iam.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("ROLE")
public class Role {
    @Id
    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("owner")
    private String admin;

    @Query()
    private List<User> members;
}
