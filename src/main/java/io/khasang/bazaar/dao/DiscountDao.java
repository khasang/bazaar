package io.khasang.bazaar.dao;

import io.khasang.bazaar.entity.Discount;
import java.util.List;

/**
 * Created by Viktor on 04.10.2017.
 */
public interface DiscountDao extends BasicDao<Discount> {
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
}
