package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.DeliveryDao;
import io.khasang.bazaar.entity.Delivery;
import org.hibernate.Session;

import java.util.List;

public class DeliveryDaoImpl extends BasicDaoImpl<Delivery> implements DeliveryDao {

    public DeliveryDaoImpl(Class<Delivery> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Delivery> getDeliveriesByRecipient(String recipient) {
        return (List<Delivery>) sessionFactory.getCurrentSession().
                createQuery("from Delivery as d where d.recipient = ?").setParameter(0, recipient).list();
    }
}