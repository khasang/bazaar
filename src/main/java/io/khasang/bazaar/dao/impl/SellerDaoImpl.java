package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.SellerDao;
import io.khasang.bazaar.entity.Seller;

import java.util.List;

/**
 * Implementation of SellerDao interface for representing DAO for Seller entity.
 *
 * @author Zulfia Garifullina
 * @date 13.10.2017.
 */
public class SellerDaoImpl extends BasicDaoImpl<Seller> implements SellerDao {
    public SellerDaoImpl(Class<Seller> entityClass) {
        super(entityClass);
    }

    @Override
    public List<Seller> getSellersByName(String name) {
        return (List<Seller>) getCurrentSession().createQuery("from Seller as s where s.name = ?").setParameter(0, name).list();
    }
}
