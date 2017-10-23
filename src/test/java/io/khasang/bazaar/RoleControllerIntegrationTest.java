package io.khasang.bazaar;

import io.khasang.bazaar.entity.Role;
import io.khasang.bazaar.entity.User;
import io.khasang.bazaar.service.RoleService;
import io.khasang.bazaar.service.impl.RoleServiceImpl;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoleControllerIntegrationTest {
    public RoleService roleService = new RoleServiceImpl();

    private final String ROOT = "http://localhost:8080/roles";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String GET_ALL = "/get/all";

    @Test
    public void addRole() {
        //Role role = createRoleObjViaRest();
        Role role = createRoleTestObj();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role> roleResponseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Role.class,
                role.getId()
        );
        Role receivedRole = roleResponseEntity.getBody();
        assertEquals(HttpStatus.OK, roleResponseEntity.getStatusCode());
        assertNotNull(receivedRole);
        //assertEquals("ADMIN", receivedRole.getRoleName());
    }

    @Test
    public void createRoleObjViaRest() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Role role1 = createRoleTestObj();
        HttpEntity<Role> httpEntity = new HttpEntity<>(role1, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        Role role2 = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Role.class).getBody();
        assertNotNull(role2);
        assertEquals("TESTROLE", role2.getRoleName());
        //assertEquals("999", role2.getRoleId().toString());
        assertNotNull(role2.getId());

        //return role2;
    }

    private Role createRoleTestObj() {
        Role role = new Role();
        role.setRoleName("TESTROLE");
        role.setRoleId(999);
        role.setConnectionLimit(-1);
        role.setIsActive(1);

        User user1 = new User("testuser1", "123456");
        User user2 = new User("testuser2", "qwerty");

        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);

        return role;
    }

}
