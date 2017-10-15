package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.CarsEntity;
import io.khasang.bazaar.entity.Cat;
import io.khasang.bazaar.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/cars")
public class CarsController {

    @Autowired
    private CarsService carsService;

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CarsEntity addCat(@RequestBody CarsEntity carsEntity) {
        return carsService.addCarsEntity(carsEntity);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<CarsEntity> getCats(){
        return carsService.getList();
    }
}
