package io.khasang.bazaar;

import io.khasang.bazaar.entity.Cat;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CatControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/cat";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String GET_ALL = "/all";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";
    private final String GET_BY_NAME = "/get/name";

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

        Cat cat = prefillCat("Barsik","sleepy");
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

    private Cat prefillCat(String name, String description) {
        Cat cat = new Cat();
        cat.setName(name);
        cat.setDescription(description);
        return cat;
    }

    /**
     * Написать тесты на update, delete, getName
     */
    @Test
    public void updateCat() {
        Cat cat = createCat();
        Cat changedCat = changeCat(cat);

        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<Cat> responseEntity = restTemplate2.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                cat.getId()
        );
        Cat receivedCat = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedCat);
        assertEquals(changedCat.getName(), receivedCat.getName());
        assertEquals(changedCat.getDescription(), receivedCat.getDescription());

    }

    private Cat changeCat(Cat cat) {
        cat.setName("Liska");
        cat.setDescription("playful");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Cat> httpEntity = new HttpEntity<>(cat, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        Cat changedCat = restTemplate.exchange(ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Cat.class).getBody();
        assertNotNull(changedCat);
        assertEquals("Liska", changedCat.getName());
        assertEquals("playful", changedCat.getDescription());
        assertNotNull(changedCat.getId());

        return changedCat;

    }

    @Test
    public void deleteCat(){
        Cat cat = createCat();
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + DELETE)
                .queryParam("id", cat.getId());

        restTemplate.delete(builder.build().encode().toUri());

        ResponseEntity<Cat> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                cat.getId()
        );

        Cat receivedCat = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(receivedCat);
    }

    @Test
    public void getName(){
        Cat cat = createCat();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Cat>> result = restTemplate.exchange(
                ROOT + GET_BY_NAME + "/{name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat>>() {
                },
                cat.getName()
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }
//    private Goods changeGoods(Goods goods) {
//        goods.setName("Snowboard");
//        goods.setDescription("All-mountain snowboard");
//        goods.setPrice(8000);
//        goods.setQuantityInStock(400);
//        goods.setQuantityReserved(150);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
//
//        HttpEntity<Goods> httpEntity = new HttpEntity<>(goods, httpHeaders);
//
//        RestTemplate restTemplate = new RestTemplate();
//        Goods changedGoods = restTemplate.exchange(
//                ROOT + UPDATE,
//                HttpMethod.POST,
//                httpEntity,
//                Goods.class).getBody();
//        assertNotNull(changedGoods);
//        assertEquals("Snowboard", changedGoods.getName());
//        assertEquals("All-mountain snowboard", changedGoods.getDescription());
//        assertEquals(new Integer(8000), changedGoods.getPrice());
//        assertEquals(new Integer(400), changedGoods.getQuantityInStock());
//        assertEquals(new Integer(150), changedGoods.getQuantityReserved());
//        assertNotNull(changedGoods.getId());
//
//        return changedGoods;

}
