package io.khasang.bazaar;

import io.khasang.bazaar.entity.Role;
import io.khasang.bazaar.entity.User;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class RoleControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/roles";
    private final String HOST = "localhost:8080/roles";
    private final String SCHEMA = "http";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String ID = "/{id}";
    private final String ID_PARAM = "?id=";
    private final String ID_FOR_QUERY = "id={id}";
    private final String GET_ALL = "/get/all";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";

    @Test
    public void addRole() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Role role = createRoleTestObj("TESTROLE");
        HttpEntity<Role> httpEntity = new HttpEntity<>(role, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role> roleResponseEntity = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Role.class);
        assertEquals(HttpStatus.OK, roleResponseEntity.getStatusCode());
        assertTrue(roleResponseEntity.hasBody());
        assertEquals(role.getRoleName(), roleResponseEntity.getBody().getRoleName());
    }

    @Test
    public void getRoleById() {
        Role role = createRoleTestObjViaRest("TESTROLE");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role> roleResponseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + ID,
                HttpMethod.GET,
                null,
                Role.class,
                role.getId()
        );
        assertEquals(HttpStatus.OK, roleResponseEntity.getStatusCode());
        assertTrue(roleResponseEntity.hasBody());
        assertEquals(role.getId(), roleResponseEntity.getBody().getId());
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
        Role role = createRoleTestObjViaRest("TESTROLE");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role> roleResponseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + ID,
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
        roleResponseEntity = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Role.class,
                role.getId()
        );
        assertEquals(HttpStatus.OK, roleResponseEntity.getStatusCode());

        roleResponseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + ID,
                HttpMethod.GET,
                null,
                Role.class,
                role.getId()
        );
        assertEquals(HttpStatus.OK, roleResponseEntity.getStatusCode());
        assertNotNull(roleResponseEntity.getBody());
        assertEquals("-1", String.valueOf(roleResponseEntity.getBody().getIsActive()));
    }

    @Test
    public void deleteRole() {
        Role role = createRoleTestObjViaRest("ROLEFORDEL");
        RestTemplate restTemplate = new RestTemplate();
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(SCHEMA).host(HOST).path(DELETE)
                .query(ID_FOR_QUERY).buildAndExpand(role.getId());
        restTemplate.delete(uriComponents.toUriString());

        ResponseEntity<Role> roleResponseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + ID,
                HttpMethod.GET,
                null,
                Role.class,
                role.getId()
        );
        assertEquals(HttpStatus.OK, roleResponseEntity.getStatusCode());
        assertNull(roleResponseEntity.getBody());
    }

    @Test
    public void deleteRoleViaRestTemplate() {
        Role role = createRoleTestObjViaRest("ROLEFORDEL");
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Role> roleResponseEntity = restTemplate.exchange(
                ROOT + DELETE + ID_PARAM + role.getId(),
                HttpMethod.DELETE,
                null,
                Role.class,
                role.getId()
        );
        assertEquals(HttpStatus.OK, roleResponseEntity.getStatusCode());

        roleResponseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + ID,
                HttpMethod.GET,
                null,
                Role.class,
                role.getId()
        );
        assertEquals(HttpStatus.OK, roleResponseEntity.getStatusCode());
        assertFalse(roleResponseEntity.hasBody());
    }

    private Role createRoleTestObjViaRest(String roleName) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Role role = createRoleTestObj(roleName);
        HttpEntity<Role> httpEntity = new HttpEntity<>(role, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        role = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Role.class).getBody();
        return role;
    }

    private Role createRoleTestObj(String roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setConnectionLimit(-1);
        role.setIsActive(1);

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