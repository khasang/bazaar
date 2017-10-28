package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.NewsCategoryDao;
import io.khasang.bazaar.entity.NewsCategory;

import java.util.List;

public class NewsCategoryDaoImpl extends BasicDaoImpl<NewsCategory> implements NewsCategoryDao {

    public NewsCategoryDaoImpl(Class<NewsCategory> entityClass) {
        super(entityClass);
    }

}
