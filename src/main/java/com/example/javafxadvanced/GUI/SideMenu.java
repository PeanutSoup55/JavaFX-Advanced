package com.example.javafxadvanced.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SideMenu extends VBox {
    private BorderPane borderPane;
    public SideMenu(BorderPane borderPane){
        this.borderPane = borderPane;
        Button home = new Button("Home");
        Button products = new Button("Products");
        Button manageUsers = new Button("Manage Users");
        Button sales = new Button("Sales");
        Button salesAnalytics = new Button("Sales Analytics");
        Button settings = new Button("Settings");

        home.setOnAction(e -> navigateToPage(new Home()));
        products.setOnAction(e -> navigateToPage(new Products()));
        manageUsers.setOnAction(e -> navigateToPage(new ManageUsers()));
        sales.setOnAction(e -> navigateToPage(new Sales()));
        salesAnalytics.setOnAction(e -> navigateToPage(new SalesAnalytics()));
        settings.setOnAction(e -> navigateToPage(new Settings()));

        this.setSpacing(10);
        this.setPadding(new Insets(15));
        this.setStyle("-fx-background-color: #2c3e50;");
        this.getChildren().addAll(home, products, manageUsers, sales, settings, salesAnalytics);
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
}
