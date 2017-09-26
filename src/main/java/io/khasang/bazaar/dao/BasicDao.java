package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Cat;
import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {

    /**
     * get current session
     *
     * @return session
     */
    Session getCurrentSession();

    /**
     * Receive entity by id
     *
     * @param id - entities id what we want to receive
     * @return entity
     */
    T getById(Long id);

    /**
     * Create entity at database
     *
     * @param entity - entity for creation
     * @return entity
     */
    T addCat(T entity);

    /**
     * Create entity at database
     *
     * @param entity - entity for creation
     * @return entity
     */
    T updateCat(T entity);

    /**
     * Receive all entities from database
     *
     * @return all entities from database
     */
    List<T> getList();

    /**
     * Delete entity from database
     *
     * @param entity - entity for deletion
     * @return entity
     */
    T delete(T entity);
}
