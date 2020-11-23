package edu.osu.cse4471.iam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/iam")
public class IamController {

    @PostMapping("/createAccount")
    @ResponseBody
    public void createAccount(@RequestParam String shortname, @RequestParam String fullName) {

    }

    @PostMapping("/login")
    @ResponseBody
    public void login(@RequestParam String shortname, @RequestParam String password) {

    }

    @PostMapping("/addRole")
    @ResponseBody
    public void addRole(@RequestParam String shortname, @RequestParam String roleName, @RequestParam String token) {

    }
    
    @PostMapping("/removeRole")
    @ResponseBody
    public void removeRole(@RequestParam String shortname, @RequestParam String roleName, @RequestParam String token) {

    }

    @PostMapping("/createRole")
    @ResponseBody
    public void createRole(@RequestParam String roleName, @RequestParam String description, @RequestParam String token) {

    }

    @PostMapping("/deleteRole")
    @ResponseBody
    public void deleteRole(@RequestParam String roleName, @RequestParam String token) {

    }

    @GetMapping("/authenticate")
    @ResponseBody
    public void authenticate(@RequestParam String shortname, @RequestParam String roleName, @RequestParam String token) {

    }

    @GetMapping("/getMembers")
    @ResponseBody
    public void getGroupMembers(@RequestParam String roleName, @RequestParam String token) {

    }
}
