package io.khasang.bazaar;

import io.khasang.bazaar.entity.Message;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration tests for MessageController.
 *
 * @author Zulfia Garifullina
 * @date 02.11.2017.
 */
public class MessageControllerIntegrationTest {
    private final String ROOT = "http://localhost:8080/message";
    private final String ADD = "/add";
    private final String DELETE = "/delete";
    private final String GET_BY_ID = "/get/id";
    private final String GET_DIALOGUE = "/get/dialogue";

    @Test
    public void addMessage() {
        Message message = createMessage();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Message> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Message.class,
                message.getId()
        );

        Message receivedMessage = responseEntity.getBody();
        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(receivedMessage);
        assertEquals("anonymousUser", receivedMessage.getSender());
        assertEquals("Hello. I have a question.", receivedMessage.getBody());
        assertEquals("ivanov87", receivedMessage.getReceiver());
    }

    @Test
    public void deleteMessage() {
        Message message = createMessage();
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + DELETE)
                .queryParam("id", message.getId());

        restTemplate.delete(builder.build().encode().toUri());

        ResponseEntity<Message> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                Message.class,
                message.getId()
        );

        Message receivedMessage = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(receivedMessage);
    }

    @Test
    public void getMessagesFromDialogue() {
        Message message = createMessage();

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ROOT + GET_DIALOGUE)
                .queryParam("sender", message.getSender())
                .queryParam("receiver", message.getReceiver());

        ResponseEntity<List<Message>> result = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Message>>() {
                }
        );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result);
    }

    private Message createMessage() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Message message = prefillMessage();
        HttpEntity<Message> httpEntity = new HttpEntity<>(message, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        Message createdMessage = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.PUT,
                httpEntity,
                Message.class
        ).getBody();

        assertNotNull(createdMessage);
        assertEquals("anonymousUser", createdMessage.getSender());
        assertEquals("Hello. I have a question.", createdMessage.getBody());
        assertEquals("ivanov87", createdMessage.getReceiver());
        assertNotNull(createdMessage.getId());

        return createdMessage;
    }

    private Message prefillMessage() {
        Message message = new Message();
        message.setBody("Hello. I have a question.");
        message.setReceiver("ivanov87");
        return message;
    }
}
