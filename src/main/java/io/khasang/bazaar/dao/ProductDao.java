package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Product;

import java.util.List;

public interface ProductDao extends BasicDao<Product> {
    /**
     * Receive products from database with specified name
     *
     * @param name - product's name
     * @return list of product's with specified name
     */
    List<Product> getProductsByName(String name);
}
