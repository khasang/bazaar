package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.GoodsDao;
import io.khasang.bazaar.entity.Goods;

import java.util.List;

/**
 * Implementation of GoodsDao interface for representing DAO for Goods entity.
 *
 * @author Zulfia Garifullina
 * @date 04.10.2017.
 */
public class GoodsDaoImpl extends BasicDaoImpl<Goods> implements GoodsDao {
    public GoodsDaoImpl(Class<Goods> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Goods> getGoodsByName(String name) {
        return (List<Goods>) sessionFactory.getCurrentSession().
                createQuery("from Goods as g where g.name = ?").setParameter(0, name).list();
    }
}
