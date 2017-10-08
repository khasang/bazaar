package io.khasang.bazaar;

import io.khasang.bazaar.entity.Cat;
import io.khasang.bazaar.entity.CatWoman;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CatWomanControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/cat_woman";
    private final String ROOT_CAT = "http://localhost:8080/cat";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String GET_ALL = "/all";

    @Test
    public void addCat() {
        CatWoman catWoman = createCatWoman();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CatWoman> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                CatWoman.class,
                catWoman.getId()
        );
        CatWoman receivedCat = responseEntity.getBody();
        assertEquals("Murka", receivedCat.getCat().getCatWomanList().get(0).getName());
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedCat);
        assertNotNull(receivedCat.getCat());
        assertEquals("Barsik", restTemplate.exchange(
                ROOT_CAT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                receivedCat.getCat().getId()
        ).getBody().getName());
        assertEquals("Barsik", receivedCat.getCat().getName());
        assertEquals(catWoman.getName(), receivedCat.getName());

    }

    @Test
    public void getAllCats() {
        RestTemplate restTemplate = new RestTemplate();
        createCatWoman();
        createCatWoman();

        ResponseEntity<List<CatWoman>> result = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CatWoman>>() {
                }
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    private CatWoman createCatWoman() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        CatWoman catWoman = prefillCatWoman();
        HttpEntity<CatWoman> httpEntity = new HttpEntity<>(catWoman, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        CatWoman createdCatWoman = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                CatWoman.class).getBody();
        assertNotNull(createdCatWoman);
        assertEquals("MurkaManyToOne", createdCatWoman.getName());
        assertNotNull(createdCatWoman.getId());

        return createdCatWoman;
    }

    private CatWoman prefillCatWoman() {
        Cat cat = new Cat();
        cat.setName("Barsik");
        cat.setDescription("ManyToOne");

        CatWoman catWoman = new CatWoman();
        catWoman.setName("MurkaManyToOne");
        catWoman.setCat(cat);

        return catWoman;
    }
}