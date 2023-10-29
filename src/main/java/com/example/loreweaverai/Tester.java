package com.example.loreweaverai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



public class Tester extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Tester.class.getResource("new.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 640);
        stage.setTitle("Demo Version 1.0");
        stage.setScene(scene);
        stage.show();
        stage.setMaxWidth(1024);
        stage.setMaxHeight(640);
    }

    public static void main(String[] args) {
        launch();
    }
}
