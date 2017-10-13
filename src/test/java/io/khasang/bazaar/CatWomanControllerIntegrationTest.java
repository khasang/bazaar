package io.khasang.bazaar;

import io.khasang.bazaar.entity.Cat;
import io.khasang.bazaar.entity.CatWoman;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CatWomanControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/cat_woman";
    private final String ROOT_CAT = "http://localhost:8080/cat";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String GET_ALL = "/all";

    private final CatControllerIntegrationTest forCat = new CatControllerIntegrationTest();

    @Test
    public void addCat() {
        CatWoman catWoman = createCatWoman("Murka");

        RestTemplate restTemplate1 = new RestTemplate();
        ResponseEntity<Cat> responseEntity1 = restTemplate1.exchange(
                ROOT_CAT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                5
        );
        Cat receivedCat = responseEntity1.getBody();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CatWoman> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                CatWoman.class,
                catWoman.getId()
        );
        CatWoman receivedCatWoman = responseEntity.getBody();
        assertEquals("Murka", catWoman.getName());
//        assertEquals("Murka", receivedCatWoman.getCat().getCatWoman().get(0).getName());
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedCatWoman);
        assertNotNull(receivedCatWoman.getCat());
        assertEquals("Barsik", restTemplate.exchange(
                ROOT_CAT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                receivedCatWoman.getCat().getId()
        ).getBody().getName());
        assertEquals("Barsik", receivedCatWoman.getCat().getName());
        assertEquals(catWoman.getName(), receivedCatWoman.getName());

    }

    @Test
    public void getAllCats() {
        RestTemplate restTemplate = new RestTemplate();
        createCatWoman("Murka");
        createCatWoman("Riska");

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

    private CatWoman createCatWoman(String name) {
        Cat cat = forCat.createCat();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        CatWoman catWoman = prefillCatWoman(cat, name);
        HttpEntity<CatWoman> httpEntity = new HttpEntity<>(catWoman, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        CatWoman createdCatWoman = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                CatWoman.class).getBody();
        assertNotNull(createdCatWoman);
        assertNotNull(createdCatWoman.getId());

        return createdCatWoman;
    }

    private CatWoman prefillCatWoman(Cat cat, String name) {
        CatWoman catWoman1 = new CatWoman();
        catWoman1.setName(name);
        catWoman1.setCat(cat);
        return catWoman1;
    }
}