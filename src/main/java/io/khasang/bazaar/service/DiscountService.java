package io.khasang.bazaar.service;

import io.khasang.bazaar.entity.Discount;
import java.util.List;

/**
 * Created by Viktor on 04.10.2017.
 */
public interface DiscountService {

    /**
     * Receive Discount by id
     *
     * @param id - discount's id what we want to receive
     * @return discount
     */
    Discount getById(Long id);

    /**
     * Create discount at database
     *
     * @param discount - discount for creation
     * @return discount
     */
    Discount addDiscount(Discount discount);

    /**
     * Update discount at database
     *
     * @param discount - discount for creation
     * @return discount
     */
    Discount updateDiscount(Discount discount);

    /**
     * Receive all discounts from database
     *
     * @return all discounts from database
     */
    List<Discount> getList();

    /**
     * Receive discounts from database with specified promo code
     *
     * @param promoCode - discount's promo code
     * @return list of discounts with specified promo code
     */
    List<Discount> getDiscountsByPromoCode(String promoCode);

    /**
     * Receive list of actual discounts from database
     * @return list of today's actual discounts
     */
    List<Discount> getActualDiscounts();

    /**
     * Delete discount from database
     *
     * @param id - discount's id for delete
     * @return discount
     */
    Discount deleteDiscount(Long id);
}
