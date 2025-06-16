package com.pluralsight.NorthwindTradersSpringBoot.data;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
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
public class MySqlProductDAO implements  ProductDAO {

    private DatabaseConfig databaseConfig;


    @Autowired
    public MySqlProductDAO(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public List<Product> getAll() {
        ArrayList<Product> products = new ArrayList<>();
        BasicDataSource bds = new BasicDataSource();
        bds.setUsername(databaseConfig.getUsername());
        bds.setPassword(databaseConfig.getPassword());
        bds.setUrl(databaseConfig.getUrl());

        String query = """
                select productID, ProductName,CategoryName, UnitPrice
                from products
                join categories on products.CategoryID = categories.CategoryID
                order by ProductID
                
                
                """;

        try(
                Connection connection =  bds.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet results = statement.executeQuery();
        ) {
            while(results.next()){
                int productID = results.getInt(1);
                String productName = results.getString(2);
                String categoryName = results.getString(3);
                double unitPrice = results.getDouble(4);
                Product p = new Product(productID,productName,categoryName,unitPrice);
                products.add(p);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;


    }
}
