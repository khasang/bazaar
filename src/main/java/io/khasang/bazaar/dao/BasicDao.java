package io.khasang.bazaar.dao;

import org.hibernate.Session;

import java.util.List;

public interface BasicDao<T> {

    /**
     *
     * */
    Session getCurrentSession();

    /**
     *
     * */
    T getById(Long id);

    /**
     *
     * */
    T addCat(T entity);

    /**
     *
     * */
    List<T> getList();
}
