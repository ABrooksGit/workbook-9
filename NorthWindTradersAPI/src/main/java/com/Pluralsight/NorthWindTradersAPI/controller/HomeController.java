package com.Pluralsight.NorthWindTradersAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue = "World") String country){

        return "<h1> Hello and Welcome to the "  + country + "</h1> <p> We hope you enjoy your stay !</p>";

    }





}
