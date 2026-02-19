package com.example.javafxadvanced;

import com.example.javafxadvanced.GUI.Home;
import com.example.javafxadvanced.db.Operations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("IMS");
        Operations.initialize();
        Home home = new Home(stage);
        Scene scene = new Scene(home, 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
    }

}
