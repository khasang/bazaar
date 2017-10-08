package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.DiscountDao;
import io.khasang.bazaar.entity.Discount;
import io.khasang.bazaar.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("discountService")
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountDao discountDao;

    @Override
    public List<Discount> getList() {
        return discountDao.getList();
    }

    @Override
    public List<Discount> getDiscountsByPromoCode(String promoCode) {
        return discountDao.getDiscountsByPromoCode(promoCode);
    }

    @Override
    public List<Discount> getActualDiscounts() {
        return discountDao.getActualDiscounts();
    }

    @Override
    public Discount deleteDiscount(Long id) {
        Discount discount = discountDao.getById(id);
        return discountDao.delete(discount);
    }

    @Override
    public Discount addDiscount(Discount discount) {
        return discountDao.add(discount);
    }

    @Override
    public Discount updateDiscount(Discount discount) {
        return discountDao.update(discount);
    }

    @Override
    public Discount getById(Long id) {
        return discountDao.getById(id);
    }
}