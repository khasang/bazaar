package io.khasang.bazaar;

import io.khasang.bazaar.entity.GoodsCategory;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration tests for GoodsCategoryController.
 *
 * @author Zulfia Garifullina
 * @date 04.10.2017.
 */
public class GoodsCategoryControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/goodscategories";
    private final String ADD = "/add";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";
    private final String GET_BY_ID = "/get/id";
    private final String GET_BY_NAME = "/get/name";
    private final String GET_ALL = "/all";

    @Test
    public void addGoodsCategory() {
        GoodsCategory goodsCategory = createGoodsCategory();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GoodsCategory> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                GoodsCategory.class,
                goodsCategory.getId()
        );

        GoodsCategory receivedGoodsCategory = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedGoodsCategory);
        assertEquals(goodsCategory.getName(), receivedGoodsCategory.getName());
        assertEquals(goodsCategory.getDescription(), receivedGoodsCategory.getDescription());
    }

    @Test
    public void updateGoodsCategory() {
        GoodsCategory goodsCategory = createGoodsCategory();
        GoodsCategory changedGoodsCategory = changeGoodsCategory(goodsCategory);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GoodsCategory> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                GoodsCategory.class,
                changedGoodsCategory.getId()
        );

        GoodsCategory receivedGoodsCategory = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedGoodsCategory);
        assertEquals(changedGoodsCategory.getName(), receivedGoodsCategory.getName());
        assertEquals(changedGoodsCategory.getDescription(), receivedGoodsCategory.getDescription());
    }

    @Test
    public void deleteGoodsCategory() {
        GoodsCategory goodsCategory = createGoodsCategory();
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + DELETE)
                .queryParam("id", goodsCategory.getId());

        restTemplate.delete(builder.build().encode().toUri());

        ResponseEntity<GoodsCategory> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                GoodsCategory.class,
                goodsCategory.getId()
        );

        GoodsCategory receivedGoodsCategory = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(receivedGoodsCategory);
    }

    @Test
    public void getGoodsCategoryByName() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<GoodsCategory>> result = restTemplate.exchange(
                ROOT + GET_BY_NAME + "/{name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GoodsCategory>>() {
                },
                "Music"
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    @Test
    public void getAllGoodsCategories() {
        RestTemplate restTemplate = new RestTemplate();
        createGoodsCategory();
        createGoodsCategory();

        ResponseEntity<List<GoodsCategory>> result = restTemplate.exchange(
                ROOT + GET_ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GoodsCategory>>() {
                }
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    private GoodsCategory createGoodsCategory() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        GoodsCategory goodsCategory = prefillGoodsCategory();
        HttpEntity<GoodsCategory> httpEntity = new HttpEntity<>(goodsCategory, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        GoodsCategory createdGoodsCategory = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                GoodsCategory.class).getBody();
        assertNotNull(createdGoodsCategory);
        assertEquals("Electronics", createdGoodsCategory.getName());
        assertEquals("Gadgets: laptops, smartphones, cameras", createdGoodsCategory.getDescription());
        assertNotNull(createdGoodsCategory.getId());

        return createdGoodsCategory;
    }

    private GoodsCategory prefillGoodsCategory() {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setName("Electronics");
        goodsCategory.setDescription("Gadgets: laptops, smartphones, cameras");
        return goodsCategory;
    }

    private GoodsCategory changeGoodsCategory(GoodsCategory goodsCategory) {
        goodsCategory.setName("Fashion");
        goodsCategory.setDescription("Womenswear and menswear");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<GoodsCategory> httpEntity = new HttpEntity<>(goodsCategory, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        GoodsCategory changedGoodsCategory = restTemplate.exchange(
                ROOT + UPDATE,
                HttpMethod.POST,
                httpEntity,
                GoodsCategory.class).getBody();
        assertNotNull(changedGoodsCategory);
        assertEquals("Fashion", changedGoodsCategory.getName());
        assertEquals("Womenswear and menswear", changedGoodsCategory.getDescription());
        assertNotNull(changedGoodsCategory.getId());

        return changedGoodsCategory;
    }
}
