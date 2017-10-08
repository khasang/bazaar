package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.CatWomanDao;
import io.khasang.bazaar.entity.CatWoman;
import io.khasang.bazaar.service.CatWomanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("catWomanService")
public class CatWomanServiceImpl implements CatWomanService {
    @Autowired
    private CatWomanDao catWomanDao;

    @Override
    public List<CatWoman> getList() {
        return catWomanDao.getList();
    }

    @Override
    public List<CatWoman> getByParam(String name) {
        return catWomanDao.getCatsByName(name);
    }

    @Override
    public CatWoman deleteById(Long id) {
        CatWoman catWoman = catWomanDao.getById(id);
        return catWomanDao.delete(catWoman);
    }

    @Override
    public CatWoman add(CatWoman catWoman) {
        return catWomanDao.add(catWoman);
    }

    @Override
    public CatWoman update(CatWoman catWoman) {
        return catWomanDao.update(catWoman);
    }

    @Override
    public CatWoman getById(Long id) {
        return catWomanDao.getById(id);
    }
}
