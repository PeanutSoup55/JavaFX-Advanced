package com.example.javafxadvanced;

import com.example.javafxadvanced.GUI.Home;
import com.example.javafxadvanced.GUI.SideMenu;
import com.example.javafxadvanced.db.Operations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage){
        BorderPane borderPane = new BorderPane();
        stage.setTitle("IMS");
        Operations.initialize();
        Home home = new Home();
        SideMenu sideMenu = new SideMenu(borderPane);
        borderPane.setLeft(sideMenu);
        borderPane.setCenter(home);

        Scene scene = new Scene(borderPane, 1200, 900);
        stage.setScene(scene);
        stage.show();
    }

    static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
    }

}
