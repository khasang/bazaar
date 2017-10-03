package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Delivery;

import java.util.List;

public interface DeliveryDao extends BasicDao<Delivery> {
    /**
     * Receive delivery's from database with specified recipient
     *
     * @param recipient - delivery's recipient
     * @return list of delivery's with specified recipient
     */
    List<Delivery> getDeliveriesByRecipient(String recipient);
}
