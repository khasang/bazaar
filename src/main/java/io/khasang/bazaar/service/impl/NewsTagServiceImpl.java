package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.entity.NewsTag;
import io.khasang.bazaar.dao.NewsTagDao;
import io.khasang.bazaar.service.NewsTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("newsTagService")
public class NewsTagServiceImpl implements NewsTagService {
    @Autowired
    private NewsTagDao newsTagDao;

    @Override
    public NewsTag getById(Long id) {
        return newsTagDao.getById(id);
    }

    @Override
    public NewsTag add(NewsTag newsTag) {
        return newsTagDao.add(newsTag);
    }

    @Override
    public List<NewsTag> getList() {
        return null;
    }

    @Override
    public NewsTag delete(Long id) {
        return null;
    }
}
