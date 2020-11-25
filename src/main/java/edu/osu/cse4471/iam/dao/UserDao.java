package edu.osu.cse4471.iam.dao;

import java.sql.PreparedStatement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import edu.osu.cse4471.iam.model.User;

@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(String username, String password, String email) {
        this.jdbcTemplate.update("INSERT INTO iam.user VALUES(?, ?, ?)", username, email, password);
    }

    public User getUser(String username) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM iam.user WHERE username = ?", (rs, rownum) -> {
            return new User(rs.getString("username"), rs.getString("password"), rs.getString("email"));
        }, username);
    }

    public User authenticate(String username, String password) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM iam.user WHERE username = ? AND password = ?", (rs, rownum) -> {
            return new User(rs.getString("username"), rs.getString("password"), rs.getString("email"));
        }, username, password);
    }
}
