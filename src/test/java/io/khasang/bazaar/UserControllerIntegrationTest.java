package io.khasang.bazaar;

import io.khasang.bazaar.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/users";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String GET_ALL = "/all";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";

    @Test
    public void addUser() {
        User user = createUser();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                User.class,
                user.getId()
        );
        User receivedUser = responseEntity.getBody();
        Assert.assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedUser);
        assertEquals(user.getFirstName(), receivedUser.getFirstName());
        assertEquals(user.getMiddleName(), receivedUser.getMiddleName());
        assertEquals(user.getLastName(), receivedUser.getLastName());

    }

    @Test
    public void getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        createUser();
        createUser();

        ResponseEntity<List<User>> result = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void updateUser() {
        User user = createUser();
        user.setFirstName("Andrie");
        user.setMiddleName("Andreevich");
        user.setLastName("Endreev");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        User changedUser = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                User.class).getBody();
        assertNotNull(changedUser);
        assertEquals("Andrie", changedUser.getFirstName());
        assertEquals("Andreevich", changedUser.getMiddleName());
        assertEquals("Endreev", changedUser.getLastName());
        assertNotNull(changedUser.getId());
    }
    @Test
    public void deleteUser(){
        User user = createUser();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + DELETE).
                queryParam("id", user.getId());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(builder.build().encode().toUri());

        ResponseEntity<User> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                User.class,
                user.getId()
        );

        User receivedUser = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(receivedUser);
    }

    private User createUser() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        User user = prefillUser();
        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        User createdUser = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                User.class).getBody();
        assertNotNull(createdUser);
        assertEquals("Андрей", createdUser.getFirstName());
        assertEquals("Андреевич", createdUser.getMiddleName());
        assertEquals("Андреев", createdUser.getLastName());
        assertNotNull(createdUser.getId());

        return createdUser;
    }

    private User prefillUser() {
        User user = new User();
        user.setFirstName("Андрей");
        user.setMiddleName("Андреевич");
        user.setLastName("Андреев");
        user.setLogin("andrei");
        user.setPassword("123");
        user.setGender(new Integer(2));

        user.setPostIdnex("123456");
        user.setCity("Пермь");
        user.setStreet("Гагарина");
        user.setHouse(new Integer(4));
        user.setRoom(new Integer(7));
        user.setPhone("9001234567");
        user.setEmail("andrei@mail.ru");
        user.setIdRole(new Long(2));

        return user;
    }


}
