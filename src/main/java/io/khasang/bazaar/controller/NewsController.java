package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.News;
import io.khasang.bazaar.entity.NewsTag;
import io.khasang.bazaar.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/news")
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public News getById(@PathVariable(value = "id") String id) {
        return newsService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/title/{title}", method = RequestMethod.GET)
    @ResponseBody
    public List<News> getNewsByTitle(@PathVariable(value = "title") String title) {
        return newsService.getNewsByTitle(title);
    }

//    @RequestMapping(value = "/{id}/tags", method = RequestMethod.GET)
//    @ResponseBody
//    public Set<NewsTag> getNewsTagSet(@PathVariable(value = "id") String id) {
//        return newsService.getNewsTagSet(Long.parseLong(id));
//    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public News addNews(@RequestBody News news) {
        return newsService.add(news);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<News> getNews() {
        return newsService.getList();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public News delete(@RequestParam(value = "id") String id) {
        return newsService.delete(Long.parseLong(id));
    }

}
