package com.Pluralsight.NorthWindTradersAPI.controller;

import com.Pluralsight.NorthWindTradersAPI.dao.ProductDAO;
import com.Pluralsight.NorthWindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {

    private ProductDAO productDAO;

    @Autowired
    public ProductsController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(path = "/Products", method = RequestMethod.GET)
    public List<Product> getProducts(){

     return productDAO.getAllProducts();

    }



    @RequestMapping(path = "/Products/{id}", method = RequestMethod.GET)
    public Product getProducts(@PathVariable int id){

        return productDAO.getProductByID(id);
    }

    @RequestMapping(path = "/Products", method = RequestMethod.POST)
      @ResponseStatus(value= HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){

        return productDAO.addProduct(product);

    }


//    @RequestMapping(path = "/Products/{price}", method = RequestMethod.GET)
//    public List<Product> getProducts(@PathVariable double price){
//
//        ArrayList<Product> products = new ArrayList<>();
//        products.add(new Product(1, "Milk", 1, 5.99));
//        products.add(new Product(2, "Bread", 1, 5.99));
//        products.add(new Product(3, "Water", 1, 5.99));
//        products.add(new Product(4, "Pants", 2, 5.99));
//        products.add(new Product(5, "TShirt", 2, 5.99));
//
//
//        for(Product p : products){
//            if(p.getPrice() == price){
//                ArrayList<Product> priceOfProducts = new ArrayList<>();
//                priceOfProducts.add(p);
//                return priceOfProducts;
//            }
//        }
//        return new ArrayList<>();
//
//    }

//    @RequestMapping(path = "/Products/{name}", method = RequestMethod.GET)
//    public List<Product> getProducts(@PathVariable String name){
//
//        ArrayList<Product> products = new ArrayList<>();
//        products.add(new Product(1, "Milk", 1, 5.99));
//        products.add(new Product(2, "Bread", 1, 5.99));
//        products.add(new Product(3, "Water", 1, 5.99));
//        products.add(new Product(4, "Pants", 2, 5.99));
//        products.add(new Product(5, "TShirt", 2, 5.99));
//
//
//        for(Product p : products){
//            if(p.getProductName().equals(name)){
//                ArrayList<Product> nameOfProducts = new ArrayList<>();
//                nameOfProducts.add(p);
//                return nameOfProducts;
//            }
//        }
//        return new ArrayList<>();
//
//    }
//
//



}
