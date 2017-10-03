package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.GoodsCategory;
import io.khasang.bazaar.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Goods Category entity.
 * @author Zulfia Garifullina
 * @date 26.09.2017.
 */
@Controller
@RequestMapping(value = "/goodscategories")
public class GoodsCategoryController {
    private final GoodsCategoryService goodsCategoryService;

    @Autowired
    public GoodsCategoryController(GoodsCategoryService goodsCategoryService) {
        this.goodsCategoryService = goodsCategoryService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GoodsCategory getGoodsCategoryById(@PathVariable(value = "id") String id) {
        return goodsCategoryService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public GoodsCategory addGoodsCategory(@RequestBody GoodsCategory goodsCategory) {
        return goodsCategoryService.addGoodsCategory(goodsCategory);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public GoodsCategory updateGoodsCategory(@RequestBody GoodsCategory goodsCategory) {
        return goodsCategoryService.updateGoodsCategory(goodsCategory);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public GoodsCategory deleteGoodsCategory(@RequestParam(value = "id") String id) {
        return goodsCategoryService.deleteGoodsCategory(Long.parseLong(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsCategory> getGoodsCategories() {
        return goodsCategoryService.getList();
    }

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<GoodsCategory> getGoodsCategoriesByName(@PathVariable(value = "name") String name) {
        return goodsCategoryService.getGoodsCategoriesByName(name);
    }
}