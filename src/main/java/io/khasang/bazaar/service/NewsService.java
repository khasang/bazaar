package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.News;
import java.util.List;

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

}
