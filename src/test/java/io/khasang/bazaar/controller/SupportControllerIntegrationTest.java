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
    private final String GET_RECIPIENT = "/get/count_requests";
    private final String RECIPIENT = "/{requests}";

    @Test
    public void addDelivery() {
        Support addSupport = createDelivery();
        ResponseEntity<Support> responseEntity = getDeliveryById(addSupport.getId());
        Support receivedSupport = responseEntity.getBody();

        assertNotNull(receivedSupport);
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertEquals(addSupport.getRecipient(), receivedSupport.getRecipient());
        assertEquals(addSupport.getSender(), receivedSupport.getSender());
    }

    @Test
    public void getDeliveries() {
        createDelivery();
        createDelivery();

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
    public void updateDelivery() {
        Support newSupport = createDelivery();
        Support prefillSupport = prefillDelivery();
        prefillSupport.setId(newSupport.getId());


        ResponseEntity<Support> result = new RestTemplate().exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                addDeliveryHttpEntity(prefillSupport),
                Support.class
        );
        Support updateSupport = result.getBody();
        assertNotNull(updateSupport);
        assertEquals("Recipient", updateSupport.getRecipient());
        assertEquals("Sender", updateSupport.getSender());
        assertEquals("Product", updateSupport.getProduct());
        assertEquals("Address", updateSupport.getAddress());
        assertEquals("DateRegistration", updateSupport.getDateRegistration());
        assertEquals("DateSend", updateSupport.getDateSend());
        assertEquals("DateReceive", updateSupport.getDateReceive());

        Support receivedSupport = getDeliveryById(updateSupport.getId()).getBody();
        assertNotNull(receivedSupport);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updateSupport.getRecipient(), receivedSupport.getRecipient());
        assertEquals(updateSupport.getSender(), receivedSupport.getSender());
    }

    @Test
    public void deleteDelivery() {
        Support newSupport = createDelivery();
        Support result = new RestTemplate().exchange(
                ROOT + DELETE + PARAM_ID + newSupport.getId(),
                HttpMethod.DELETE,
                null,
                Support.class).getBody();
        assertNotNull(result);
        assertEquals(newSupport.getId(), result.getId());
        assertEquals(null, getDeliveryById(newSupport.getId()).getBody());
        assertEquals(null, getDeliveryById(result.getId()).getBody());
    }


    @Test
    public void getDeliveriesByRecipient() {
        Support newSupport = createDelivery();
        createDelivery();

        ResponseEntity<List<Support>> result = new RestTemplate().exchange(
                ROOT + GET_RECIPIENT + RECIPIENT,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Support>>() {
                },
                newSupport.getRecipient());
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    private Support createDelivery() {
        Support support = prefillDelivery();

        Support createSupport = new RestTemplate().exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                addDeliveryHttpEntity(support),
                Support.class
        ).getBody();
        assertNotNull(createSupport);
        assertEquals("Recipient", createSupport.getRecipient());
        assertEquals("Sender", createSupport.getSender());
        assertEquals("Product", createSupport.getProduct());
        assertEquals("Address", createSupport.getAddress());
        assertEquals("DateRegistration", createSupport.getDateRegistration());
        assertEquals("DateSend", createSupport.getDateSend());
        assertEquals("DateReceive", createSupport.getDateReceive());
        return createSupport;
    }

    private ResponseEntity<Support> getDeliveryById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                ROOT + GET_BY_ID + ID,
                HttpMethod.GET,
                null,
                Support.class,
                id);
    }

    private HttpEntity<Support> addDeliveryHttpEntity(Support support) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<>(support, httpHeaders);
    }

    private Support prefillDelivery() {
        Support support = new Support();
        support.setRecipient("Recipient");
        support.setSender("Sender");
        support.setProduct("Product");
        support.setAddress("Address");
        support.setDateRegistration("DateRegistration");
        support.setDateSend("DateSend");
        support.setDateReceive("DateReceive");
        return support;
    }
}