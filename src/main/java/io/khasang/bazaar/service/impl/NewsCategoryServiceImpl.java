package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.dao.NewsCategoryDao;
import io.khasang.bazaar.entity.NewsCategory;
import io.khasang.bazaar.service.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("newCategoryService")
public class NewsCategoryServiceImpl implements NewsCategoryService {
    @Autowired
    private NewsCategoryDao newsCategoryDao;

    @Override
    public NewsCategory getById(Long id) {
        return newsCategoryDao.getById(id);
    }

    @Override
    public NewsCategory add(NewsCategory newsCategory) {
        return newsCategoryDao.add(newsCategory);
    }

    @Override
    public List<NewsCategory> getList() {
        return newsCategoryDao.getList();
    }

    @Override
    public NewsCategory delete(Long id) {
        NewsCategory newsCategory = newsCategoryDao.getById(id);
        return newsCategoryDao.delete(newsCategory);
    }

}
