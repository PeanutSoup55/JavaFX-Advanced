package com.example.javafxadvanced.db;

public class Operations {
    private static final String URL = Credentials.getDbUrl();
    private static final String USER = Credentials.getDbUser();
    private static final String PASS = Credentials.getDbPass();

    private String productSQL = "CREATE TABLE IF NOT EXISTS products (" +
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
    private String companySQL = "CREATE TABLE IF NOT EXISTS company (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "name VARCHAR(255) NOT NULL UNIQUE," +
            "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ");";
    private String userSQL = "CREATE TABLE IF NOT EXISTS user (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL," +
            "username VARCHAR(255) NOT NULL," +
            "password VARCHAR(255) NOT NULL," +
            "pay DOUBLE(10, 2) NOT NULL," +
            "role ENUM('admin', 'employee') DEFAULT 'employee'," +
            "UNIQUE (company_id, username)," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE" +
            ");";
    private String settingsSQL = "CREATE TABLE IF NOT EXISTS settings (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "company_id INT NOT NULL UNIQUE," +
            "tax_rate DECIMAL (5, 2) DEFAULT 13.00," +
            "FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE" +
            ");";
}
