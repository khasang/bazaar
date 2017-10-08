package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.Basket;

import java.util.List;

public interface BasketService {
    /**
     *
     */
    Basket getBasketById(Long id);

    /**
     *
     */

    List<Basket> getBaskets();

    /**
     *
     */
    Basket addGoodsInBasket (Basket basket);

    /**
     *
     */
    Basket updateBasket(Basket basket);

    /**
     *
     */
    Basket deleteGoodsInBasket(Long id);

    /**
     *
     */
    Basket orderNotIssued (Long id, Integer ordernotissued);

    /**
     *
     */
    Basket orderIssued(Long id, Integer orderissued);

}
