package edu.osu.cse4471.iam.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RoleDao {
    private final  JdbcTemplate jdbcTemplate;

    public RoleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createRole(String name, String description, String owner) {
        this.jdbcTemplate.update("INSERT INTO mydb.role VALUES(:name, :description, :owner)", name, description, owner);
    }

    
}
