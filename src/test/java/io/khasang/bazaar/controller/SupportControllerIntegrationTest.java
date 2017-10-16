package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Support;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;


/**
 * IntegrationTest for DeliveryController.class
 *
 */
public class SupportControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/support";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String ID = "/{id}";
    private final String PARAM_ID = "?id=";
    private final String GET_ALL = "/all";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";
    private final String GET_COUNT_REQUESTS = "/get/count_requests";
    private final String REQUESTS = "/{requests}";

    @Test
    public void addSupport() {
        Support addSupport = createSupport();
        ResponseEntity<Support> responseEntity = getSupportById(addSupport.getId());
        Support receivedSupport = responseEntity.getBody();

        assertNotNull(receivedSupport);
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertEquals(addSupport.getQuestion(), receivedSupport.getQuestion());
        assertEquals(addSupport.getResponse(), receivedSupport.getResponse());
    }

    @Test
    public void getSupport() {
        createSupport();
        createSupport();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Support>> result = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Support>>() {
                }
        );
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void updateSupport() {
        Support newSupport = createSupport();
        Support prefillSupport = prefillSupport();
        prefillSupport.setId(newSupport.getId());


        ResponseEntity<Support> result = new RestTemplate().exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                addSupportHttpEntity(prefillSupport),
                Support.class
        );
        Support updateSupport = result.getBody();
        assertNotNull(updateSupport);
        assertEquals("Question", updateSupport.getQuestion());
        assertEquals("Response", updateSupport.getResponse());
        assertEquals(true, updateSupport.getClose());

        Support receivedSupport = getSupportById(updateSupport.getId()).getBody();
        assertNotNull(receivedSupport);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updateSupport.getQuestion(), receivedSupport.getQuestion());
        assertEquals(updateSupport.getResponse(), receivedSupport.getResponse());
    }

    @Test
    public void deleteSupport() {
        Support newSupport = createSupport();
        Support result = new RestTemplate().exchange(
                ROOT + DELETE + PARAM_ID + newSupport.getId(),
                HttpMethod.DELETE,
                null,
                Support.class).getBody();
        assertNotNull(result);
        assertEquals(newSupport.getId(), result.getId());
        assertEquals(null, getSupportById(newSupport.getId()).getBody());
        assertEquals(null, getSupportById(result.getId()).getBody());
    }


    @Test
    public void getSupportByCountRequests() {
        Support newSupport = createSupport();
        createSupport();

        ResponseEntity<List<Support>> result = new RestTemplate().exchange(
                ROOT + GET_COUNT_REQUESTS + REQUESTS,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Support>>() {
                },
                newSupport.getCountRequests().toString());
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    private Support createSupport() {
        Support support = prefillSupport();

        Support createSupport = new RestTemplate().exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                addSupportHttpEntity(support),
                Support.class
        ).getBody();

        assertNotNull(createSupport);
        assertEquals("Question", createSupport.getQuestion());
        assertEquals("Response", createSupport.getResponse());
        assertEquals(true, createSupport.getClose());
        return createSupport;
    }

    private ResponseEntity<Support> getSupportById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                ROOT + GET_BY_ID + ID,
                HttpMethod.GET,
                null,
                Support.class,
                id);
    }

    private HttpEntity<Support> addSupportHttpEntity(Support support) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<>(support, httpHeaders);
    }

    private Support prefillSupport() {
        Support support = new Support();
        support.setQuestion("Question");
        support.setResponse("Response");
        support.setClose(true);
        support.setCountRequests(2L);
        return support;
    }
}