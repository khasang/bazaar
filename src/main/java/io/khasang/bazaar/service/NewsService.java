package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.News;
import io.khasang.bazaar.entity.NewsTag;

import java.util.List;
import java.util.Set;

public interface NewsService {
    /**
     *
     * @param id
     * @return
     */
    News getById(Long id);

    /**
     *
     * @param news
     * @return
     */
    News add(News news);

    /**
     *
     * @return
     */
    List<News> getList();

    /**
     *
     * @param id
     * @return
     */

    News delete(Long id);

    List<News> getNewsByTitle(String title);

    // Set<NewsTag> getNewsTagSet(Long id);
}
