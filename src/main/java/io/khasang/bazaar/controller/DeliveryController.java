package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Delivery;
import io.khasang.bazaar.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Delivery getDeliveryById(@PathVariable(value = "id") String id){
        return deliveryService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Delivery addDelivery(@RequestBody Delivery delivery){
        return deliveryService.add(delivery);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;carset=utf-8")
    @ResponseBody
    public Delivery updateDelivery(@RequestBody Delivery delivery){
        return deliveryService.update(delivery);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Delivery deleteDelivery(@RequestParam(value = "id") String id){
        return deliveryService.deleteById(Long.parseLong(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Delivery> getDeliveries(){
        return deliveryService.getList();
    }

    @RequestMapping(value = "/get/recipient/{recipient}", method = RequestMethod.GET)
    @ResponseBody
    public List<Delivery> getDeliveriesByName(@PathVariable(value = "recipient") String recipient){
        return deliveryService.getByParam(recipient);
    }
}
