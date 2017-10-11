package io.khasang.bazaar.dao;

import io.khasang.bazaar.dao.impl.BasicDaoImpl;
import io.khasang.bazaar.entity.Basket;

import java.util.List;

public interface BasketDao extends BasicDao<Basket> {
    List<Basket> getBasketByOrderId(String name);
}
