package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.GoodsCategoryDao;
import io.khasang.bazaar.entity.GoodsCategory;

import java.util.List;

/**
 * Implementation of GoodsCategoryDao interface for representing DAO for Goods Category entity.
 * @author Zulfia Garifullina
 * @date 26.09.2017.
 */
public class GoodsCategoryDaoImpl extends BasicDaoImpl<GoodsCategory> implements GoodsCategoryDao {
    public GoodsCategoryDaoImpl(Class<GoodsCategory> entityClass) {
        super(entityClass);
    }

    @Override
    public List<GoodsCategory> getGoodsCategoriesByName(String name) {
        return (List<GoodsCategory>) sessionFactory.getCurrentSession().
                createQuery("from GoodsCategory as gc where gc.name = ?").setParameter(0, name).list();
    }
}