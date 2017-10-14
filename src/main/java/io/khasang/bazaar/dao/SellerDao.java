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
     * Method for retrieving seller from the database by their login.
     * @param login - login of the seller
     * @return seller with the given login
     */
    List<Seller> getSellerByLogin(String login);
}
