package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Support;
import io.khasang.bazaar.service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/support")
public class SupportController {

    private final SupportService supportService;

    @Autowired
    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Support getSupportById(@PathVariable(value = "id") String id){
        return supportService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Support addSupport(@RequestBody Support support){
        return supportService.add(support);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;carset=utf-8")
    @ResponseBody
    public Support updateSupport(@RequestBody Support support){
        return supportService.update(support);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Support deleteSupport(@RequestParam(value = "id") String id){
        return supportService.deleteById(Long.parseLong(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Support> getSupport(){
        return supportService.getList();
    }

    @RequestMapping(value = "/get/count_requests/{requests}", method = RequestMethod.GET)
    @ResponseBody
    public List<Support> getSupportByCountRequests(@PathVariable(value = "requests") String recipient){
        return supportService.getByParam(recipient);
    }
}