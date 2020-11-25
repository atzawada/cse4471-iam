package edu.osu.cse4471.iam.controller;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.github.lambdaexpression.annotation.RequestBodyParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    Pattern kebabCase = Pattern.compile("^[a-z]+(-[a-z]+)*$");
    Pattern usernameCase = Pattern.compile("[a-z]+[0-9]+");

    @PostMapping("/createAccount")
    public void createAccount(@RequestBodyParam String shortname, @RequestBodyParam String password, @RequestBodyParam String fullName, @RequestBodyParam String email, HttpServletResponse response) throws IOException {
        if (shortname.length() < 6) {
            LOG.info("Shortname is < 6 characters: " + shortname);
            response.sendError(400, "Shortname must be at least 6 characters.");

            return;
        }

        if (password.length() < 6) {
            LOG.info("Password is < 6 characters: " + password);
            response.sendError(400, "Password must be at least 6 characters.");

            return;
        }

        if (email.length() < 0) {
            LOG.info("Email is 0 characters.");
            response.sendError(400, "Email must be at least 1 character.");

            return;
        }

        Matcher m = usernameCase.matcher(shortname);
        if (!m.matches()) {
            LOG.info("Shortname does not match case constraints: " + shortname);
            response.sendError(400, "Shortname must be lowercase letters followed by numbers.");
            
            return;
        }

        boolean status = userService.createAccount(fullName, shortname, password, email);

        if (status) {
            response.setStatus(200);
        } else {
            LOG.info("User already exists: " + shortname);
            response.sendError(400, "Shortname already exists");
        }
    }

    @PostMapping("/addRole")
    public void addRole(@RequestBodyParam String shortname, @RequestBodyParam String roleName, @RequestBodyParam String username, @RequestBodyParam String password, HttpServletResponse response) {
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
    public void removeRole(@RequestBodyParam String shortname, @RequestBodyParam String roleName, @RequestBodyParam String username, @RequestBodyParam String password, HttpServletResponse response) {
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
    public void createRole(@RequestBodyParam String roleName, @RequestBodyParam String description, @RequestBodyParam String shortname, @RequestBodyParam String password, HttpServletResponse response) throws IOException {
        User requestor = userService.authenticate(shortname, password);

        if (requestor == null) {
            LOG.info("Login request for " + shortname + " is invalid.");
            response.setStatus(401);
            return;
        }

        Matcher m = kebabCase.matcher(roleName);

        if (roleName.length() < 0) {
            LOG.info("roleName is 0 characters.");
            response.sendError(400, "Role name must be at least 1 character.");

            return;
        }

        if (!m.matches()) {
            LOG.info("Role name does not follow kebab-case: " + roleName);
            response.sendError(400, "Role name does not follow kebab-case.");
            
            return;
        }

        boolean status = roleService.createRole(roleName, description, shortname);

        if (status) {
            response.setStatus(200);
        } else {
            LOG.info("Unable to create role: " + roleName);
            response.sendError(400, "Unable to create role");
        }
    }

    @PostMapping("/deleteRole")
    public void deleteRole(@RequestBodyParam String roleName, @RequestBodyParam String shortname, @RequestBodyParam String password, HttpServletResponse response) throws IOException {
        boolean isAllowed = checkModifyRoleStatus(shortname, password, roleName, response);
        
        if (!isAllowed) {
            return;
        }

        boolean status = roleService.deleteRole(roleName);

        if (status) {
            response.setStatus(200);
        } else {
            LOG.info("Unable to delete role: " + roleName);
            response.sendError(400, "Unable to delete role");
        }
    }

    @GetMapping("/authenticate")
    public boolean authenticate(@RequestBodyParam String shortname, @RequestBodyParam String roleName, @RequestBodyParam String username, @RequestBodyParam String password, HttpServletResponse response) {
        return false;
    }

    @GetMapping("/getMembers")
    public void getGroupMembers(@RequestBodyParam String roleName, @RequestBodyParam String username, @RequestBodyParam String password, HttpServletResponse response) {

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
