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
                cat.getId()
        );
        Cat receivedCat = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedCat);
        assertNotNull(receivedCat.getCatWomanList());
        assertEquals("Murka", receivedCat.getCatWomanList().get(1).getName());
        assertEquals(cat.getName(), receivedCat.getName());

    }

    @Test
    public void getAllCats() {
        RestTemplate restTemplate = new RestTemplate();
        createCat();
        createCat();

        ResponseEntity<List<Cat>> result = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat>>() {
                }
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    private Cat createCat() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat();
        HttpEntity<Cat> httpEntity = new HttpEntity<>(cat, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        Cat createdCat = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Cat.class).getBody();
        assertNotNull(createdCat);
        assertEquals("Barsik", createdCat.getName());
        assertEquals("sleepy", createdCat.getDescription());
        assertNotNull(createdCat.getId());

        return createdCat;
    }

    private Cat prefillCat() {
        Cat cat = new Cat();
        cat.setName("Barsik");
        cat.setDescription("sleepy");

        CatWoman catWoman1 = new CatWoman();
        catWoman1.setName("Riska");

        CatWoman catWoman2 = new CatWoman();
        catWoman2.setName("Murka");

        List<CatWoman> list = new ArrayList<>();
        list.add(catWoman1);
        list.add(catWoman2);
        cat.setCatWomanList(list);

        return cat;
    }
}
