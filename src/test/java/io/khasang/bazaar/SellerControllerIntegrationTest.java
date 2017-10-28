package io.khasang.bazaar;

import io.khasang.bazaar.entity.Seller;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration tests for SellerController.
 *
 * @author Zulfia Garifullina
 * @date 13.10.2017.
 */
public class SellerControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/seller";
    private final String ADD = "/add";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";
    private final String GET_BY_ID = "/get/id";
    private final String GET_BY_LOGIN = "/get/login";
    private final String GET_ALL = "/all";
    private final String INCREASE_BALANCE = "/increase/balance";

    @Test
    public void addSeller() {
        Seller seller = createSeller("ivanov");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Seller> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Seller.class,
                seller.getId()
        );

        Seller receivedSeller = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedSeller);
        assertEquals(seller.getLogin(), receivedSeller.getLogin());
        assertEquals(seller.getBalance(), receivedSeller.getBalance());
        assertEquals(seller.getRoleId(), receivedSeller.getRoleId());
    }

    @Test
    public void updateSeller() {
        Seller seller = createSeller("petrov");
        Seller changedSeller = changeSeller(seller);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Seller> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Seller.class,
                changedSeller.getId()
        );

        Seller receivedSeller = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedSeller);
        assertEquals(changedSeller.getLogin(), receivedSeller.getLogin());
        assertEquals(changedSeller.getBalance(), receivedSeller.getBalance());
        assertEquals(changedSeller.getRoleId(), receivedSeller.getRoleId());
    }

    @Test
    public void deleteSeller() {
        Seller seller = createSeller("ivanov123");
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + DELETE)
                .queryParam("id", seller.getId());

        restTemplate.delete(builder.build().encode().toUri());

        ResponseEntity<Seller> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Seller.class,
                seller.getId()
        );

        Seller receivedSeller = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(receivedSeller);
    }

    @Test
    public void getSellerByLogin() {
        Seller seller = createSeller("ser_ivanov");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Seller>> result = restTemplate.exchange(
                ROOT + GET_BY_LOGIN + "/{login}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Seller>>() {
                },
                seller.getLogin()
        );

        //Seller receivedSeller = responseEntity.getBody();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        /*assertEquals(seller.getLogin(), receivedSeller.getLogin());
        assertEquals(seller.getBalance(), receivedSeller.getBalance());
        assertEquals(seller.getRoleId(), receivedSeller.getRoleId());*/
    }

    @Test
    public void getAllSellers() {
        RestTemplate restTemplate = new RestTemplate();
        createSeller("sidorov_1");
        createSeller("sidorov_2");

        ResponseEntity<List<Seller>> result = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Seller>>() {
                }
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());

    }

    @Test
    public void increaseSellerBalance() {
        Seller seller = createSeller("petrov123");
        Seller sellerWithIncreasedBalance = increaseBalance(seller);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Seller> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Seller.class,
                sellerWithIncreasedBalance.getId()
        );

        Seller receivedSeller = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(receivedSeller);
        assertEquals(sellerWithIncreasedBalance.getLogin(), receivedSeller.getLogin());
        assertEquals(sellerWithIncreasedBalance.getBalance(), receivedSeller.getBalance());
        assertEquals(sellerWithIncreasedBalance.getRoleId(), receivedSeller.getRoleId());
    }

    private Seller createSeller(String login) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Seller seller = prefillSeller(login);
        HttpEntity<Seller> HttpEntity = new HttpEntity<>(seller, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        Seller createdSeller = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                HttpEntity,
                Seller.class).getBody();
        assertNotNull(createdSeller);
        assertEquals(login, createdSeller.getLogin());
        assertEquals(Integer.valueOf(500), createdSeller.getBalance());
        assertEquals(Long.valueOf(3), createdSeller.getRoleId());
        assertNotNull(createdSeller.getId());

        return createdSeller;
    }

    private Seller prefillSeller(String login) {
        Seller seller = new Seller();
        seller.setLogin(login);
        seller.setBalance(500);
        seller.setRoleId((long) 3);
        return seller;
    }

    private Seller changeSeller(Seller seller) {
        seller.setLogin("sidorov");
        seller.setBalance(1000);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Seller> httpEntity = new HttpEntity<>(seller, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        Seller changedSeller = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Seller.class).getBody();
        assertNotNull(changedSeller);
        assertEquals("sidorov", changedSeller.getLogin());
        assertEquals(Integer.valueOf(1000), changedSeller.getBalance());
        assertEquals(Long.valueOf(3), changedSeller.getRoleId());
        assertNotNull(changedSeller.getId());

        return changedSeller;
    }

    private Seller increaseBalance(Seller seller) {
        seller.setBalance(seller.getBalance() + 350);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Seller> httpEntity = new HttpEntity<>(seller, httpHeaders);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + INCREASE_BALANCE)
                .queryParam("id", seller.getId())
                .queryParam("amount", 350);

        RestTemplate restTemplate = new RestTemplate();
        Seller sellerWithIncreasedBalance = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                httpEntity,
                Seller.class).getBody();

        assertNotNull(sellerWithIncreasedBalance);
        assertEquals("petrov123", sellerWithIncreasedBalance.getLogin());
        assertEquals(Integer.valueOf(850), sellerWithIncreasedBalance.getBalance());
        assertEquals(Long.valueOf(3), sellerWithIncreasedBalance.getRoleId());
        assertNotNull(sellerWithIncreasedBalance.getId());

        return sellerWithIncreasedBalance;
    }
}