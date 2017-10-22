package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.NewsTag;
import io.khasang.bazaar.service.NewsTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/tags")
public class NewsTagController {
    private final NewsTagService newsTagService;

    @Autowired
    public NewsTagController (NewsTagService newsService) {
        this.newsTagService = newsService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public NewsTag getNewsTagById(@PathVariable(value = "id") String id) {
        return newsTagService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public NewsTag addNewsTag(@RequestBody NewsTag newsTag) {
        return newsTagService.add(newsTag);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<NewsTag> getNewsTag() {
        return newsTagService.getList();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public NewsTag delete(@RequestParam(value = "id") String id){
        return newsTagService.delete(Long.parseLong(id));
    }

}
