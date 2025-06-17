package com.Pluralsight.NorthWindTradersAPI.controller;

import com.Pluralsight.NorthWindTradersAPI.dao.CategoryDAO;
import com.Pluralsight.NorthWindTradersAPI.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CategoriesController {

    private CategoryDAO categoryDAO;

    @Autowired
    public CategoriesController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @RequestMapping(path = {"/Categories", "/Categories/"}, method = RequestMethod.GET)
    public List<Category> getCategories(){

        return categoryDAO.getAllCategories();


    }





    @RequestMapping(path = "/Categories/id={id}, /Categories/id={id}/", method = RequestMethod.GET)
    public Category getCategories(@PathVariable int id){

       return categoryDAO.getCategoryByID(id);


    }


    @RequestMapping(path = "/Categories/name={name}, /Categories/name={name}/", method = RequestMethod.GET)
    public Category getCategories(@PathVariable String name){

        return categoryDAO.getCategoryByName(name);





    }





}
