package com.example.javafxadvanced.GUI;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SideMenu extends VBox {
    private BorderPane borderPane;
    public SideMenu(BorderPane borderPane){
        this.borderPane = borderPane;

        Label label = new Label("Home");
        label.setPadding(new Insets(10, 10, 50, 0));
        label.setStyle("" +
                "-fx-font-size: 35px;");

        Button home = new Button("Home");
        Button products = new Button("Products");
        Button manageUsers = new Button("Manage Users");
        Button sales = new Button("Sales");
        Button salesAnalytics = new Button("Sales Analytics");
        Button settings = new Button("Settings");

        home.setOnAction(e -> {
            navigateToPage(new Home());
            label.setText(home.getText());
        });
        products.setOnAction(e -> {
            navigateToPage(new Products());
            label.setText(products.getText());
        });
        manageUsers.setOnAction(e -> {
            navigateToPage(new ManageUsers());
            label.setText(manageUsers.getText());
        });
        sales.setOnAction(e -> {
            navigateToPage(new Sales());
            label.setText(sales.getText());
        });
        salesAnalytics.setOnAction(e -> {
            navigateToPage(new SalesAnalytics());
            label.setText(salesAnalytics.getText());
        });
        settings.setOnAction(e -> {
            navigateToPage(new Settings());
            label.setText(settings.getText());
        });
        styleButton(home);
        styleButton(products);
        styleButton(manageUsers);
        styleButton(sales);
        styleButton(salesAnalytics);
        styleButton(settings);

        this.setPrefWidth(300);
        this.setSpacing(20);
        this.setPadding(new Insets(15));
        this.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 15px;");
        this.getChildren().addAll(label, home, products, manageUsers, sales, settings, salesAnalytics);
    }

    public void navigateToPage(Node page){
        try {
            borderPane.setCenter(page);
        }catch (Exception e){
            for (StackTraceElement el : e.getStackTrace()){
                System.out.println(el);
            }
        }
    }

    private void styleButton(Button button){
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(45);
        button.setStyle("-fx-background-color: #eeeeee;" +
                "-fx-text-fill: black;" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: regular;" +
                "-fx-alignment: left;");
    }
}
