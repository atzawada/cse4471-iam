package edu.osu.cse4471.iam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("iam")
public class IamController {

    @PostMapping("/createAccount")
    public @ResponseBody void createAccount(@RequestParam String shortname, @RequestParam String fullName) {

    }

    @PostMapping("/login")
    public @ResponseBody void login(@RequestParam String shortname, @RequestParam String password) {

    }

    @PostMapping("/addRole")
    public @ResponseBody void addRole(@RequestParam String shortname, @RequestParam String roleName, @RequestParam String token) {

    }
    
    @PostMapping("/removeRole")
    public @ResponseBody void removeRole(@RequestParam String shortname, @RequestParam String roleName, @RequestParam String token) {

    }

    @PostMapping("/createRole")
    public @ResponseBody void createRole(@RequestParam String roleName, @RequestParam String description, @RequestParam String token) {

    }

    @PostMapping("/deleteRole")
    public @ResponseBody void deleteRole(@RequestParam String roleName, @RequestParam String token) {

    }

    @GetMapping("/authenticate")
    public @ResponseBody void authenticate(@RequestParam String shortname, @RequestParam String roleName, @RequestParam String token) {

    }

    @GetMapping("/getMembers")
    public @ResponseBody void getGroupMembers(@RequestParam String roleName, @RequestParam String token) {

    }
}
