package com.involuntary.revpos.controller;

import com.involuntary.revpos.models.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PosExtendedController implements Initializable {
    @FXML
    private Button confirmChangesBtn;
    private static Button referConfirmChangesBtn;

    @FXML
    private ComboBox selectProductField;
    private static ComboBox referSelectProductField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        referConfirmChangesBtn = confirmChangesBtn;
        referSelectProductField = selectProductField;
    }

    public static void openModal(MenuItem item) throws IOException {
        Parent root = FXMLLoader.load(PosExtendedController.class.getResource("/views/posEditOrder.fxml"));
        Scene scene = new Scene(root);
        Stage modal = new Stage();
        modal.setScene(scene);
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.setAlwaysOnTop(true);
        modal.setIconified(false);
        modal.setResizable(false);
        modal.setTitle("Editing Order");
        modal.getIcons().add(new Image(PosExtendedController.class.getResourceAsStream("/images/tamulogo.png")));

        referSelectProductField.getItems().clear();
        referSelectProductField.getItems().addAll("lol", "123", "lmao");

        referConfirmChangesBtn.setOnAction(event -> {
            Stage stage = (Stage) (referConfirmChangesBtn.getScene().getWindow());
            stage.close();
            PosController.verifyOrder(item);
        });
        modal.showAndWait();
    }
}
