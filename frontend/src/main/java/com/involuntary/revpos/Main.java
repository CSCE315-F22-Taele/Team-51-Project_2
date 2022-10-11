package com.involuntary.revpos;

import com.involuntary.revpos.database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        stage.setTitle("Revs American Grill [POS]");
        stage.setScene(scene);
        stage.setMaximized(true);



        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}