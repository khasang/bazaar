package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.MessageDao;
import io.khasang.bazaar.entity.Message;

import java.util.List;

/**
 * Implementation of MessageDao interface for representing DAO for Message entity.
 *
 * @author Zulfia Garifullina
 * @date 02.11.2017.
 */
public class MessageDaoImpl extends BasicDaoImpl<Message> implements MessageDao {
    public MessageDaoImpl(Class<Message> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Message> getMessagesFromDialogue(String sender, String receiver) {
        return (List<Message>) getCurrentSession().createQuery("from Message where " +
                "(sender = :sender or sender = :receiver) and (receiver = :sender or receiver = :receiver)")
                .setParameter("sender", sender).setParameter("receiver", receiver).list();
    }
}
