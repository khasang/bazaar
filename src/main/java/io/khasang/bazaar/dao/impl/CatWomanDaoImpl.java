package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.CatWomanDao;
import io.khasang.bazaar.entity.CatWoman;

import java.util.List;

public class CatWomanDaoImpl extends BasicDaoImpl<CatWoman> implements CatWomanDao {
    public CatWomanDaoImpl(Class<CatWoman> entityClass) {
        super(entityClass);
    }

    @Override
    public List<CatWoman> getCatsByName(String name) {
        return (List<CatWoman>) sessionFactory.getCurrentSession().
                createQuery("from CatWoman as c where c.name = ?").setParameter(0, name).list();
    }
}
