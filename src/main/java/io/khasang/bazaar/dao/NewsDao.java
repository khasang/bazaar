package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.News;
import io.khasang.bazaar.entity.NewsTag;

import java.util.List;
import java.util.Set;

public interface NewsDao extends BasicDao<News> {

    List<News> getNewsByTitle(String name);

    List<NewsTag> getNewsTagsList(Long id);
}
