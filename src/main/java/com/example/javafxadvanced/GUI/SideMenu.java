package com.example.javafxadvanced.GUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class SideMenu extends VBox {
    private final SplitPane splitPane;
    private HBox selected = null;
    private final String baseStyle = "-fx-background-color: #ffffff; -fx-background-radius: 10px; -fx-cursor: hand;";
    private final String hoverStyle = "-fx-background-color: #f5f5f5; -fx-background-radius: 10px; -fx-cursor: hand;";
    private final String selectedStyle = "-fx-background-color: #f5f5f5; -fx-background-radius: 10px; -fx-cursor: hand;";


    public SideMenu(SplitPane splitPane) {
        this.splitPane = splitPane;
        splitPane.setStyle("-fx-box-border: transparent; -fx-padding: 0; -fx-background-color: transparent;");

        Label label = new Label("Home");
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setStyle("-fx-font-weight: bold;" +
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 25px;");

        Separator separator = new Separator();
        separator.setMaxWidth(Double.MAX_VALUE);
        Separator separator2 = new Separator();
        separator2.setMaxWidth(Double.MAX_VALUE);

        HBox home = createNavItem("fas-home", "Home", () -> navigateToPage(new Home()));
        HBox products = createNavItem("fas-box", "Products", () -> navigateToPage(new Products()));
        HBox manageUsers = createNavItem("fas-users", "Manage Users", () -> navigateToPage(new ManageUsers()));
        HBox sales = createNavItem("fas-dollar-sign", "Sales", () -> navigateToPage(new Sales()));
        HBox salesAnalytics = createNavItem("fas-chart-line", "Sales Analytics", () -> navigateToPage(new SalesAnalytics()));
        HBox settings = createNavItem("fas-cog", "Settings", () -> navigateToPage(new Settings()));

        this.setPrefWidth(300);
        this.setSpacing(10);
        this.setMinWidth(200);
        this.setMaxWidth(400);
        this.setPadding(new Insets(15));
        this.setStyle("-fx-background-color: #ffffff;");
        this.getChildren().addAll(label, separator, home, products, manageUsers, sales, salesAnalytics, separator2, settings);
    }

    private HBox createNavItem(String iconCode, String text, Runnable onAction) {
        FontIcon icon = new FontIcon(iconCode);
        icon.setIconSize(18);

        StackPane iconContainer = new StackPane(icon);
        iconContainer.setPrefWidth(40);
        iconContainer.setAlignment(Pos.CENTER);

        Label label = new Label(text);
        label.setPadding(new Insets(0, 0, 0, 30));
        label.setStyle("-fx-font-size: 14px; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-family: 'Arial';");

        return getHBox(onAction, iconContainer, label);
    }

    private HBox getHBox(Runnable onAction, StackPane iconContainer, Label label) {
        HBox hbox = new HBox(iconContainer, label);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefHeight(50);
        hbox.setMaxWidth(Double.MAX_VALUE);
        hbox.setPadding(new Insets(0, 10, 0, 10));

        hbox.setOnMouseEntered(e -> { if (selected != hbox) hbox.setStyle(hoverStyle); });
        hbox.setOnMouseExited(e -> { if (selected != hbox) hbox.setStyle(baseStyle); });
        hbox.setOnMouseClicked(e -> {
            if (selected != null) selected.setStyle(baseStyle);
            selected = hbox;
            hbox.setStyle(selectedStyle);
            onAction.run();
        });
        return hbox;
    }

    public void navigateToPage(Node page) {
        splitPane.getItems().set(1, page);
    }
}
