package io.khasang.bazaar.controller;

import io.khasang.bazaar.model.CreateTable;
import io.khasang.bazaar.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public String javaPageHello(Model model) {
        model.addAttribute("name", message.getName());
        // hello.jsp
        return "hello";
    }

    @RequestMapping("/create")
    public String createTable(Model model) {
        model.addAttribute("status", createTable.createStatus());
        // table.jsp
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

}
