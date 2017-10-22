package io.khasang.bazaar.service.impl;

import io.khasang.bazaar.entity.News;
import io.khasang.bazaar.entity.NewsTag;
import io.khasang.bazaar.service.NewsService;
import io.khasang.bazaar.dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service("newsService")
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
        return newsDao.getList();
    }

    @Override
    public News delete(Long id) {
        News news = newsDao.getById(id);
        return newsDao.delete(news);
    }

    @Override
    public List<News> getNewsByTitle(String title) {
        return newsDao.getNewsByTitle(title);
    }

    //    @Override
//    public Set<NewsTag> getNewsTagSet(Long id) {
//        return null;
//    }

}
