package edu.osu.cse4471.iam.dao;

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
        this.jdbcTemplate.update("INSERT INTO mydb.role VALUES(:name, :description, :owner)", name, description, owner);
    }

    public void deleteRole(String name) {
        this.jdbcTemplate.update("DELETE FROM mydb.role WHERE name=':name'", name);
    }

    public Role getRole(String name) {
        Role role = this.jdbcTemplate.queryForObject("SELECT * FROM mydb.role WHERE NAME = :name", (rs, rownum) -> {
            return new Role(rs.getString("name"), rs.getString("description"), rs.getString("owner"));
        }, name);

        List<User> users = this.jdbcTemplate.query("SELECT * FROM mydb.rules INNER JOIN mydb.user on mydb.rules.user = mydb.user.username WHERE name = :username", (rs, rownum) -> {
            return new User(rs.getString("username"), rs.getString("password"), rs.getString("email"));
        }, name);

        role.setMembers(users);

        return role;
    }

    public void createRule(String user, String role) {
        this.jdbcTemplate.update("INSERT INTO mydb.rules VALUES(:user, :role)", user, role);
    }

    public void deleteRule(String user, String role) {
        this.jdbcTemplate.update("DELETE FROM mydb.rules WHERE user=:user AND role=:role", user, role);
    }

    public boolean checkRule(String user, String role) {
        SqlRowSet rs = this.jdbcTemplate.queryForRowSet("SELECT * FROM mydb.rules WHERE NAME = :user AND ROLE = :rule", new Object[] {user, role});
        return rs.next();
    }


    
}
