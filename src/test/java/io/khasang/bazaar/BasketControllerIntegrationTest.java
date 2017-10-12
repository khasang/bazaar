package io.khasang.bazaar;

import io.khasang.bazaar.entity.Basket;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.*;

public class BasketControllerIntegrationTest {

    private final String ROOT = "http://localhost:8080/basket";
    private final String GET_BY_ID = "/get/id";
    private final String ADD = "/add";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";
    private final String ORDERNOTISSUED = "/orderNotIssued";
    private final String ORDERISSUED = "/orderIssued";

    @Test
    public void addGoodsInBasket() {
        Basket basket = createBasket();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Basket> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Basket.class,
                basket.getId()
        );

        Basket receivedBasket = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedBasket);
        assertEquals(basket.getGoodsid(), receivedBasket.getGoodsid());
        assertEquals(basket.getOrderid(), receivedBasket.getOrderid());
        assertEquals(basket.getUserid(), receivedBasket.getUserid());
    }

    @Test
    public void updateBasket() {
        Basket basket = createBasket();
        Basket changedBasket = changeBasket(basket);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Basket> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Basket.class,
                changedBasket.getId()
        );

        Basket receivedBasket = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedBasket);
        assertEquals(changedBasket.getGoodsid(), receivedBasket.getGoodsid());
        assertEquals(changedBasket.getOrderid(), receivedBasket.getOrderid());
        assertEquals(changedBasket.getUserid(), receivedBasket.getUserid());
        assertEquals(changedBasket.getOrderissued(), receivedBasket.getOrderissued());
        assertEquals(changedBasket.getOrdernotissued(), receivedBasket.getOrdernotissued());
    }

    @Test
    public void deleteGoodsInBasket() {
        Basket basket = createBasket();
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + DELETE)
                .queryParam("id", basket.getId());

        restTemplate.delete(builder.build().encode().toUri());

        ResponseEntity<Basket> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Basket.class,
                basket.getId()
        );

        Basket receivedBasket = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(receivedBasket);
    }

    @Test
    public void orderNotIssued() {
        Basket basket = createBasket();
        Basket ordernotissued = orderIssued(basket);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Basket> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Basket.class,
                ordernotissued.getId()
        );

        Basket receivedBasket = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedBasket);
        assertEquals(ordernotissued.getGoodsid(), receivedBasket.getGoodsid());
        assertEquals(ordernotissued.getOrderid(), receivedBasket.getOrderid());
        assertEquals(ordernotissued.getUserid(), receivedBasket.getUserid());
        assertEquals(ordernotissued.getOrderissued(), receivedBasket.getOrderissued());
        assertEquals(ordernotissued.getOrdernotissued(), receivedBasket.getOrdernotissued());
    }

    @Test
    public void orderIssued() {
        Basket basket = createBasket();
        Basket ordernotissued = orderIssued(basket);
        Basket orderissued = orderNotIssued(ordernotissued);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Basket> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Basket.class,
                orderissued.getId()
        );

        Basket receivedBasket = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedBasket);
        assertEquals(orderissued.getGoodsid(), receivedBasket.getGoodsid());
        assertEquals(orderissued.getOrderid(), receivedBasket.getOrderid());
        assertEquals(orderissued.getUserid(), receivedBasket.getUserid());
        assertEquals(orderissued.getOrderissued(), receivedBasket.getOrderissued());
        assertEquals(orderissued.getOrdernotissued(), receivedBasket.getOrdernotissued());
    }

    private Basket createBasket() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Basket basket = prefillBasket();
        HttpEntity<Basket> httpEntity = new HttpEntity<>(basket, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        Basket createdBasket = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Basket.class).getBody();
        assertNotNull(createdBasket);
        assertEquals(new Integer(1), createdBasket.getGoodsid());
        assertEquals(new Integer(1), createdBasket.getOrderid());
        assertEquals(new Integer(1), createdBasket.getUserid());
        assertEquals(new Integer(1), createdBasket.getOrdernotissued());
        assertEquals(new Integer(0), createdBasket.getOrderissued());
        assertNotNull(createdBasket.getId());

        return createdBasket;
    }

    private Basket prefillBasket() {
        Basket basket = new Basket();
        basket.setGoodsid(1);
        basket.setOrderid(1);
        basket.setUserid(1);
        basket.setOrdernotissued(1);
        basket.setOrderissued(0);
        return basket;
    }

    private Basket changeBasket(Basket basket) {
        basket.setGoodsid(2);
        basket.setOrderid(2);
        basket.setOrdernotissued(0);
        basket.setOrderissued(1);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Basket> httpEntity = new HttpEntity<>(basket, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        Basket changedBasket = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Basket.class).getBody();
        assertNotNull(changedBasket);
        assertEquals(new Integer(1), changedBasket.getGoodsid());
        assertEquals(new Integer(1), changedBasket.getOrderid());
        assertEquals(new Integer(1), changedBasket.getUserid());
        assertEquals(new Integer(1), changedBasket.getOrdernotissued());
        assertEquals(new Integer(0), changedBasket.getOrderissued());
        assertNotNull(changedBasket.getId());

        return changedBasket;
    }

    private Basket orderIssued(Basket basket) {
        basket.setOrderissued(basket.getOrderissued() + 1);
        basket.setOrdernotissued(basket.getOrdernotissued() - 1);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Basket> httpEntity = new HttpEntity<>(basket, httpHeaders);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + ORDERISSUED)
                .queryParam("id", basket.getId())
                .queryParam("order_issued", 0)
                .queryParam("order_not_issued",1);

        RestTemplate restTemplate = new RestTemplate();
        Basket orderIssued = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                httpEntity,
                Basket.class).getBody();
        assertNotNull(orderIssued);
        assertEquals(new Integer(1), orderIssued.getGoodsid());
        assertEquals(new Integer(1), orderIssued.getOrderid());
        assertEquals(new Integer(1), orderIssued.getUserid());
        assertEquals(new Integer(1), orderIssued.getOrdernotissued());
        assertEquals(new Integer(0), orderIssued.getOrderissued());
        assertNotNull(orderIssued.getId());

        return orderIssued;
    }

    private Basket orderNotIssued(Basket basket) {
        basket.setOrdernotissued(basket.getOrdernotissued() + 1);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Basket> httpEntity = new HttpEntity<>(basket, httpHeaders);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + ORDERNOTISSUED)
                .queryParam("id", basket.getId())
                .queryParam("order_issued", 0)
                .queryParam("order_not_issued",0);

        RestTemplate restTemplate = new RestTemplate();
        Basket ordernotissued = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                httpEntity,
                Basket.class).getBody();
        assertNotNull(ordernotissued);
        assertEquals(new Integer(1), ordernotissued.getGoodsid());
        assertEquals(new Integer(1), ordernotissued.getOrderid());
        assertEquals(new Integer(1), ordernotissued.getUserid());
        assertEquals(new Integer(1), ordernotissued.getOrdernotissued());
        assertEquals(new Integer(0), ordernotissued.getOrderissued());
        assertNotNull(ordernotissued.getId());

        return ordernotissued;
    }

}
