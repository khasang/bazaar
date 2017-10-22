package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.Goods;

import java.util.List;

/**
 * Service interface for Goods entity.
 *
 * @author Zulfia Garifullina
 * @date 04.10.2017.
 */
public interface GoodsService {
    /**
     * Method for retrieving goods by their database id.
     *
     * @param id - id of the goods in the database
     * @return goods with given id
     */
    Goods getById(Long id);

    /**
     * Method for retrieving goods from the database by their name.
     *
     * @param name - name of the goods
     * @return all the goods with the given name
     */
    List<Goods> getGoodsByName(String name);

    /**
     * Method for adding new goods to the database table
     *
     * @param goods- the object being added to the database table as a row
     * @return the goods that was added
     */
    Goods addGoods(Goods goods);

    /**
     * Method for updating goods in the database
     *
     * @param goods - the object being updated in the database
     * @return the goods that were updated
     */
    Goods updateGoods(Goods goods);

    /**
     * Deletes the goods with the specified id from the database
     *
     * @param id database id of the goods that needs to be deleted
     * @return the deleted goods
     */
    Goods deleteGoods(Long id);

    /**
     * Method returns all the goods from the corresponding table in the database.
     *
     * @return a List of goods objects from the database table.
     */
    List<Goods> getList();

    /**
     * Reserves a specified amount of goods and reduces the amount in stock by that number.
     * This method should be called before processing order payment.
     *
     * @param id       database id for chosen goods
     * @param quantity quantity to be reserved
     * @return goods that were reserved
     */
    Goods reserveGoods(Long id, Integer quantity);

    /**
     * Cancels reservation on a specified amount of goods and increases the amount in stock by that number.
     * This method should be called if processing order payment failed.
     *
     * @param id       database id for chosen goods
     * @param quantity quantity to be removed from reserved and added back to quantity in stock
     * @return goods that were removed from reserved and added back to quantity in stock
     */
    Goods unreserveGoods(Long id, Integer quantity);

    /**
     * Reduces the amount of goods which were reserved before payment processing, by the specified amount.
     * This method should be called after successful processing of order payment.
     *
     * @param id       database id for chosen goods
     * @param quantity amount by which reserved quantity is reduced
     * @return goods for which reserved quantity was reduced
     */
    Goods buyGoods(Long id, Integer quantity);
}
