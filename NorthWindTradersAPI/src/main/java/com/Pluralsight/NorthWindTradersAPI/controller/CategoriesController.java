package com.Pluralsight.NorthWindTradersAPI.controller;

import com.Pluralsight.NorthWindTradersAPI.dao.CategoryDAO;
import com.Pluralsight.NorthWindTradersAPI.models.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = {"/Categories", "/Categories/"}, method = RequestMethod.POST)
    @ResponseStatus(value= HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category){

        return categoryDAO.addCategory(category);

    }







    @RequestMapping(path = {"/Categories/id={id}", "/Categories/id={id}/"}, method = RequestMethod.GET)
    public Category getCategories(@PathVariable int id){

       return categoryDAO.getCategoryByID(id);


    }


    @RequestMapping(path = {"/Categories/name={name}", "/Categories/name={name}/"}, method = RequestMethod.GET)
    public Category getCategories(@PathVariable String name){

        return categoryDAO.getCategoryByName(name);





    }





}
