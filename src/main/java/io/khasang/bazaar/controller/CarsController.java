package io.khasang.bazaar.controller;

import io.khasang.bazaar.dto.CarsEmployeeDto;
import io.khasang.bazaar.entity.CarsEntity;
import io.khasang.bazaar.entity.Cat;
import io.khasang.bazaar.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/cars")
public class CarsController {

    @Autowired
    private CarsService carsService;
    @Autowired
    private CarsEmployeeDto carsEmployeeDto;

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public CarsEntity addCar(@RequestBody CarsEntity carsEntity) {
        return carsService.addCarsEntity(carsEntity);
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CarsEmployeeDto getCarsEntityById(@PathVariable(value = "id") String id) {
        return carsEmployeeDto.getCarsEmployeeDto(carsService.getById(Long.parseLong(id)));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<CarsEntity> getCars(){
        return carsService.getList();
    }
}
