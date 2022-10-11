package com.involuntary.revpos;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import com.ShoppingCart.Cart;
import com.ShoppingCart.CartItem;
import com.involuntary.revpos.controller.PosController;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/pos.fxml"));
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