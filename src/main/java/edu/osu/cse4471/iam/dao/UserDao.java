package edu.osu.cse4471.iam.dao;

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
        this.jdbcTemplate.update("INSERT INTO mydb.user VALUES(:username, :email, :password)", username, email, password);
    }

    public User getUser(String username) {
        this.jdbcTemplate.queryForObject("SELECT * FROM mydb.user WHERE USERNAME = :username", (rs, rownum) -> {
            return new User();
        }, username);
        return null;
    }

    public User authenticate(String username, String password) {
        return null;
    }
}
