package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Delivery;
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
public class DeliveryControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/delivery";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String ID = "/{id}";
    private final String PARAM_ID = "?id=";
    private final String GET_ALL = "/all";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";
    private final String GET_RECIPIENT = "/get/recipient";
    private final String RECIPIENT = "/{recipient}";

    @Test
    public void addDelivery() {
        Delivery addDelivery = createDelivery();
        ResponseEntity<Delivery> responseEntity = getDeliveryById(addDelivery.getId());
        Delivery receivedDelivery = responseEntity.getBody();

        assertNotNull(receivedDelivery);
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertEquals(addDelivery.getRecipient(), receivedDelivery.getRecipient());
        assertEquals(addDelivery.getSender(), receivedDelivery.getSender());
    }

    @Test
    public void getDeliveries() {
        createDelivery();
        createDelivery();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Delivery>> result = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Delivery>>() {
                }
        );
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void updateDelivery() {
        Delivery newDelivery = createDelivery();
        Delivery prefillDelivery = prefillDelivery();
        prefillDelivery.setId(newDelivery.getId());


        ResponseEntity<Delivery> result = new RestTemplate().exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                addDeliveryHttpEntity(prefillDelivery),
                Delivery.class
        );
        Delivery updateDelivery = result.getBody();
        assertNotNull(updateDelivery);
        assertEquals("Recipient", updateDelivery.getRecipient());
        assertEquals("Sender", updateDelivery.getSender());
        assertEquals("Product", updateDelivery.getProduct());
        assertEquals("Address", updateDelivery.getAddress());
        assertEquals("DateRegistration", updateDelivery.getDateRegistration());
        assertEquals("DateSend", updateDelivery.getDateSend());
        assertEquals("DateReceive", updateDelivery.getDateReceive());

        Delivery receivedDelivery = getDeliveryById(updateDelivery.getId()).getBody();
        assertNotNull(receivedDelivery);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updateDelivery.getRecipient(), receivedDelivery.getRecipient());
        assertEquals(updateDelivery.getSender(), receivedDelivery.getSender());
    }

    @Test
    public void deleteDelivery() {
        Delivery newDelivery = createDelivery();
        Delivery result = new RestTemplate().exchange(
                ROOT + DELETE + PARAM_ID + newDelivery.getId(),
                HttpMethod.DELETE,
                null,
                Delivery.class).getBody();
        assertNotNull(result);
        assertEquals(newDelivery.getId(), result.getId());
        assertEquals(null, getDeliveryById(newDelivery.getId()).getBody());
        assertEquals(null, getDeliveryById(result.getId()).getBody());
    }


    @Test
    public void getDeliveriesByRecipient() {
        Delivery newDelivery = createDelivery();
        createDelivery();

        ResponseEntity<List<Delivery>> result = new RestTemplate().exchange(
                ROOT + GET_RECIPIENT + RECIPIENT,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Delivery>>() {
                },
                newDelivery.getRecipient());
        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    private Delivery createDelivery() {
        Delivery delivery = prefillDelivery();

        Delivery createDelivery = new RestTemplate().exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                addDeliveryHttpEntity(delivery),
                Delivery.class
        ).getBody();
        assertNotNull(createDelivery);
        assertEquals("Recipient", createDelivery.getRecipient());
        assertEquals("Sender", createDelivery.getSender());
        assertEquals("Product", createDelivery.getProduct());
        assertEquals("Address", createDelivery.getAddress());
        assertEquals("DateRegistration", createDelivery.getDateRegistration());
        assertEquals("DateSend", createDelivery.getDateSend());
        assertEquals("DateReceive", createDelivery.getDateReceive());
        return createDelivery;
    }

    private ResponseEntity<Delivery> getDeliveryById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                ROOT + GET_BY_ID + ID,
                HttpMethod.GET,
                null,
                Delivery.class,
                id);
    }

    private HttpEntity<Delivery> addDeliveryHttpEntity(Delivery delivery) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<>(delivery, httpHeaders);
    }

    private Delivery prefillDelivery() {
        Delivery delivery = new Delivery();
        delivery.setRecipient("Recipient");
        delivery.setSender("Sender");
        delivery.setProduct("Product");
        delivery.setAddress("Address");
        delivery.setDateRegistration("DateRegistration");
        delivery.setDateSend("DateSend");
        delivery.setDateReceive("DateReceive");
        return delivery;
    }
}