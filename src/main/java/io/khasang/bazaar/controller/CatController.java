package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Cat;
import io.khasang.bazaar.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/cat")
public class CatController {
    private final CatService catService;

    @Autowired
    public CatController (CatService catService) {
        this.catService = catService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Cat getCatById(@PathVariable(value = "id") String id) {
        return catService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Cat addCat(@RequestBody Cat cat) {
        return catService.addCat(cat);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Cat> getCats() {
        return catService.getList();
    }
}
