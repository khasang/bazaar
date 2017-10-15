package io.khasang.bazaar.controller;

import io.khasang.bazaar.service.impl.InflationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/inflation") // http://localhost:8080/inflation/value?amount=900.00&start=2017.05&end=2017.09
public class InflationController {

    @Autowired
    private InflationService inflationService;

    @RequestMapping(value = "/value")
    public String getValueChange(@RequestParam(value = "start") String startMonth,
                          @RequestParam(value = "end") String endMonth,
                          @RequestParam(value = "amount") String startAmount,
                          Model model) {
        model.addAttribute("result_inflation", inflationService.getValueChange(startAmount, startMonth, endMonth));
        return "inflation";
    }

//    @RequestMapping(value = "/value")
//    @ResponseBody
//    String getValueChange(@RequestBody  ) {
//
//        return null;
//    }
}
