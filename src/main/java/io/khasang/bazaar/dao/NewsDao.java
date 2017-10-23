package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.News;

import java.util.List;

public interface NewsDao extends BasicDao<News> {

    List<News> getNewsByName(String name);

}
