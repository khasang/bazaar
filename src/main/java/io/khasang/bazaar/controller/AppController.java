package io.khasang.bazaar.controller;

import io.khasang.bazaar.model.InsertTable;
import io.khasang.bazaar.model.JoinTable;
import io.khasang.bazaar.model.Message;
import io.khasang.bazaar.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/")
public class AppController {
    @Autowired
    @Value("Barsik")
    private Message message;

    @Autowired
    private CreateTable createTable;

    @Autowired
    private InsertTable insertTable;

    @Autowired
    private JoinTable joinTable;

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
        return "table";
    }

    @RequestMapping("/insert")
    public String insertTable(Model model) {
        model.addAttribute("insert", insertTable.createInsert());
        return "insert";
    }

    @RequestMapping("/join")
    public String joinTable(Model model) {
        model.addAttribute("join", joinTable.createJoin());
        return "join";
    }

}
