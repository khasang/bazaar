package io.khasang.bazaar.controller;

import io.khasang.bazaar.model.*;
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

    @Autowired
    private PopulateTable populateTable;

    @Autowired
    private SelectFromTable selectFromTable;

    @Autowired
    private UpdateTable updateTable;

    @Autowired
    private DeleteFromTable deleteFromTable;

    @Autowired
    private JoinTable joinTable;

    @Autowired
    private SubqueryTable subqueryTable;

    @Autowired
    private CaseWhenTable caseWhenTable;

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

    @RequestMapping("/populate")
    public String populateTable(Model model) {
        model.addAttribute("status", populateTable.populateStatus());
        // table.jsp
        return "table";
    }

    @RequestMapping("/select")
    public String selectFromTable(Model model) {
        model.addAttribute("status", selectFromTable.selectStatus());
        // table.jsp
        return "table";
    }

    @RequestMapping("/update")
    public String updateTable(Model model) {
        model.addAttribute("status", updateTable.updateStatus());
        // table.jsp
        return "table";
    }

    @RequestMapping("/delete")
    public String deleteFromTable(Model model) {
        model.addAttribute("status", deleteFromTable.deleteStatus());
        // table.jsp
        return "table";
    }

    @RequestMapping("/join")
    public String joinTable(Model model) {
        model.addAttribute("status", joinTable.joinStatus());
        // table.jsp
        return "table";
    }

    @RequestMapping("/subquery")
    public String subqueryTable(Model model) {
        model.addAttribute("status", subqueryTable.subqueryStatus());
        // table.jsp
        return "table";
    }

    @RequestMapping("/casewhen")
    public String caseWhenTable(Model model) {
        model.addAttribute("status", caseWhenTable.caseWhenStatus());
        // table.jsp
        return "table";
    }

    @RequestMapping("/admin")
    public String getAdminInfo(Model model) {
        model.addAttribute("secure", "It's a very secure page");
        // admin.jsp
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
