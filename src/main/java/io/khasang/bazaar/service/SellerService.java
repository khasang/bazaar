package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.Seller;

import java.util.List;

/**
 * Service interface for Seller entity.
 *
 * @author Zulfia Garifullina
 * @date 13.10.2017.
 */
public interface SellerService {
    /**
     * Method for retrieving seller by their database id.
     * @param id - id of the seller in the database
     * @return seller with given id
     */
    Seller getById(Long id);

    /**
     * Method for retrieving seller from the database by their login.
     * @param login - login of the seller
     * @return seller with the given login
     */
    List<Seller> getSellerByLogin(String login);

    /**
     * Method for adding new seller to the database table
     * @param seller the object being added to the database table as a row
     * @return the seller that was added
     */
    Seller addSeller(Seller seller);

    /**
     * Method for updating seller in the database
     * @param seller the object being updated in the database
     * @return the seller that was updated
     */
    Seller updateSeller(Seller seller);

    /**
     * Deletes the seller with the specified id from the database
     * @param id database id of the seller that needs to be deleted
     * @return the deleted seller
     */
    Seller deleteSeller(Long id);

    /**
     * Method returns all the sellers from the corresponding table in the database.
     * @return a List of seller objects from the database table.
     */
    List<Seller> getList();

    /**
     * Increases the seller's balance by a given amount as a result of paying for an order of seller's goods
     * @param id database id of the seller
     * @param amount by which the balance will be increased
     * @return seller whose balance was increased by the given amount
     */
    Seller increaseSellerBalance(Long id, Integer amount);
}
