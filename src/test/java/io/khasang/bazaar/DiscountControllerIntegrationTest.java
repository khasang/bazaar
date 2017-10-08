package io.khasang.bazaar;

import io.khasang.bazaar.entity.Discount;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DiscountControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/discount";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String GET_BY_PROMO_CODE = "/get/code";
    private final String GET_ALL = "/all";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";
    private final String GET_ACTUAL = "/get/actual";


    @Test
    public void addDiscount() {
        Discount discount = createDiscount();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Discount> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Discount.class,
                discount.getId()
        );
        Discount receivedDiscount = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedDiscount);
        assertEquals(discount.getRate(), receivedDiscount.getRate());
        assertEquals(discount.getStartDate(), receivedDiscount.getStartDate());
        assertEquals(discount.getEndDate(), receivedDiscount.getEndDate());
        assertEquals(discount.getPromoCode(),receivedDiscount.getPromoCode());
        assertEquals(discount.getDescription(),receivedDiscount.getDescription());
    }

    @Test
    public void getAllDiscounts() {
        RestTemplate restTemplate = new RestTemplate();
        createDiscount();
        createDiscount();

        ResponseEntity<List<Discount>> result = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Discount>>() {
                }
        );
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void getActualDiscounts() {
        RestTemplate restTemplate = new RestTemplate();
        createDiscount();

        ResponseEntity<List<Discount>> result = restTemplate.exchange(
                ROOT + GET_ACTUAL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Discount>>() {
                }
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        Date date = Date.valueOf(LocalDate.now());
        for (Discount discount: result.getBody()) {
            assertFalse(discount.getStartDate().after(date));
            assertFalse(discount.getEndDate().before(date));
        }
    }

    @Test
    public void updateDiscount() {
        Discount discount = createDiscount();
        Discount changedDiscount = changeDiscount(discount);

        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<Discount> responseEntity = restTemplate2.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Discount.class,
                discount.getId()
        );
        Discount receivedDiscount = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedDiscount);
        assertEquals(changedDiscount.getRate(), receivedDiscount.getRate());
        assertEquals(changedDiscount.getStartDate(), receivedDiscount.getStartDate());
        assertEquals(changedDiscount.getEndDate(), receivedDiscount.getEndDate());
        assertEquals(changedDiscount.getPromoCode(),receivedDiscount.getPromoCode());
        assertEquals(changedDiscount.getDescription(),receivedDiscount.getDescription());
    }

    @Test
    public void deleteDiscount(){
        Discount discount = createDiscount();
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + DELETE)
                .queryParam("id", discount.getId());

        restTemplate.delete(builder.build().encode().toUri());

        ResponseEntity<Discount> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Discount.class,
                discount.getId()
        );

        Discount receivedDiscount = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(receivedDiscount);
    }

    @Test
    public void getDiscountByPromoCode(){
        Discount discount = createDiscount();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Discount>> result = restTemplate.exchange(
                ROOT + GET_BY_PROMO_CODE + "/{promo_code}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Discount>>() {
                },
                discount.getPromoCode()
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }



    private Discount createDiscount() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Discount discount = prefillDiscount();
        HttpEntity<Discount> httpEntity = new HttpEntity<>(discount, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        Discount createdDiscount = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Discount.class).getBody();
        assertNotNull(createdDiscount);
        assertEquals(20, createdDiscount.getRate().longValue());
        assertEquals("darim_skidku", createdDiscount.getDescription());
        assertNotNull(createdDiscount.getId());

        return createdDiscount;
    }

    private Discount prefillDiscount() {
        Discount discount = new Discount();
        discount.setRate(20);
        discount.setDescription("darim_skidku");
        discount.setStartDate(java.sql.Date.valueOf(LocalDate.now().minusDays(5)));
        discount.setEndDate(java.sql.Date.valueOf(LocalDate.now().plusDays(5)));
        discount.setPromoCode("1");
        return discount;
    }

    private Discount changeDiscount(Discount discount) {
        discount.setRate(15);
        discount.setDescription("изменённая скидка");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Discount> httpEntity = new HttpEntity<>(discount, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        Discount changedDiscount = restTemplate.exchange(ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                Discount.class).getBody();
        assertNotNull(changedDiscount);
        assertEquals(15, changedDiscount.getRate().intValue());
        assertEquals("изменённая скидка", changedDiscount.getDescription());
        assertNotNull(changedDiscount.getId());

        return changedDiscount;
    }
}