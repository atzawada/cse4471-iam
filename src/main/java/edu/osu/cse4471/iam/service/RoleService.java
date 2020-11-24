package edu.osu.cse4471.iam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.osu.cse4471.iam.dao.RoleDao;
import edu.osu.cse4471.iam.model.Role;
import edu.osu.cse4471.iam.model.User;

@Component
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role getRole(String roleName) {
        try {
            return roleDao.getRole(roleName);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addRole(String shortname, String roleName) {
        try {
            roleDao.createRule(shortname, roleName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeRole(String shortname, String roleName) {
        try {
            roleDao.deleteRule(shortname, roleName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createRole(String roleName, String roleDescription, String roleOwner) {
        try {
            roleDao.createRole(roleName, roleDescription, roleOwner);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteRole(String roleName) {
        try {
            roleDao.deleteRole(roleName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<User> getGroupMembers(String roleName) {
        try {
            return roleDao.getGroupMembers(roleName);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean checkAccess(String shortname, String roleName) {
        try {
            return roleDao.checkRule(shortname, roleName);
        } catch (Exception e) {
            return false;
        }
    }
}
