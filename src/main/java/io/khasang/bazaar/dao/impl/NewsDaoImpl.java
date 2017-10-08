package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.NewsDao;
import io.khasang.bazaar.entity.News;

import java.util.List;

public class NewsDaoImpl extends BasicDaoImpl<News> implements NewsDao{

    public NewsDaoImpl(Class<News> entityClass) {
        super(entityClass);
    }


    @Override
    public List<News> getNewsByName(String name) {
        return (List<News>) sessionFactory.getCurrentSession().
                createQuery("from News as n where n.title = ?").setParameter(0, name).list();
    }

}
