package io.khasang.bazaar.service;

import java.util.List;

public interface Service<T> {
    /**
     * Receive Entity by id
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
     * Update entity at database
     *
     * @param entity - entity for update
     * @return entity
     */
    T update(T entity);

    /**
     * Receive all entities from database
     *
     * @return all entities from database
     */
    List<T> getList();

    /**
     * Receive cats from database with specified name
     *
     * @param param - entities name
     * @return list of entities with specified param
     */
    List<T> getByParam(String param);

    /**
     * Delete entity from database by id
     *
     * @param id - entities id for delete
     * @return entity
     */
    T deleteById(Long id);
}