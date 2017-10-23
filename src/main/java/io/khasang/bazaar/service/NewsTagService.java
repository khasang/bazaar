package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.NewsTag;

import java.util.List;

public interface NewsTagService {
    /**
     *
     * @param id
     * @return
     */
    NewsTag getById(Long id);

    /**
     *
     * @param newsTag
     * @return
     */
    NewsTag add(NewsTag newsTag);

    /**
     *
     * @return
     */
    List<NewsTag> getList();

    /**
     *
     * @param id
     * @return
     */

    NewsTag delete(Long id);

}
