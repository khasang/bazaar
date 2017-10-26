package io.khasang.bazaar;

import io.khasang.bazaar.entity.Role;
import io.khasang.bazaar.entity.User;
import io.khasang.bazaar.service.RoleService;
import io.khasang.bazaar.service.impl.RoleServiceImpl;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RoleControllerIntegrationTest {
    public RoleService roleService = new RoleServiceImpl();

    private final String ROOT = "http://localhost:8080/roles";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String GET_ALL = "/get/all";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";

    @Test
    public void addRole() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Role role = createRoleTestObj("TESTROLE", 999, 1, -1);
        HttpEntity<Role> httpEntity = new HttpEntity<>(role, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role> roleResponseEntity = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Role.class);
        assertEquals(HttpStatus.OK, roleResponseEntity.getStatusCode());
        assertTrue(roleResponseEntity.hasBody());
        assertNotNull(roleResponseEntity.getBody());
    }

    @Test
    public void getRoleById() {
        Role role = createRoleTestObjViaRest("TESTROLE", 111, 111, 1);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role> roleResponseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Role.class,
                role.getId()
        );
        assertEquals(HttpStatus.OK, roleResponseEntity.getStatusCode());
        assertTrue(roleResponseEntity.hasBody());
        //TODO нужна ли эта проверка после предыдущей?
        assertNotNull(roleResponseEntity.getBody());
    }

    @Test
    public void getAllRoles() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Role>> results = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Role>>() {
                }
        );
        assertEquals(HttpStatus.OK, results.getStatusCode());
        assertTrue(results.hasBody());
    }

    @Test
    public void updateRole() {
        Role role = createRoleTestObjViaRest();
        //TODO использовать RoleService.getRoleById вместо вот этой громоздкой конструкции
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role> roleResponseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Role.class,
                role.getId()
        );
        assertEquals(HttpStatus.OK, roleResponseEntity.getStatusCode());
        assertTrue(roleResponseEntity.hasBody());
        Role receivedRole = roleResponseEntity.getBody();

        receivedRole.setIsActive(-1);//making changes

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Role> httpEntity = new HttpEntity<>(receivedRole, httpHeaders);
        restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Role.class,
                role.getId()
        );

        roleResponseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Role.class,
                role.getId()
        );
        assertEquals(HttpStatus.OK, roleResponseEntity.getStatusCode());
        assertNotNull(receivedRole);
        assertEquals("-1", String.valueOf(receivedRole.getIsActive()));
    }

    private Role createRoleTestObjViaRest(String roleName, Integer roleId, Integer connectionLimit, Integer isActive) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Role role = createRoleTestObj(roleName, roleId, connectionLimit, isActive);
        HttpEntity<Role> httpEntity = new HttpEntity<>(role, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        role = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Role.class).getBody();
        return role;
    }

    private Role createRoleTestObj(String roleName, Integer roleId, Integer connectionLimit, Integer isActive) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleId(roleId);
        role.setConnectionLimit(connectionLimit);
        role.setIsActive(isActive);

        User user1 = new User("testuser1", "123456");
        User user2 = new User("testuser2", "qwerty");
        User user3 = new User("testuser3", "asdfgh");

        Set<User> users = new HashSet<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return role;
    }

}