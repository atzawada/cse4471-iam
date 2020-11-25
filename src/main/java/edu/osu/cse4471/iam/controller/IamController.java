package edu.osu.cse4471.iam.controller;

import javax.servlet.http.HttpServletResponse;

import com.github.lambdaexpression.annotation.RequestBodyParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static Log LOG = LogFactory.getLog(IamController.class);

    @PostMapping("/createAccount")
    public void createAccount(@RequestBodyParam String shortname, @RequestBodyParam String password, @RequestBodyParam String fullName, @RequestBodyParam String email, HttpServletResponse response) {
        boolean status = userService.createAccount(fullName, shortname, password, email);

        if (status) {
            response.setStatus(200);
        } else {
            response.setStatus(400);
        }
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
        User requestor = userService.authenticate(username, password);

        if (requestor == null) {
            LOG.info("Login request for " + username + " is invalid.");
            response.setStatus(401);
            return false;
        }

        Role role = roleService.getRole(roleName);
        
        if (role == null) {
            LOG.info("Role request for " + roleName + " is invalid.");
            response.setStatus(400);
            return false;
        }

        if (!role.getAdmin().equals(requestor.getUsername())) {
            LOG.info("Requestor " + requestor.getUsername() + " is not admin for role " + role.getAdmin() + ".");
            response.setStatus(403);
            return false;
        }

        return true;
    }
}
