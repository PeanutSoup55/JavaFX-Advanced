package com.example.javafxadvanced.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import org.kordamp.ikonli.javafx.FontIcon;

public class SideMenu extends VBox {
    private BorderPane borderPane;

    public SideMenu(BorderPane borderPane) {
        this.borderPane = borderPane;

        Label label = new Label("Home");
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setStyle("-fx-font-weight: bold;" +
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 30px;");

        Separator separator = new Separator();
        separator.setMaxWidth(Double.MAX_VALUE);
        Separator separator2 = new Separator();
        separator2.setMaxWidth(Double.MAX_VALUE);

        HBox home = createNavItem("fas-home", "Home");
        HBox products = createNavItem("fas-box", "Products");
        HBox manageUsers = createNavItem("fas-users", "Manage Users");
        HBox sales = createNavItem("fas-dollar-sign", "Sales");
        HBox salesAnalytics = createNavItem("fas-chart-line", "Sales Analytics");
        HBox settings = createNavItem("fas-cog", "Settings");

        home.setOnMouseClicked(e -> { navigateToPage(new Home()); label.setText("Home"); });
        products.setOnMouseClicked(e -> { navigateToPage(new Products()); label.setText("Products"); });
        manageUsers.setOnMouseClicked(e -> { navigateToPage(new ManageUsers()); label.setText("Manage Users"); });
        sales.setOnMouseClicked(e -> { navigateToPage(new Sales()); label.setText("Sales"); });
        salesAnalytics.setOnMouseClicked(e -> { navigateToPage(new SalesAnalytics()); label.setText("Sales Analytics"); });
        settings.setOnMouseClicked(e -> { navigateToPage(new Settings()); label.setText("Settings"); });

        this.setPrefWidth(300);
        this.setSpacing(10);
        this.setPadding(new Insets(15));
        this.setStyle("-fx-background-color: #ffffff;");
        this.getChildren().addAll(label, separator, home, products, manageUsers, sales, salesAnalytics, separator2, settings);
    }

    private HBox createNavItem(String iconCode, String text) {
        FontIcon icon = new FontIcon(iconCode);
        icon.setIconSize(18);

        StackPane iconContainer = new StackPane(icon);
        iconContainer.setPrefWidth(40);
        iconContainer.setAlignment(Pos.CENTER);

        Label label = new Label(text);
        label.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");

        HBox hbox = new HBox(iconContainer, label);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefHeight(50);
        hbox.setMaxWidth(Double.MAX_VALUE);
        hbox.setPadding(new Insets(0, 10, 0, 10));
        hbox.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px; -fx-cursor: hand;");

        hbox.setOnMouseEntered(e -> hbox.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 10px; -fx-cursor: hand;"));
        hbox.setOnMouseExited(e -> hbox.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px; -fx-cursor: hand;"));
        hbox.setOnMousePressed(e -> hbox.setStyle("-fx-background-color: #e0e0e0; -fx-background-radius: 10px; -fx-cursor: hand;"));
        hbox.setOnMouseReleased(e -> {
            if (hbox.isHover()) hbox.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 10px; -fx-cursor: hand;");
            else hbox.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px; -fx-cursor: hand;");
        });

        return hbox;
    }

    public void navigateToPage(Node page) {
        try {
            borderPane.setCenter(page);
        } catch (Exception e) {
            for (StackTraceElement el : e.getStackTrace()) {
                System.out.println(el);
            }
        }
    }
}
