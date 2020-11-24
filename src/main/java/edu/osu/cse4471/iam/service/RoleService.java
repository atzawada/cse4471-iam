package edu.osu.cse4471.iam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.osu.cse4471.iam.dao.RoleDao;
import edu.osu.cse4471.iam.model.Role;

@Component
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role getRole(String roleName) {
        return null;
    }

    public boolean addRole(String shortname, String roleName) {
        return true;
    }

    public boolean removeRole(String shortname, String roleName) {
        return true;
    }

    public boolean createRole(String roleName, String roleDescription, String roleOwner) {
        return true;
    }

    public boolean deleteRole(String roleName) {
        return true;
    }

    public boolean getGroupMembers(String roleName) {
        return true;
    }

    public boolean checkAccess(String shortname, String roleName) {
        return false;
    }
}
