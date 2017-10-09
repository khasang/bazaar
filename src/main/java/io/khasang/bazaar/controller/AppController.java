package io.khasang.bazaar.controller;

import io.khasang.bazaar.dao.GoodsCategoryDao;
import io.khasang.bazaar.dao.GoodsDao;
import io.khasang.bazaar.dao.impl.GoodsCategoryDaoImpl;
import io.khasang.bazaar.dao.impl.GoodsDaoImpl;
import io.khasang.bazaar.entity.Goods;
import io.khasang.bazaar.entity.GoodsCategory;
import io.khasang.bazaar.model.CreateTable;
import io.khasang.bazaar.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/")
public class AppController {
    @Autowired
    @Value("Barsik")
    private Message message;

    @Autowired
    private CreateTable createTable;

    // http://localhost:8080/
    @RequestMapping("/")
    public String javaPageHello() {
        return "menu";
    }

    @RequestMapping("/create")
    public String createTable(Model model) {
        model.addAttribute("status", createTable.createStatus());
        // hello.jsp
        return "table";
    }

    @RequestMapping("/admin")
    public String getAdminInfo(Model model){
        model.addAttribute("secure", "It's very secure page!");
        return "admin";
    }

    @RequestMapping(value = {"/password/{password}"}, method = RequestMethod.GET)
    public ModelAndView passwordEncode(@PathVariable("password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("password");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(password));
        return modelAndView;
    }

    @Bean
    public GoodsCategoryDao goodsCategoryDao() {
        return new GoodsCategoryDaoImpl(GoodsCategory.class);
    }
    @Bean
    public GoodsDao goodsDao() {
        return new GoodsDaoImpl(Goods.class);
    }
}
