package com.example.javafxadvanced;

import com.example.javafxadvanced.GUI.Home;
import com.example.javafxadvanced.GUI.SideMenu;
import com.example.javafxadvanced.db.Operations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage){
        stage.setTitle("IMS");
        Operations.initialize();

        SplitPane splitPane = new SplitPane();
        SideMenu sideMenu = new SideMenu(splitPane);
        splitPane.getItems().addAll(sideMenu, new Home());
        splitPane.setDividerPositions(0.2);

        Scene scene = new Scene(splitPane, 1400, 1000);
        stage.setScene(scene);
        stage.show();
    }

    static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
    }

}
