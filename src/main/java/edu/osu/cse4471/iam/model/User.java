package edu.osu.cse4471.iam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("USER")
public class User {
    private String username;
    private String email;
    private String password;
    private String create_time;



    @Id
    @Column("username") 
    public String getUsername(){
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Column("password") 
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column("email") 
    public String getEmail(){
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column("create_time") 
    public String getCreate_time(){
        return create_time;
    }
    public void setCreate_time(String crearte_time) {
        this.create_time = crearte_time;
    }
}


