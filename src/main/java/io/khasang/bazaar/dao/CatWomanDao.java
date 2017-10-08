package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.CatWoman;

import java.util.List;

public interface CatWomanDao extends BasicDao<CatWoman> {

    /**
     * Receive woman_cats from database with specified name
     *
     * @param name - woman_cat's name
     * @return list of cats with specified name
     */
    List<CatWoman> getCatsByName(String name);
}
