package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.SupportDao;
import io.khasang.bazaar.entity.Support;

import java.util.List;

public class SupportDaoImpl extends BasicDaoImpl<Support> implements SupportDao {

    public SupportDaoImpl(Class<Support> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Support> getSupportByCountRequests(String countRequests) {
        return (List<Support>) sessionFactory.getCurrentSession().
                createQuery("from Support as s where s.countRequests = ?").setParameter(0, countRequests).list();
    }
}