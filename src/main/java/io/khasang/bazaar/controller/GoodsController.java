package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Goods;
import io.khasang.bazaar.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Goods entity.
 *
 * @author Zulfia Garifullina
 * @date 04.10.2017.
 */
@Controller
@RequestMapping(value = "/goods")
public class GoodsController {
    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Goods getGoodsById(@PathVariable(value = "id") String id) {
        return goodsService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Goods> getGoodsByName(@PathVariable(value = "name") String name) {
        return goodsService.getGoodsByName(name);
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Goods addGoods(@RequestBody Goods goods) {
        return goodsService.addGoods(goods);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Goods updateGoods(@RequestBody Goods goods) {
        return goodsService.updateGoods(goods);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Goods deleteGoods(@RequestParam(value = "id") String id) {
        return goodsService.deleteGoods(Long.parseLong(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Goods> getGoods() {
        return goodsService.getList();
    }

    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    @ResponseBody
    public Goods reserveGoods(@RequestParam(value = "id") String id, @RequestParam(value = "quantity") String quantity) {
        return goodsService.reserveGoods(Long.parseLong(id), Integer.parseInt(quantity));
    }

    @RequestMapping(value = "/unreserve", method = RequestMethod.POST)
    @ResponseBody
    public Goods unreserveGoods(@RequestParam(value = "id") String id, @RequestParam(value = "quantity") String quantity) {
        return goodsService.unreserveGoods(Long.parseLong(id), Integer.parseInt(quantity));
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    @ResponseBody
    public Goods buyGoods(@RequestParam(value = "id") String id, @RequestParam(value = "quantity") String quantity) {
        return goodsService.buyGoods(Long.parseLong(id), Integer.parseInt(quantity));
    }
}