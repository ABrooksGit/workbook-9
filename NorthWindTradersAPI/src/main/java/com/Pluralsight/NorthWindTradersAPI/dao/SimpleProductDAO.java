package com.Pluralsight.NorthWindTradersAPI.dao;

import com.Pluralsight.NorthWindTradersAPI.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class SimpleProductDAO implements ProductDAO {


    @Override
   public List<Product> getAllProducts(){
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Milk", 1, 5.99));
        products.add(new Product(2, "Bread", 1, 5.99));
        products.add(new Product(3, "Water", 1, 5.99));
        products.add(new Product(4, "Pants", 2, 5.99));
        products.add(new Product(5, "TShirt", 2, 5.99));
        return products;

    }

    @Override
   public Product getProductByID(int productID){

        List<Product> allProducts = getAllProducts();
        return  allProducts.stream().filter(p -> p.getProductID() == productID).findFirst().orElse(null);

    }

    @Override
    public Product addProduct(Product product) {
        return new Product();

    }
}