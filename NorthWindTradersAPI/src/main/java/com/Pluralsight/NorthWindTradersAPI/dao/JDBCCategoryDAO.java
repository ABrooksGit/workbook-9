package com.Pluralsight.NorthWindTradersAPI.dao;

import com.Pluralsight.NorthWindTradersAPI.models.Category;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCCategoryDAO implements CategoryDAO {

    private DatabaseConfig databaseConfig;
    private BasicDataSource basicDataSource;


    @Autowired
    public JDBCCategoryDAO(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
        this.basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseConfig.getUrl());
        basicDataSource.setUsername(databaseConfig.getUsername());
        basicDataSource.setPassword(databaseConfig.getPassword());

    }


    @Override
    public List<Category> getAllCategories() {

        ArrayList<Category> categories = new ArrayList<>();
        String query = """
                                select
                                CategoryID,
                                CategoryName
                                from
                                categories

                """;


        try(Connection connection = basicDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
        ){
            while (resultSet.next()){
                int categoryID = resultSet.getInt(1);
                String categoryName = resultSet.getString(2);
//                String description = resultSet.getString(3);

                categories.add(new Category(categoryID,categoryName));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return categories;
    }

    @Override
    public Category getCategoryByID(int categoryID) {

        String query = """
                      Select
                      CategoryID,
                      CategoryName
                      from
                      categories
                      WHERE CategoryID = ?
                
                
                
                
                
                """;



        try(Connection connection = basicDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, categoryID);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int idCategory = resultSet.getInt(1);
                    String categoryName = resultSet.getString(2);
                    Category c = new Category(idCategory, categoryName);
                    return  c;



                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }



    @Override
    public Category getCategoryByName(String name) {

        String query = """
                                select
                                CategoryID,
                                CategoryName
                                from
                                categories
                                WHERE CategoryName = ?
                
                
                
                
                
                """;


        try(Connection connection = basicDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)

        ){
            preparedStatement.setString(1,name);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int idCategory = resultSet.getInt(1);
                    String categoryName = resultSet.getString(2);
                    Category c = new Category(idCategory, categoryName);
                    return  c;



                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    @Override
    public Category addCategory(Category category) {

        String query = """
                Insert into Categories
                (CategoryID, CategoryName)
                values
                (?,?)
                
                
                
                """;

        try(Connection connection = basicDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)
        ){
            preparedStatement.setInt(1, category.getCategoryID());
            preparedStatement.setString(2, category.getCategoryName());

            int rows = preparedStatement.executeUpdate();

            try(ResultSet keys = preparedStatement.getGeneratedKeys()){
                while (keys.next()){
                    Category categoryResult = new Category();
                    categoryResult.setCategoryID(keys.getInt(1));
                    categoryResult.setCategoryName(category.getCategoryName());
                    return categoryResult;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return  null;








    }
}
