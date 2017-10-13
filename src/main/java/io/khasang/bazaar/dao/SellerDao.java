package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Seller;

import java.util.List;

/**
 * Interface for representing DAO for Seller entity.
 *
 * @author Zulfia Garifullina
 * @date 13.10.2017.
 */
public interface SellerDao extends BasicDao<Seller> {
    /**
     * Method for retrieving sellers from the database by their name.
     * @param name - name of the sellers
     * @return sellers with the given name
     */
    List<Seller> getSellersByName(String name);
}
