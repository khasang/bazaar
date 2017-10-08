package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.News;
import io.khasang.bazaar.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/news")
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController (NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public News getCatById(@PathVariable(value = "id") String id) {
        return newsService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public News addCat(@RequestBody News news) {
        return newsService.add(news);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<News> getCats() {
        return newsService.getList();
    }

}
