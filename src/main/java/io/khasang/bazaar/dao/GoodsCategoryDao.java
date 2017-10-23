package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.GoodsCategory;

import java.util.List;

/**
 * Interface for representing DAO for GoodsCategory entity.
 * @author Zulfia Garifullina
 * @date 26.09.2017.
 */
public interface GoodsCategoryDao extends BasicDao<GoodsCategory> {
    /**
     * Method for retrieving goods categories from the database by their name.
     * @param name - name of the category
     * @return - returns all the goods categories with the given name
     */
    List<GoodsCategory> getGoodsCategoriesByName(String name);
}