package io.khasang.bazaar.dao;

import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {

    /**
     * Get current Hibernate session
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
    T add(T entity);

    /**
     * Create entity at database
     *
     * @param entity - entity for creation
     * @return entity
     */
    T update(T entity);

    /**
     * Delete entity from database
     *
     * @param entity - entity for deletion
     * @return entity
     */
    T delete(T entity);

    /**
     * Receive all entities from database
     *
     * @return all entities from database
     */
    List<T> getList();
}
