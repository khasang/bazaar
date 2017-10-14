package io.khasang.bazaar.controller;

import io.khasang.bazaar.entity.Seller;
import io.khasang.bazaar.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Seller entity.
 *
 * @author Zulfia Garifullina
 * @date 13.10.2017.
 */
@Controller
@RequestMapping(value = "/seller")
public class SellerController {
    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Seller getSellerById(@PathVariable(value = "id") String id) {
        return sellerService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/login/{login}", method = RequestMethod.GET)
    @ResponseBody
    public List<Seller> getSellerByLogin(@PathVariable(value = "login") String login) {
        return sellerService.getSellerByLogin(login);
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Seller addSeller(@RequestBody Seller seller) {
        return sellerService.addSeller(seller);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Seller updateSeller(@RequestBody Seller seller) {
        return sellerService.updateSeller(seller);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Seller deleteSeller(@RequestParam(value = "id") String id) {
        return sellerService.deleteSeller(Long.parseLong(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Seller> getSellers() {
        return sellerService.getList();
    }

    @RequestMapping(value = "/increase/balance", method = RequestMethod.POST, produces = "application/json;charset+utf-8")
    @ResponseBody
    public Seller increaseSellerBalance(@RequestParam(value = "id") String id, @RequestParam(value = "amount") String amount) {
        return sellerService.increaseSellerBalance(Long.parseLong(id), Integer.parseInt(amount));
    }
}
