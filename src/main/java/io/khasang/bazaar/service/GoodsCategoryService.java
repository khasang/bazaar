package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.GoodsCategory;

import java.util.List;

/**
 * Service interface for Goods Category entity.
 * @author Zulfia Garifullina
 * @date 26.09.2017.
 */
public interface GoodsCategoryService {
    /**
     * Method for retrieving goods categories by their database id.
     * @param id - id of the category in the database
     * @return a category of goods with given id
     */
    GoodsCategory getById(Long id);

    /**
     * Method for retrieving goods categories from the database by their name.
     * @param name - name of the category
     * @return all the goods categories with the given name
     */
    List<GoodsCategory> getGoodsCategoriesByName(String name);

    /**
     * Method for adding a new goods category to the database table
     * @param goodsCategory - the object being added to the database table as a row
     * @return the object that was added
     */
    GoodsCategory addGoodsCategory(GoodsCategory goodsCategory);

    /**
     * Method for updating a goods category in the database
     * @param goodsCategory - the object being updated in the database
     * @return the goods category that was updated
     */
    GoodsCategory updateGoodsCategory(GoodsCategory goodsCategory);

    /**
     * Deletes the goods category with the specified id from the database
     * @param id - database id of the goods category that needs to be deleted
     * @return the deleted goods category
     */
    GoodsCategory deleteGoodsCategory(Long id);

    /**
     * Method returns all the goods categories from the corresponding table in the database.
     * @return a List of goods categories objects from the database table.
     */
    List<GoodsCategory> getList();
}