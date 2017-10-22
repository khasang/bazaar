package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Goods;

import java.util.List;

/**
 * Interface for representing DAO for Goods entity.
 *
 * @author Zulfia Garifullina
 * @date 04.10.2017.
 */
public interface GoodsDao extends BasicDao<Goods> {
    /**
     * Method for retrieving goods from the database by their name.
     *
     * @param name - name of the goods
     * @return goods with the given name
     */
    List<Goods> getGoodsByName(String name);
}
