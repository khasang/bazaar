package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.MessageDao;
import io.khasang.bazaar.entity.Message;
import io.khasang.bazaar.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the service interface for Message entity.
 *
 * @author Zulfia Garifullina
 * @date 25.10.2017.
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public Message getById(Long id) {
        return messageDao.getById(id);
    }

    @Override
    public List<Message> getMessagesFromDialogue(String sender, String receiver) {
        return messageDao.getMessagesFromDialogue(sender, receiver);
    }

    @Override
    public Message addMessage(Message message) {
        message.setSender(SecurityContextHolder.getContext().getAuthentication().getName());
        message.setTimestamp(LocalDateTime.now());
        return messageDao.add(message);
    }

    @Override
    public Message deleteMessage(Long id) {
        Message message = messageDao.getById(id);
        return messageDao.delete(message);
    }
}
