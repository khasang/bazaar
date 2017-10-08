package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.BasketDao;
import io.khasang.bazaar.entity.Basket;

import java.util.List;

public class BasketDaoImpl extends BasicDaoImpl implements BasketDao {

    public BasketDaoImpl(Class<Basket> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Basket> getBasketByOrderId (String orderid) {
        return (List<Basket>) sessionFactory.getCurrentSession().
                createQuery("from Basket as b where b.order_id = ?").setParameter(0, orderid).list();
    }
}
