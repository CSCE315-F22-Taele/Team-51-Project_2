package com.involuntary.revpos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PosController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}