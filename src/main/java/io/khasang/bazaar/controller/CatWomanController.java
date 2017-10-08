package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.CatWoman;
import io.khasang.bazaar.service.CatWomanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/cat_woman")
public class CatWomanController {
    private final CatWomanService catService;

    @Autowired
    public CatWomanController(CatWomanService catWomanService) {
        this.catService = catWomanService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CatWoman getCatById(@PathVariable(value = "id") String id) {
        return catService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CatWoman addCat(@RequestBody CatWoman catWoman) {
        return catService.add(catWoman);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CatWoman updateCat(@RequestBody CatWoman catWoman) {
        return catService.update(catWoman );
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public CatWoman deleteCat(@RequestParam(value = "id") String id){
        return catService.deleteById(Long.parseLong(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<CatWoman> getCats(){
        return catService.getList();
    }

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<CatWoman> getCatsByName(@PathVariable(value = "name") String name){
        return catService.getByParam(name);
    }
}
