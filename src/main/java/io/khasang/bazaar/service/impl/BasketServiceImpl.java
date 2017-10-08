package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.BasketDao;
import io.khasang.bazaar.entity.Basket;
import io.khasang.bazaar.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BasketService")
public class BasketServiceImpl implements BasketService {
    @Autowired
    private BasketDao basketDао;

    @Override
    public Basket getBasketById(Long id) {
        return (Basket) basketDао.getById(id);
    }

    @Override
    public List<Basket> getBaskets() {
        return basketDао.getList();
    }

    @Override
    public Basket addGoodsInBasket(Basket basket) {
        return (Basket) basketDао.add(basket);
    }

    @Override
    public Basket updateBasket(Basket basket) {
        return (Basket) basketDао.update(basket);
    }

    @Override
    public Basket deleteGoodsInBasket(Long id) {
        return (Basket) basketDао.delete(id);
    }

    @Override
    public Basket orderNotIssued(Long id, Integer ordernotissued) {
        Basket basket = (Basket) basketDао.getById(id);
        basket.setOrdernotissued(basket.getOrdernotissued() + ordernotissued);
        return (Basket) basketDао.update(basket);
    }

    @Override
    public Basket orderIssued(Long id, Integer orderissued) {
        Basket basket = (Basket) basketDао.getById(id);
        basket.setOrdernotissued(basket.getOrdernotissued() - orderissued);
        basket.setOrderissued(basket.getOrderissued() + orderissued);
        return (Basket) basketDао.update(basket);
    }

}
