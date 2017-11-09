package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.Message;

import java.util.List;

/**
 * Service interface for Message entity.
 *
 * @author Zulfia Garifullina
 * @date 25.10.2017.
 */
public interface MessageService {
    Message getById(Long id);

    List<Message> getMessagesFromDialogue(String sender, String receiver);

    Message addMessage(Message message);

    Message deleteMessage(Long id);
}
