package edu.osu.cse4471.iam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/iam")
public class IamController {

    @PostMapping("/createAccount")
    public void createAccount(String shortname, String fullName) {

    }

    @PostMapping("/login")
    public void login(String shortname, String password) {

    }

    @PostMapping("/addRole")
    public void addRole(String shortname, String roleName, String token) {

    }
    
    @PostMapping("/removeRole")
    public void removeRole(String shortname, String roleName, String token) {

    }

    @PostMapping("/createRole")
    public void createRole(String roleName, String description, String token) {

    }

    @PostMapping("/deleteRole")
    public void deleteRole(String roleName, String token) {

    }

    @GetMapping("/authenticate")
    public void authenticate(String shortname, String roleName, String token) {

    }

    @GetMapping("/getMembers")
    public void getGroupMembers(String roleName, String token) {

    }
}
