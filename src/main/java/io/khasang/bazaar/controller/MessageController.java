package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Message;
import io.khasang.bazaar.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Message entity.
 *
 * @author Zulfia Garifullina
 * @date 25.10.2017.
 */
@Controller
@RequestMapping(value = "/message")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Message getMessageById(@PathVariable(value = "id") String id) {
        return messageService.getById(Long.valueOf(id));
    }

    @RequestMapping(value = "/get/dialogue", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> getMessagesFromDialogue(@RequestParam(value = "sender") String sender, @RequestParam(value = "receiver") String receiver) {
        return messageService.getMessagesFromDialogue(sender, receiver);
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Message addMessage(@RequestBody Message message) {
        return messageService.addMessage(message);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Message deleteMessage(@RequestParam(value = "id") String id) {
        return messageService.deleteMessage(Long.parseLong(id));
    }
}
