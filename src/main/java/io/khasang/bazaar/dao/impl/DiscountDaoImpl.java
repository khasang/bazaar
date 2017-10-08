package io.khasang.bazaar.dao.impl;

import io.khasang.bazaar.dao.DiscountDao;
import io.khasang.bazaar.entity.Discount;
import java.sql.Date;
import java.util.List;

/**
 * Created by Viktor on 04.10.2017.
 */
public class DiscountDaoImpl extends BasicDaoImpl<Discount> implements DiscountDao {

    public DiscountDaoImpl(Class<Discount> entityClass) {
        super(entityClass);
    }


    @Override
    public List<Discount> getDiscountsByPromoCode(String promoCode1) {
        return (List<Discount>) sessionFactory.getCurrentSession().
                createQuery("from Discount as c where (c.promoCode = :code)").setParameter("code", promoCode1).list();
    }

    @Override
    public List<Discount> getActualDiscounts() {

       return (List<Discount>)sessionFactory.getCurrentSession().
               createQuery("from Discount as c where (c.startDate <= :date and c.endDate >= :date)").setParameter("date", new Date(new java.util.Date().getTime())).list();
    }


}
