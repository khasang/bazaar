package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.ProductDao;
import io.khasang.bazaar.entity.Product;

import java.util.List;

public class ProductDaoImpl extends BasicDaoImpl<Product> implements ProductDao{

    public ProductDaoImpl(Class<Product> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return (List<Product>) sessionFactory.getCurrentSession().
                createQuery("from Cat as c where c.name = ?").setParameter(0, name).list();
    }
}
