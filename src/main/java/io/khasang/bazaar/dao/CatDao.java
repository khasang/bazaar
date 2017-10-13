package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Cat;
import java.util.List;

public interface CatDao extends BasicDao<Cat> {

    /**
     * Receive cats from database with specified name
     *
     * @param name - cat's name
     * @return list of cats with specified name
     */
    List<Cat> getCatsByName(String name);
}
