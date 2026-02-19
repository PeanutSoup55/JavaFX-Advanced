package com.example.javafxadvanced.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Operations {
    private static final String URL = Credentials.getDbUrl();
    private static final String USER = Credentials.getDbUser();
    private static final String PASS = Credentials.getDbPass();

    private static final String productSQL = "CREATE TABLE IF NOT EXISTS products (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL," +
            "name VARCHAR(255) NOT NULL," +
            "price DOUBLE(10, 2) NOT NULL," +
            "cog DOUBLE(10, 2) NOT NULL," +
            "quantity INT NOT NULL," +
            "station VARCHAR(50)," +
            "is_empty BOOLEAN DEFAULT FALSE," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE" +
            ");";
    private static final String companySQL = "CREATE TABLE IF NOT EXISTS company (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "name VARCHAR(255) NOT NULL UNIQUE," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ");";
    private static final String userSQL = "CREATE TABLE IF NOT EXISTS user (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL," +
            "username VARCHAR(255) NOT NULL," +
            "password VARCHAR(255) NOT NULL," +
            "pay DOUBLE(10, 2) NOT NULL," +
            "role ENUM('admin', 'employee') DEFAULT 'employee'," +
            "UNIQUE (company_id, username)," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE" +
            ");";
    private static final String settingsSQL = "CREATE TABLE IF NOT EXISTS settings (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL UNIQUE," +
            "tax_rate DECIMAL (5, 2) DEFAULT 13.00," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE" +
            ");";
    private static final String salesSQL = "CREATE TABLE IF NOT EXISTS sale (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL," +
            "employee_id INT NOT NULL," +
            "gross_revenue DECIMAL(10, 2) NOT NULL," +
            "cog_sold DECIMAL(10, 2) DEFAULT 0.00," +
            "operating_cost DECIMAL (10, 2) DEFAULT 0.00," +
            "tax_amount DECIMAL (10, 2) DEFAULT 0.00," +
            "payment_method VARCHAR(50)," +
            "invoice VARCHAR(100)," +
            "sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "UNIQUE (company_id, invoice)," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE," +
            "FOREIGN KEY (employee_id) REFERENCES user(id)" +
            ");";
    private static final String sale_itemSQL = "CREATE TABLE IF NOT EXISTS sale_items (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "sale_id INT NOT NULL," +
            "product_id INT NOT NULL," +
            "quantity INT NOT NULL," +
            "unit_price DECIMAL(10, 2) NOT NULL," +
            "total_price DECIMAL (10, 2) NOT NULL," +
            "FOREIGN KEY (sale_id) REFERENCES sale(id) ON DELETE CASCADE," +
            "FOREIGN KEY (product_id) REFERENCES products(id)" +
            ");";

    public static void initialize(){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASS); Statement statement = connection.createStatement()){
            statement.executeUpdate(companySQL);
            statement.executeUpdate(productSQL);
            statement.executeUpdate(userSQL);
            statement.executeUpdate(settingsSQL);
            statement.executeUpdate(salesSQL);
            statement.executeUpdate(sale_itemSQL);
        } catch (Exception e){
            for (StackTraceElement el : e.getStackTrace()){
                System.out.println(el);
            }
        }
    }


}
