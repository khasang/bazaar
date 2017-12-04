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
     * @param basket- the object being added to the database table as a row
     * @return the basket that was added
     */
    Basket addGoodsInBasket (Basket basket);

    /**
     * Method for updating goods in the database
     * @param basket - the object being updated in the database
     * @return the goods that were updated
     */
    Basket updateBasket(Basket basket);

    /**
     * Deletes the goods of basket with the specified id from the database
     * @param id database id of the goods of basket that needs to be deleted
     * @return the deleted goods of basket
     */
    Basket deleteGoodsInBasket(Long id);

    /**
     * Specifies the added items in the basket, but not in the order.
     * The method is called when the user adds items to the basket
     * @param id database id for chosen goods
     * @param ordernotissued - 1 - the item has been added to the basket.
     * @return returns item marked addition to the basket
     */
    Basket orderNotIssued(Long id, Integer ordernotissued);

    /**
     * Specifies the goods already ordered by the user
     * The method is called after placing an order
     * @param id database id for chosen goods in basket
     * @param orderissued - 1 - the user has made an order for the selected goods
     * @return returns the goods of basket marked as ordered
     */
    Basket orderIssued(Long id, Integer orderissued);

}
