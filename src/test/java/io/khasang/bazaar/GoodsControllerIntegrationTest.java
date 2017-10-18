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
        assertEquals(goods.getCategory(), receivedGoods.getCategory());
        assertEquals(goods.getSellerLogin(), receivedGoods.getSellerLogin());
        assertEquals(goods.getPrice(), receivedGoods.getPrice());
        assertEquals(goods.getQuantityInStock(), receivedGoods.getQuantityInStock());
        assertEquals(goods.getQuantityReserved(), receivedGoods.getQuantityReserved());
    }

    @Test
    public void updateGoods() {
        Goods goods = createGoods();
        Goods changedGoods = changeGoods(goods);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Goods> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Goods.class,
                changedGoods.getId()
        );

        Goods receivedGoods = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedGoods);
        assertNotNull(receivedGoods.getCategory());
        assertEquals(changedGoods.getName(), receivedGoods.getName());
        assertEquals(changedGoods.getDescription(), receivedGoods.getDescription());
        assertEquals(changedGoods.getCategory(), receivedGoods.getCategory());
        assertEquals(changedGoods.getSellerLogin(), receivedGoods.getSellerLogin());
        assertEquals(changedGoods.getPrice(), receivedGoods.getPrice());
        assertEquals(changedGoods.getQuantityInStock(), receivedGoods.getQuantityInStock());
        assertEquals(changedGoods.getQuantityReserved(), receivedGoods.getQuantityReserved());
    }

    @Test
    public void deleteGoods() {
        Goods goods = createGoods();
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + DELETE)
                .queryParam("id", goods.getId());

        restTemplate.delete(builder.build().encode().toUri());

        ResponseEntity<Goods> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Goods.class,
                goods.getId()
        );

        Goods receivedGoods = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(receivedGoods);
    }

    @Test
    public void getGoodsByName() {
        Goods goods = createGoods();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Goods>> result = restTemplate.exchange(
                ROOT + GET_BY_NAME + "/{name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Goods>>() {
                },
                goods.getName()
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void getAllGoods() {
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
        Goods goods = createGoods();
        Goods reservedGoods = reserve(goods);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Goods> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Goods.class,
                reservedGoods.getId()
        );

        Goods receivedGoods = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedGoods);
        assertEquals(reservedGoods.getName(), receivedGoods.getName());
        assertEquals(reservedGoods.getDescription(), receivedGoods.getDescription());
        assertEquals(reservedGoods.getCategory(), receivedGoods.getCategory());
        assertEquals(reservedGoods.getSellerLogin(), receivedGoods.getSellerLogin());
        assertEquals(reservedGoods.getPrice(), receivedGoods.getPrice());
        assertEquals(reservedGoods.getQuantityInStock(), receivedGoods.getQuantityInStock());
        assertEquals(reservedGoods.getQuantityReserved(), receivedGoods.getQuantityReserved());
    }

    @Test
    public void unreserveGoods() {
        Goods goods = createGoods();
        Goods reservedGoods = reserve(goods);
        Goods unreservedGoods = unreserve(reservedGoods);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Goods> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Goods.class,
                unreservedGoods.getId()
        );

        Goods receivedGoods = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedGoods);
        assertEquals(unreservedGoods.getName(), receivedGoods.getName());
        assertEquals(unreservedGoods.getDescription(), receivedGoods.getDescription());
        assertEquals(unreservedGoods.getCategory(), receivedGoods.getCategory());
        assertEquals(unreservedGoods.getSellerLogin(), receivedGoods.getSellerLogin());
        assertEquals(unreservedGoods.getPrice(), receivedGoods.getPrice());
        assertEquals(unreservedGoods.getQuantityInStock(), receivedGoods.getQuantityInStock());
        assertEquals(unreservedGoods.getQuantityReserved(), receivedGoods.getQuantityReserved());
    }

    @Test
    public void buyGoods() {
        Goods goods = createGoods();
        Goods reservedGoods = reserve(goods);
        Goods boughtGoods = buy(reservedGoods);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Goods> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Goods.class,
                boughtGoods.getId()
        );

        Goods receivedGoods = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedGoods);
        assertEquals(boughtGoods.getName(), receivedGoods.getName());
        assertEquals(boughtGoods.getDescription(), receivedGoods.getDescription());
        assertEquals(boughtGoods.getCategory(), receivedGoods.getCategory());
        assertEquals(boughtGoods.getSellerLogin(), receivedGoods.getSellerLogin());
        assertEquals(boughtGoods.getPrice(), receivedGoods.getPrice());
        assertEquals(boughtGoods.getQuantityInStock(), receivedGoods.getQuantityInStock());
        assertEquals(boughtGoods.getQuantityReserved(), receivedGoods.getQuantityReserved());
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
        assertEquals("Skis", createdGoods.getName());
        assertEquals("Mountain skis", createdGoods.getDescription());
        assertEquals("Sports goods", createdGoods.getCategory().getName());
        assertEquals("All you need to keep active", createdGoods.getCategory().getDescription());
        assertEquals("anonymousUser", createdGoods.getSellerLogin());
        assertEquals(new Integer(5000), createdGoods.getPrice());
        assertEquals(new Integer(600), createdGoods.getQuantityInStock());
        assertEquals(new Integer(200), createdGoods.getQuantityReserved());
        assertNotNull(createdGoods.getId());

        return createdGoods;
    }

    private Goods prefillGoods() {
        Goods goods = new Goods();
        goods.setName("Skis");
        goods.setDescription("Mountain skis");
        GoodsCategory category = new GoodsCategory();
        category.setName("Sports goods");
        category.setDescription("All you need to keep active");
        goods.setCategory(category);
        goods.setPrice(5000);
        goods.setQuantityInStock(600);
        goods.setQuantityReserved(200);
        return goods;
    }

    private Goods changeGoods(Goods goods) {
        goods.setName("Snowboard");
        goods.setDescription("All-mountain snowboard");
        goods.setPrice(8000);
        goods.setQuantityInStock(400);
        goods.setQuantityReserved(150);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Goods> httpEntity = new HttpEntity<>(goods, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        Goods changedGoods = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Goods.class).getBody();
        assertNotNull(changedGoods);
        assertEquals("Snowboard", changedGoods.getName());
        assertEquals("All-mountain snowboard", changedGoods.getDescription());
        assertEquals("anonymousUser", changedGoods.getSellerLogin());
        assertEquals(new Integer(8000), changedGoods.getPrice());
        assertEquals(new Integer(400), changedGoods.getQuantityInStock());
        assertEquals(new Integer(150), changedGoods.getQuantityReserved());
        assertNotNull(changedGoods.getId());

        return changedGoods;
    }

    private Goods reserve(Goods goods) {
        goods.setQuantityInStock(goods.getQuantityInStock() - 10);
        goods.setQuantityReserved(goods.getQuantityReserved() + 10);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Goods> httpEntity = new HttpEntity<>(goods, httpHeaders);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + RESERVE)
                .queryParam("id", goods.getId())
                .queryParam("quantity", 10);

        RestTemplate restTemplate = new RestTemplate();
        Goods reservedGoods = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                httpEntity,
                Goods.class).getBody();
        assertNotNull(reservedGoods);
        assertEquals("Skis", reservedGoods.getName());
        assertEquals("Mountain skis", reservedGoods.getDescription());
        assertEquals("anonymousUser", reservedGoods.getSellerLogin());
        assertEquals(new Integer(5000), reservedGoods.getPrice());
        assertEquals(new Integer(590), reservedGoods.getQuantityInStock());
        assertEquals(new Integer(210), reservedGoods.getQuantityReserved());
        assertNotNull(reservedGoods.getId());

        return reservedGoods;
    }

    private Goods unreserve(Goods reservedGoods) {
        reservedGoods.setQuantityInStock(reservedGoods.getQuantityInStock() + 10);
        reservedGoods.setQuantityReserved(reservedGoods.getQuantityReserved() - 10);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Goods> httpEntity = new HttpEntity<>(reservedGoods, httpHeaders);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + UNRESERVE)
                .queryParam("id", reservedGoods.getId())
                .queryParam("quantity", 10);

        RestTemplate restTemplate = new RestTemplate();
        Goods unreservedGoods = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                httpEntity,
                Goods.class).getBody();
        assertNotNull(unreservedGoods);
        assertEquals("Skis", unreservedGoods.getName());
        assertEquals("Mountain skis", unreservedGoods.getDescription());
        assertEquals("anonymousUser", unreservedGoods.getSellerLogin());
        assertEquals(new Integer(5000), unreservedGoods.getPrice());
        assertEquals(new Integer(600), unreservedGoods.getQuantityInStock());
        assertEquals(new Integer(200), unreservedGoods.getQuantityReserved());
        assertNotNull(unreservedGoods.getId());

        return unreservedGoods;
    }

    private Goods buy(Goods reservedGoods) {
        reservedGoods.setQuantityInStock(reservedGoods.getQuantityReserved() - 10);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Goods> httpEntity = new HttpEntity<>(reservedGoods, httpHeaders);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + BUY)
                .queryParam("id", reservedGoods.getId())
                .queryParam("quantity", 10);


        RestTemplate restTemplate = new RestTemplate();
        Goods boughtGoods = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                httpEntity,
                Goods.class).getBody();
        assertNotNull(boughtGoods);
        assertEquals("Skis", boughtGoods.getName());
        assertEquals("Mountain skis", boughtGoods.getDescription());
        assertEquals("anonymousUser", boughtGoods.getSellerLogin());
        assertEquals(new Integer(5000), boughtGoods.getPrice());
        assertEquals(new Integer(590), boughtGoods.getQuantityInStock());
        assertEquals(new Integer(200), boughtGoods.getQuantityReserved());
        assertNotNull(boughtGoods.getId());

        return boughtGoods;
    }
}