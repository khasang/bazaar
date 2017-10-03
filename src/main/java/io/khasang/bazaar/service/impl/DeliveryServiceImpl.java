package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.DeliveryDao;
import io.khasang.bazaar.entity.Delivery;
import io.khasang.bazaar.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deliveryService")
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryDao deliveryDao;

    @Override
    public Delivery getById(Long id) {
        return deliveryDao.getById(id);
    }

    @Override
    public Delivery add(Delivery entity) {
        return deliveryDao.add(entity);
    }

    @Override
    public Delivery update(Delivery entity) {
        return deliveryDao.update(entity);
    }

    @Override
    public List<Delivery> getList() {
        return deliveryDao.getList();
    }

    @Override
    public List<Delivery> getByParam(String param) {
        return deliveryDao.getDeliveriesByRecipient(param);
    }

    @Override
    public Delivery deleteById(Long id) {
        Delivery delivery = deliveryDao.getById(id);
        return deliveryDao.delete(delivery);
    }
}
