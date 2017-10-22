package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.CatDao;
import io.khasang.bazaar.entity.Cat;

public class CatDaoImpl extends BasicDaoImpl<Cat> implements CatDao {
    public CatDaoImpl(Class<Cat> entityClass) {
        super(entityClass);
    }
}
