package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.SupportDao;
import io.khasang.bazaar.entity.Support;
import io.khasang.bazaar.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deliveryService")
public class SupportServiceImpl implements SupportService {

    @Autowired
    SupportDao supportDao;

    @Override
    public Support getById(Long id) {
        return supportDao.getById(id);
    }

    @Override
    public Support add(Support entity) {
        return supportDao.add(entity);
    }

    @Override
    public Support update(Support entity) {
        return supportDao.update(entity);
    }

    @Override
    public List<Support> getList() {
        return supportDao.getList();
    }

    @Override
    public List<Support> getByParam(String param) {
        return supportDao.getSupportByCountRequests(param);
    }

    @Override
    public Support deleteById(Long id) {
        Support support = supportDao.getById(id);
        return supportDao.delete(support);
    }
}