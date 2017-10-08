package io.khasang.bazaar.controller;


import io.khasang.bazaar.entity.Discount;
import io.khasang.bazaar.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Viktor on 04.10.2017.
 */
@Controller
@RequestMapping(value = "/discount")
public class DiscountController {
    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService){
        this.discountService = discountService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Discount getDiscountById(@PathVariable(value = "id") String id) {
        return discountService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Discount addDiscount(@RequestBody Discount discount) {
        return discountService.addDiscount(discount);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Discount updateDiscount(@RequestBody Discount discount) {
        return discountService.updateDiscount(discount);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Discount deleteDiscount(@RequestParam(value = "id") String id){
        return discountService.deleteDiscount(Long.parseLong(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Discount> getDiscounts(){
        return discountService.getList();
    }

    @RequestMapping(value = "/get/actual", method = RequestMethod.GET)
    @ResponseBody
    public List<Discount> getActualDiscounts(){
        return discountService.getActualDiscounts();
    }

    @RequestMapping(value = "/get/code/{promo_code}", method = RequestMethod.GET)
    @ResponseBody
    public List<Discount> getDiscountsByPromoCode(@PathVariable(value = "promo_code") String promoCode){

        return discountService.getDiscountsByPromoCode(promoCode);
    }

}

