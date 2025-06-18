package com.Pluralsight.NorthWindTradersAPI.dao;

import com.Pluralsight.NorthWindTradersAPI.models.Category;

import java.util.List;

public interface CategoryDAO {

    List<Category> getAllCategories();
    Category getCategoryByID(int categoryID);
    Category getCategoryByName(String categoryName);
    Category addCategory(Category category);





}
