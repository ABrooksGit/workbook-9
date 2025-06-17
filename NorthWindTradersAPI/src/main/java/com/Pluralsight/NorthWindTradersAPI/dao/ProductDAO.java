package com.Pluralsight.NorthWindTradersAPI.dao;

import com.Pluralsight.NorthWindTradersAPI.models.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts();
    Product getProductByID(int productID);
    void addProduct(Product product);
}
