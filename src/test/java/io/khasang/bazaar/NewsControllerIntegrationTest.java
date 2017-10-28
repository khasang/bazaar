package io.khasang.bazaar;

import io.khasang.bazaar.entity.News;
import io.khasang.bazaar.entity.NewsCategory;
import io.khasang.bazaar.entity.NewsTag;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class NewsControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/news";
    private final String ADD = "/add";
    private final String UPDATE = "/update";
    private final String DELETE = "/delete";
    private final String GET_BY_ID = "/get/id";
    private final String GET_BY_TITLE = "/get/title";
    private final String GET_ALL = "/all";

    @Test
    public void addNews() {
        News news = createNews();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<News> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                News.class,
                news.getId()
        );

        News receivedNews = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedNews);
        assertEquals(news.getTitle(), receivedNews.getTitle());
        assertEquals(news.getBody(), receivedNews.getBody());
    }

    @Test
    public void getNewsByTitle() {
        News news = createNews();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<News>> result = restTemplate.exchange(
                ROOT + GET_BY_TITLE + "/{title}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<News>>() {
                },
                news.getTitle()
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }


    private News createNews() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        News news = prefillNews();
        HttpEntity<News> httpEntity = new HttpEntity<>(news, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        News createdNews = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                News.class).getBody();


        assertNotNull(createdNews);
        assertEquals("Black friday! Super sale!", createdNews.getTitle());

        assertNotNull(createdNews.getId());

        return createdNews;
    }

    private News prefillNews() {
        News news = new News();
        news.setTitle("Black friday! Super sale!");
        news.setBody("Only in this friday sale! Discount up to 80%");
        NewsCategory category = new NewsCategory();
        category.setName("Sale");
        news.setCategory(category);

        NewsTag newsTag1 = new NewsTag();
        newsTag1.setName("Sale");

        NewsTag newsTag2 = new NewsTag();
        newsTag2.setName("Black Friday");

        news.addTag(newsTag1);
        news.addTag(newsTag2);

        return news;
    }



}
