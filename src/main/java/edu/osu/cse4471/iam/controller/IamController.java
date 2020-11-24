package edu.osu.cse4471.iam.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.osu.cse4471.iam.model.Role;
import edu.osu.cse4471.iam.model.User;
import edu.osu.cse4471.iam.service.RoleService;
import edu.osu.cse4471.iam.service.UserService;

@RestController
@RequestMapping("iam")
public class IamController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @PostMapping("/createAccount")
    public void createAccount(@RequestParam String shortname, @RequestParam String fullName, HttpServletResponse response) {
        
    }

    @PostMapping("/login")
    public void login(@RequestParam String shortname, @RequestParam String password, HttpServletResponse response) {

    }

    @PostMapping("/addRole")
    public void addRole(@RequestParam String shortname, @RequestParam String roleName, @RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        boolean isAllowed = checkModifyRoleStatus(username, password, roleName, response);
        
        if (!isAllowed) {
            return;
        }

        boolean status = roleService.addRole(shortname, roleName);

        if (status) {
            response.setStatus(200);
        } else {
            response.setStatus(400);
        }
    }
    
    @PostMapping("/removeRole")
    public void removeRole(@RequestParam String shortname, @RequestParam String roleName, @RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        boolean isAllowed = checkModifyRoleStatus(username, password, roleName, response);
        
        if (!isAllowed) {
            return;
        }

        boolean status = roleService.removeRole(shortname, roleName);

        if (status) {
            response.setStatus(200);
        } else {
            response.setStatus(400);
        }
    }

    @PostMapping("/createRole")
    public void createRole(@RequestParam String roleName, @RequestParam String description, @RequestParam String username, @RequestParam String password) {

    }

    @PostMapping("/deleteRole")
    public void deleteRole(@RequestParam String roleName, @RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        boolean isAllowed = checkModifyRoleStatus(username, password, roleName, response);
        
        if (!isAllowed) {
            return;
        }

        boolean status = roleService.deleteRole(roleName);

        if (status) {
            response.setStatus(200);
        } else {
            response.setStatus(400);
        }
    }

    @GetMapping("/authenticate")
    public boolean authenticate(@RequestParam String shortname, @RequestParam String roleName, @RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        return false;
    }

    @GetMapping("/getMembers")
    public void getGroupMembers(@RequestParam String roleName, @RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        
    }

    private boolean checkModifyRoleStatus(String username, String password, String roleName, HttpServletResponse response) {
        User requestor = userService.login(username, password);

        if (requestor == null) {
            response.setStatus(401);
            return false;
        }

        Role role = roleService.getRole(roleName);
        
        if (role == null) {
            response.setStatus(400);
            return false;
        }

        if (!role.getAdmin().getShortName().equals(requestor.getShortName())) {
            response.setStatus(403);
            return false;
        }

        return true;
    }
}
