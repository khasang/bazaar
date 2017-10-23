package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.entity.News;
import io.khasang.bazaar.service.NewsService;
import io.khasang.bazaar.dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("newsCategoryService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public News getById(Long id) {
        return newsDao.getById(id);
    }

    @Override
    public News add(News news) {
        return newsDao.add(news);
    }

    @Override
    public List<News> getList() {
        return null;
    }

    @Override
    public News delete(Long id) {
        return null;
    }
}
