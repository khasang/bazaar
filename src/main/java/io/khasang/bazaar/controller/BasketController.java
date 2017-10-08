package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Basket;
import io.khasang.bazaar.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер управления корзиной.
 */
@Controller
@RequestMapping(value = "/basket")
@Secured({"ROLE_USER","ROLE_ADMIN"})

public class BasketController {
    private BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Basket getBasketById (@PathVariable(value = "id")String id) {
        return basketService.getBasketById(Long.parseLong(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Basket> getBaskets() {
        return basketService.getBaskets();
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Basket addGoodsInBasket(@RequestBody Basket basket) {
        return basketService.addGoodsInBasket(basket);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Basket updateBasket(@RequestBody Basket basket) {
        return basketService.updateBasket(basket);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Basket deleteGoodsInBasket(@RequestParam(value = "id") String id) {
        return basketService.deleteGoodsInBasket(Long.parseLong(id));
    }

    @RequestMapping(value = "/orderNotIssued", method = RequestMethod.POST)
    @ResponseBody
    public Basket orderNotIssued (@RequestParam(value = "id") String id, @RequestParam(value = "ordernotissued") String ordernotissued) {
        return basketService.orderNotIssued(Long.parseLong(id), Integer.parseInt(ordernotissued));
    }

    @RequestMapping(value = "/orderIssued", method = RequestMethod.POST)
    @ResponseBody
    public Basket orderIssued (@RequestParam(value = "id") String id, @RequestParam(value = "orderissued") String orderissued) {
        return basketService.orderIssued(Long.parseLong(id), Integer.parseInt(orderissued));
    }
}
