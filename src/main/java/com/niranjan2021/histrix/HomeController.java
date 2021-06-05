package com.niranjan2021.histrix;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private  GoodByeService goodByeService;


    @GetMapping("/goodbye")
    public  String goodByeServiceCall(){

     return    goodByeService.getGoodBye();
    }
}
