package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.Cat;

import java.util.List;

public interface CatService {

    /**
     * Receive Cat by id
     *
     * @param id - cat's id what we want to receive
     * @return cat
     */
    Cat getById(Long id);

    /**
     * Create cat at database
     *
     * @param cat - cat for creation
     * @return cat
     */
    Cat addCat(Cat cat);

    /**
     * Update cat at database
     *
     * @param cat - cat for creation
     * @return cat
     */
    Cat updateCat(Cat cat);

    /**
     * Receive all cats from database
     *
     * @return all cats from database
     */
    List<Cat> getList();

    /**
     * Receive cats from database with specified name
     *
     * @param name - cat's name
     * @return list of cats with specified name
     */
    List<Cat> getCatsByName(String name);

    /**
     * Delete cat from database
     *
     * @param id - cat's id for delete
     * @return cat
     */
    Cat deleteCat(Long id);
}
