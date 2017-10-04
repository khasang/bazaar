package io.khasang.bazaar.dao;

import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {
    /**
     * Get current session for Hibernate
     * @return current session from Session
     */
    Session getCurrentSession();

    /**
     * Receive entity by ID
     * @param id
     * @return entity
     */
    T getById(Long id);

    /**
     * Add entity to the database table as a row
     * @param entity - the object being added to the database table as a row
     * @return the object that was added
     */
    T add(T entity);

    /**
     * Update entity in the database
     * @param entity - the object being updated in the database
     * @return the object that was updated
     */
    T update(T entity);

    /**
     * Deletes the specified entity from the database
     * @param entity - entity that needs to be deleted
     * @return the deleted entity
     */
    T delete(T entity);

    /**
     * Receive all the entities from the database
     * @return List of all entities from the database
     */
    List<T> getList();
}
