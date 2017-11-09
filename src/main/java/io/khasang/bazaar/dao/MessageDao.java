package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Message;

import java.util.List;

/**
 * Interface for representing DAO for Message entity.
 *
 * @author Zulfia Garifullina
 * @date 02.11.2017.
 */
public interface MessageDao extends BasicDao<Message> {
    /**
     * Method for retrieving messages from a particular dialogue between sender and receiver.
     * @param sender - login of the sender
     * @param receiver - login of the receiver
     * @return messages from the dialogue between sender and receiver
     */
    List<Message> getMessagesFromDialogue(String sender, String receiver);
}
