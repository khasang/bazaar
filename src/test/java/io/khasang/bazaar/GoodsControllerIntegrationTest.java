package io.khasang.bazaar;

import io.khasang.bazaar.entity.Goods;
import io.khasang.bazaar.entity.GoodsCategory;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration tests for GoodsController.
 *
 * @author Zulfia Garifullina
 * @date 04.10.2017.
 */
public class GoodsControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/goods";
    private final String ADD = "/add";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";
    private final String GET_BY_ID = "/get/id";
    private final String GET_BY_NAME = "/get/name";
    private final String GET_ALL = "/all";
    private final String RESERVE = "/reserve";
    private final String UNRESERVE = "/unreserve";
    private final String BUY = "/buy";
    private GoodsCategory category = (new RestTemplate()).exchange(
            ROOT + GET_BY_ID + "/{id}",
            HttpMethod.GET,
            null,
            GoodsCategory.class,
            5
    ).getBody();

    @Test
    public void addGoods() {
        Goods goods = createGoods();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Goods> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Goods.class,
                goods.getId()
        );

        Goods receivedGoods = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedGoods);
        assertEquals(goods.getName(), receivedGoods.getName());
        assertEquals(goods.getDescription(), receivedGoods.getDescription());
    }

    @Test
    public void updateGoods() {
        Goods goods = changeGoods();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Goods> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Goods.class,
                goods.getId()
        );

        Goods receivedGoods = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedGoods);
        assertNotNull(receivedGoods.getCategory());
        assertEquals(goods.getName(), receivedGoods.getName());
        assertEquals(goods.getDescription(), receivedGoods.getDescription());
        assertEquals(goods.getCategory(), receivedGoods.getCategory());
        assertEquals(goods.getPrice(), receivedGoods.getPrice());
        assertEquals(goods.getQuantityInStock(), receivedGoods.getQuantityInStock());
        assertEquals(goods.getQuantityReserved(), receivedGoods.getQuantityReserved());
    }

    @Test
    public void deleteGoods() {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + DELETE)
                .queryParam("id", "6");

        restTemplate.delete(builder.build().encode().toUri());

        ResponseEntity<Goods> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Goods.class,
                6
        );

        Goods receivedGoods = responseEntity.getBody();
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(receivedGoods);
    }

    @Test
    public void getGoodsByName() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Goods>> result = restTemplate.exchange(
                ROOT + GET_BY_NAME + "/{name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Goods>>() {
                },
                "Harry Potter"
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void getAllGoodsCategories() {
        RestTemplate restTemplate = new RestTemplate();
        createGoods();
        createGoods();

        ResponseEntity<List<Goods>> result = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Goods>>() {
                }
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void reserveGoods() {
        Goods goods = reserve();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Goods> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Goods.class,
                goods.getId()
        );

        Goods receivedGoods = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedGoods);
        assertEquals(goods.getName(), receivedGoods.getName());
        assertEquals(goods.getDescription(), receivedGoods.getDescription());
        assertEquals(goods.getCategory(), receivedGoods.getCategory());
        assertEquals(goods.getPrice(), receivedGoods.getPrice());
        assertEquals(goods.getQuantityInStock(), receivedGoods.getQuantityInStock());
        assertEquals(goods.getQuantityReserved(), receivedGoods.getQuantityReserved());
    }

    @Test
    public void unreserveGoods() {

    }

    @Test
    public void buyGoods() {

    }

    private Goods createGoods() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Goods goods = prefillGoods();
        HttpEntity<Goods> httpEntity = new HttpEntity<>(goods, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        Goods createdGoods = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Goods.class).getBody();
        assertNotNull(createdGoods);
        assertEquals("iPhone X", createdGoods.getName());
        assertEquals("Latest iPhone model", createdGoods.getDescription());
        assertEquals(category, createdGoods.getCategory());
        assertEquals(new Integer(100000), createdGoods.getPrice());
        assertEquals(new Integer(20), createdGoods.getQuantityInStock());
        assertEquals(new Integer(0), createdGoods.getQuantityReserved());
        assertNotNull(createdGoods.getId());

        return createdGoods;
    }

    private Goods prefillGoods() {
        Goods goods = new Goods();
        goods.setName("iPhone X");
        goods.setDescription("Latest iPhone model");
        goods.setCategory(category);
        goods.setPrice(100000);
        goods.setQuantityInStock(20);
        goods.setQuantityReserved(0);
        return goods;
    }

    private Goods changeGoods() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Goods> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Goods.class,
                6
        );

        Goods changingGoods = responseEntity.getBody();
        changingGoods.setName("Samsung Galaxy S8");
        changingGoods.setDescription("Closest rival of iPhone line");
        changingGoods.setPrice(80000);
        changingGoods.setQuantityInStock(400);
        changingGoods.setQuantityReserved(150);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Goods> httpEntity = new HttpEntity<>(changingGoods, httpHeaders);

        Goods changedGoods = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Goods.class).getBody();
        assertNotNull(changedGoods);
        assertEquals("Samsung Galaxy S8", changedGoods.getName());
        assertEquals("Closest rival of iPhone line", changedGoods.getDescription());
        assertEquals(new Integer(80000), changedGoods.getPrice());
        assertEquals(new Integer(400), changedGoods.getQuantityInStock());
        assertEquals(new Integer(150), changedGoods.getQuantityReserved());
        assertNotNull(changedGoods.getId());

        return changedGoods;
    }

    private Goods reserve() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Goods> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Goods.class,
                6
        );

        Goods changingGoods = responseEntity.getBody();
        changingGoods.setName("Samsung Galaxy S8");
        changingGoods.setDescription("Closest rival of iPhone line");
        changingGoods.setPrice(80000);
        changingGoods.setQuantityInStock(400);
        changingGoods.setQuantityReserved(150);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Goods> httpEntity = new HttpEntity<>(changingGoods, httpHeaders);

        Goods changedGoods = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Goods.class).getBody();
        assertNotNull(changedGoods);
        assertEquals("Samsung Galaxy S8", changedGoods.getName());
        assertEquals("Closest rival of iPhone line", changedGoods.getDescription());
        assertEquals(new Integer(80000), changedGoods.getPrice());
        assertEquals(new Integer(400), changedGoods.getQuantityInStock());
        assertEquals(new Integer(150), changedGoods.getQuantityReserved());
        assertNotNull(changedGoods.getId());

        return reservedGoods;
    }
}
