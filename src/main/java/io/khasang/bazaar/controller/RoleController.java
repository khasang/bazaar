package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Role;
import io.khasang.bazaar.entity.User;
import io.khasang.bazaar.service.RoleService;
import io.khasang.bazaar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Role getRoleById(@PathVariable(value = "id") String id) {
        return roleService.getRoleById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @RequestMapping(value = "/get/roleid/{roleid}", method = RequestMethod.GET)
    @ResponseBody
    public Role getRolesByRoleId(@PathVariable(value = "roleid") String roleId) {
        return roleService.getRoleByRoleId(roleId);
    }

    @RequestMapping(value = "/get/activeroles", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getActiveRoles() {
        return roleService.getRolesByIsActive(1);
    }

    @RequestMapping(value = "/get/inactiveroles", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getInactiveRoles() {
        return roleService.getRolesByIsActive(0);
    }

    @RequestMapping(value = "/get/rolename/{rolename}", method = RequestMethod.GET)
    @ResponseBody
    public Role getRolesByName(@PathVariable(value = "rolename") String roleName) {
        return roleService.getRoleByName(roleName);
    }

    @RequestMapping(value = "/getusers/{rolename}", method = RequestMethod.GET)
    @ResponseBody
    public Set<User> getUsersByRole(@PathVariable(value = "rolename") String roleName) {
        return userService.findUsersByRole(roleName);
    }
}