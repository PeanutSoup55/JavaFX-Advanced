package com.example.javafxadvanced.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Operations {
    private static final String URL = Credentials.getDbUrl();
    private static final String USER = Credentials.getDbUser();
    private static final String PASS = Credentials.getDbPass();

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
            "email VARCHAR(255)," +
            "phone VARCHAR(50)," +
            "pay DOUBLE(10, 2) NOT NULL," +
            "role ENUM('admin', 'employee') DEFAULT 'employee'," +
            "deleted_at TIMESTAMP DEFAULT NULL," +
            "UNIQUE (company_id, username)," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE" +
            ");";

    private static final String customerSQL = "CREATE TABLE IF NOT EXISTS customer (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL," +
            "name VARCHAR(255) NOT NULL," +
            "email VARCHAR(255)," +
            "phone VARCHAR(50)," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "deleted_at TIMESTAMP DEFAULT NULL," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE" +
            ");";

    private static final String productSQL = "CREATE TABLE IF NOT EXISTS products (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL," +
            "name VARCHAR(255) NOT NULL," +
            "price DOUBLE(10, 2) NOT NULL," +
            "cog DOUBLE(10, 2) NOT NULL," +
            "quantity INT NOT NULL," +
            "station VARCHAR(50)," +
            "deleted_at TIMESTAMP DEFAULT NULL," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE" +
            ");";

    private static final String settingsSQL = "CREATE TABLE IF NOT EXISTS settings (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL UNIQUE," +
            "tax_rate DECIMAL(5, 2) DEFAULT 13.00," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE" +
            ");";

    private static final String projectSQL = "CREATE TABLE IF NOT EXISTS project (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL," +
            "name VARCHAR(255) NOT NULL," +
            "description TEXT," +
            "status ENUM('active', 'completed', 'on_hold') DEFAULT 'active'," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE" +
            ");";

    private static final String taskSQL = "CREATE TABLE IF NOT EXISTS task (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL," +
            "project_id INT," +
            "assigned_to INT," +
            "title VARCHAR(255) NOT NULL," +
            "description TEXT," +
            "status ENUM('pending', 'in_progress', 'completed') DEFAULT 'pending'," +
            "due_date DATE," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE," +
            "FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE SET NULL," +
            "FOREIGN KEY (assigned_to) REFERENCES user(id) ON DELETE SET NULL" +
            ");";

    private static final String salesSQL = "CREATE TABLE IF NOT EXISTS sale (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL," +
            "employee_id INT NOT NULL," +
            "customer_id INT DEFAULT NULL," +
            "gross_revenue DECIMAL(10, 2) NOT NULL," +
            "cog_sold DECIMAL(10, 2) DEFAULT 0.00," +
            "operating_cost DECIMAL(10, 2) DEFAULT 0.00," +
            "tax_amount DECIMAL(10, 2) DEFAULT 0.00," +
            "payment_method VARCHAR(50)," +
            "invoice VARCHAR(100)," +
            "sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "UNIQUE (company_id, invoice)," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE," +
            "FOREIGN KEY (employee_id) REFERENCES user(id)," +
            "FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE SET NULL" +
            ");";

    private static final String saleItemSQL = "CREATE TABLE IF NOT EXISTS sale_items (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "sale_id INT NOT NULL," +
            "product_id INT NOT NULL," +
            "quantity INT NOT NULL," +
            "unit_price DECIMAL(10, 2) NOT NULL," +
            "total_price DECIMAL(10, 2) NOT NULL," +
            "FOREIGN KEY (sale_id) REFERENCES sale(id) ON DELETE CASCADE," +
            "FOREIGN KEY (product_id) REFERENCES products(id)" +
            ");";

    private static final String feedbackSQL = "CREATE TABLE IF NOT EXISTS feedback (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL," +
            "customer_id INT," +
            "user_id INT," +
            "type ENUM('customer', 'employee') NOT NULL," +
            "message TEXT NOT NULL," +
            "rating TINYINT CHECK (rating BETWEEN 1 AND 5)," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE," +
            "FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE SET NULL," +
            "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL" +
            ");";

    private static final String logsSQL = "CREATE TABLE IF NOT EXISTS logs (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL," +
            "user_id INT," +
            "action VARCHAR(100) NOT NULL," +
            "entity_type VARCHAR(50)," +
            "entity_id INT," +
            "description TEXT," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE," +
            "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL" +
            ");";

    public static void initialize() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(companySQL);
            statement.executeUpdate(userSQL);
            statement.executeUpdate(customerSQL);
            statement.executeUpdate(productSQL);
            statement.executeUpdate(settingsSQL);
            statement.executeUpdate(projectSQL);
            statement.executeUpdate(taskSQL);
            statement.executeUpdate(salesSQL);
            statement.executeUpdate(saleItemSQL);
            statement.executeUpdate(feedbackSQL);
            statement.executeUpdate(logsSQL);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            for (StackTraceElement el : e.getStackTrace()) {
                System.out.println(el);
            }
        }
    }
}
