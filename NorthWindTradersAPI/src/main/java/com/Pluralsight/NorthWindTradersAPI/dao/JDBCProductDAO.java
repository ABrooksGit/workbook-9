package com.Pluralsight.NorthWindTradersAPI.dao;


import com.Pluralsight.NorthWindTradersAPI.models.Product;
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
public class JDBCProductDAO  implements ProductDAO{


    private DatabaseConfig databaseConfig;
    private BasicDataSource basicDataSource;

    @Autowired
    public JDBCProductDAO(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
        this.basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(databaseConfig.getUrl());
        basicDataSource.setUsername(databaseConfig.getUsername());
        basicDataSource.setPassword(databaseConfig.getPassword());
    }

    @Override
    public List<Product> getAllProducts(){

        ArrayList<Product> products = new ArrayList<>();

        String query = """
                select
                productID,
                ProductName,
                CategoryID,
                UnitPrice
                from
                products
                
                
                """;


        try(
            Connection connection = basicDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
        ){
            while (resultSet.next()){
                int productId = resultSet.getInt(1);
                String productName = resultSet.getString(2);
                int categoryID = resultSet.getInt(3);
                double price = resultSet.getDouble(4);
                Product p = new Product(productId,productName,categoryID,price);
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return products;
    }


    @Override
    public Product getProductByID(int productID) {



        String query = """
                select
                productID,
                ProductName,
                CategoryID,
                UnitPrice
                from
                products
                WHERE ProductID = ?
                
                
                """;



        try(
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);

        ) {
            preparedStatement.setInt(1, productID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int productId = resultSet.getInt(1);
                    String productName = resultSet.getString(2);
                    int categoryID = resultSet.getInt(3);
                    double price = resultSet.getDouble(4);
                    Product p = new Product(productId, productName, categoryID, price);
                    return p;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }







        return null;


    }

    @Override
    public Product addProduct(Product product) {

        String query = """
                Insert into products
                (ProductName,SupplierID,CategoryID,UnitPrice)
                values
                (?, 1 , ?, ?)
                """;
        try(
                Connection connection = basicDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)

                ){
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setInt(2,product.getCategoryID());
            preparedStatement.setDouble(3,product.getPrice());

            int rows = preparedStatement.executeUpdate();

            try(ResultSet keys = preparedStatement.getGeneratedKeys()){
                while (keys.next()){
                    Product result = new Product();
                    result.setProductID(keys.getInt(1));
                    result.setProductName(product.getProductName());
                    result.setCategoryID(product.getCategoryID());
                    result.setPrice(product.getPrice());
                    return result;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return null;
    }



}
