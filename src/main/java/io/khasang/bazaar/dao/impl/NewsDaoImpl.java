package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.NewsDao;
import io.khasang.bazaar.entity.News;
import io.khasang.bazaar.entity.NewsTag;

import java.util.List;
import java.util.Set;

public class NewsDaoImpl extends BasicDaoImpl<News> implements NewsDao {

    public NewsDaoImpl(Class<News> entityClass) {
        super(entityClass);
    }


    @Override
    public List<News> getNewsByTitle(String name) {
        return (List<News>) sessionFactory.getCurrentSession().
                createQuery("from News as n where n.title = ?").setParameter(0, name).list();
    }

    @Override
    public List<NewsTag> getNewsTagsList(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from NewsTag as nt where nt.id = :id", NewsTag.class)
                .setParameter("id", id)
                .getResultList();

    }


}
