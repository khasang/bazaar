package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Role;
import io.khasang.bazaar.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Role getRoleById(@PathVariable(value = "id") String id) {
        return roleService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getAllRoles() {
        return roleService.getRoleList();
    }

    @RequestMapping(value = "/get/roleid/{roleid}", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getRolesByRoleId(@PathVariable(value = "roleid") String roleId) {
        return roleService.getRolesByRoleId(roleId);
    }

    @RequestMapping(value = "/get/isactive/{isactive}", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getRolesByIsActive(@PathVariable(value = "isactive") String isActive) {
        return roleService.getRolesByIsActive(Boolean.parseBoolean(isActive));
    }

}