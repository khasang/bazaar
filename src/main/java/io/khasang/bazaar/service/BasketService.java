package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.Basket;

import java.util.List;

public interface BasketService {
    /**
     * Method for retrieving goods of basket by their database id.
     * @param id - id of the goods of basket in the database
     * @return goods of basket with given id
     */
    Basket getBasketById(Long id);

    /**
     * Method for retrieving goods of basket from the database.
     * @return all the goods of basket.
     */

    List<Basket> getBaskets();

    /**
     * Method for adding new goods to the database table
     * @param goods- the object being added to the database table as a row
     * @return the goods that was added
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
