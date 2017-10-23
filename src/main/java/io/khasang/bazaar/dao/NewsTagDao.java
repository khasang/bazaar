package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.NewsTag;

import java.util.List;

public interface NewsTagDao extends BasicDao<NewsTag> {

    List<NewsTag> getNewsTagsByName(String name);
}
