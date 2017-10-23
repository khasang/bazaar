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
    public List<Seller> getSellerByLogin(String login) {

        return (List<Seller>) getCurrentSession().createQuery("from Seller where login = ?").setParameter(0, login).list();
    }
}