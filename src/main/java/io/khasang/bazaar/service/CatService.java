package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.Cat;

import java.util.List;

public interface CatService {

    /**
     *
     * */
    Cat getById(Long id);

    /**
     *
     * */
    Cat addCat(Cat cat);

    /**
     *
     * */
    List<Cat> getList();
}
