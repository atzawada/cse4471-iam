package edu.osu.cse4471.iam.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import edu.osu.cse4471.iam.model.Role;
import edu.osu.cse4471.iam.model.User;
 

@Component
public class RoleDao {
    private final  JdbcTemplate jdbcTemplate;

    public RoleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createRole(String name, String description, String owner) {
        this.jdbcTemplate.update("INSERT INTO iam.role VALUES(?, ?, ?)", name, description, owner);
    }

    public void deleteRole(String name) {
        this.jdbcTemplate.update("DELETE FROM iam.role WHERE name=?", name);
    }

    public Role getRole(String name) {
        Role role = this.jdbcTemplate.queryForObject("SELECT * FROM iam.role WHERE NAME = ?", (rs, rownum) -> {
            return new Role(rs.getString("name"), rs.getString("description"), rs.getString("owner"));
        }, name);

        role.setMembers(this.getGroupMembers(name));

        return role;
    }

    public void createRule(String user, String role) {
        this.jdbcTemplate.update("INSERT INTO iam.rules VALUES(?, ?)", user, role);
    }

    public void deleteRule(String user, String role) {
        this.jdbcTemplate.update("DELETE FROM iam.rules WHERE user=? AND role=?", user, role);
    }

    public boolean checkRule(String user, String role) {
        SqlRowSet rs = this.jdbcTemplate.queryForRowSet("SELECT * FROM iam.rules WHERE NAME = ? AND ROLE = ?", new Object[] {user, role});
        return rs.next();
    }

    public List<User> getGroupMembers(String roleName) {
        return this.jdbcTemplate.query("SELECT * FROM iam.rules INNER JOIN iam.user on iam.rules.user = iam.user.username WHERE iam.rules.role = ?", (rs, rownum) -> {
            return new User(rs.getString("username"), rs.getString("password"), rs.getString("email"));
        }, roleName);
    }


    
}
