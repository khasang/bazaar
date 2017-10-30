package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.CatDao;
import io.khasang.bazaar.entity.Cat;

import java.util.List;

/**
 * Implementation of {@link CatDao} interface
 *
 * @author Sergey Khuznecov (Khasang.io)
 * @version 1.0
 */
public class CatDaoImpl extends BasicDaoImpl<Cat> implements CatDao {
    public CatDaoImpl(Class<Cat> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Cat> getCatsByName(String name) {
        return (List<Cat>) sessionFactory.getCurrentSession().
                createQuery("from Cat as c where c.name = ?").setParameter(0, name).list();
    }
}
