package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.NewsCategory;
import io.khasang.bazaar.service.NewsCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/news_cat")
public class NewsCategoryController {

    private final NewsCategoryService newsCategoryService;

    @Autowired
    public NewsCategoryController(NewsCategoryService newsCategoryService) {
        this.newsCategoryService = newsCategoryService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public NewsCategory getById(@PathVariable(value = "id") String id) {
        return newsCategoryService.getById(Long.parseLong(id));
    }


    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public NewsCategory addNewsCategory(@RequestBody NewsCategory newsCategory) {
        return newsCategoryService.add(newsCategory);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<NewsCategory> getNewsCategoryies() {
        return newsCategoryService.getList();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public NewsCategory delete(@RequestParam(value = "id") String id) {
        return newsCategoryService.delete(Long.parseLong(id));
    }

}

