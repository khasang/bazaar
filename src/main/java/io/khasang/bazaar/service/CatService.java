package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.Cat;

import java.util.List;

public interface CatService {
    /**
     *
     * @param id
     * @return
     */
    Cat getById(Long id);

    /**
     *
     * @param cat
     * @return
     */
    Cat addCat(Cat cat);

    /**
     *
     * @return
     */
    List<Cat> getList();
}
