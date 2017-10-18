package io.khasang.bazaar;

import io.khasang.bazaar.entity.Feedback;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FeedbackControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/feedback";
    private final String ADD = "/add";
    private final String GET_BY_ID = "/get/id";
    private final String GET_ALL_BY_USER_ID = "/get/byuser";
    private final String GET_ALL_BY_GOOD_ID = "/get/bygood";

    @Test
    public void getFeedbackByUserId() {
        RestTemplate restTemplate = new RestTemplate();
        createfeedback();
        Feedback createdFeedback = createfeedback();

        ResponseEntity<List<Feedback>> result = restTemplate.exchange(
                ROOT + GET_ALL_BY_USER_ID+"/{user_id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Feedback>>(){},
                createdFeedback.getUser_id()
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
    }

    private Feedback createfeedback() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Feedback feedback = prefillFeedback();
        HttpEntity<Feedback> httpEntity = new HttpEntity<>(feedback, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        Feedback createdFeedback = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Feedback.class).getBody();
        assertNotNull(createdFeedback);
        assertEquals((Long)1L,createdFeedback.getUser_id());
        assertEquals((Long)1L,createdFeedback.getGood_id());
        assertEquals(java.util.Optional.of(5),createdFeedback.getFeedbackRating());
        assertNotNull(createdFeedback.getId());
        return createdFeedback;
    }

    private Feedback prefillFeedback() {
        Feedback feedback = new Feedback();
        feedback.setMessage("It's a test message by user 1 about good 1");
        feedback.setUser_id(1L);
        feedback.setGood_id(1L);
        feedback.setFeedbackRating((short)5);
        return feedback;
    }
}
