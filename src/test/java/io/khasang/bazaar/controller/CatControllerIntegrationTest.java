package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Cat;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
* IntegrationTest for CatController.class
*
*/
public class CatControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/cat";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String GET_ALL = "/all";


    @Test
    public void addCat() {
        Cat cat = createCat();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cat> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                cat.getId());
        Cat receivedCat = responseEntity.getBody();
        assertNotNull(receivedCat);
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertEquals(cat.getName(), receivedCat.getName());
        assertEquals(cat.getDescription(), receivedCat.getDescription());
    }

    @Test
    public void getAllCats() {
        createCat();
        createCat();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Cat>> result = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat>>() {
                }
        );
        List<Cat> catList = result.getBody();
        assertNotNull(result.getBody());
        assertNotNull(catList);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    private Cat createCat() {
        Cat cat = prefillCat();
        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity<Cat> httpEntity = new HttpEntity<>(cat, httpHeaders);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        RestTemplate restTemplate = new RestTemplate();
        Cat createCat = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Cat.class).getBody();
        assertNotNull(createCat);
        assertEquals("barstest", createCat.getName());
        assertEquals("descriptiontest", createCat.getDescription());
        return createCat;
    }

    private Cat prefillCat(){
        Cat cat = new Cat();
        cat.setName("barstest");
        cat.setDescription("descriptiontest");
        return cat;
    }
}
