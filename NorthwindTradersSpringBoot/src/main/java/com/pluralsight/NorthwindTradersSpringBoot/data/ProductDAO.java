package com.pluralsight.NorthwindTradersSpringBoot.data;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;

import java.util.HashMap;
import java.util.List;

public interface ProductDAO {
    void add(Product product);
    List<Product> getAll();
//    HashMap<Integer,Product> getAll();

}
