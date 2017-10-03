package io.khasang.bazaar.controller;


import io.khasang.bazaar.entity.Product;
import io.khasang.bazaar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
    }

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProductById(@PathVariable(value = "id") String id){
        return productService.getById(Long.parseLong(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    Product addProduct(@RequestBody Product product){
        return productService.add(product);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Product updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Product deleteProduct(@RequestParam(value = "id") String id){
        return productService.deleteById(Long.parseLong(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProducts(){
        return productService.getList();
    }

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProductsByName(@PathVariable(value = "name") String name){
        return productService.getByParam(name);
    }

}
