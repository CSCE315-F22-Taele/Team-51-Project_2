package com.involuntary.revpos.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class StatsController extends ManagerController {
    @FXML
    private ImageView manageReportBtn;

    @FXML
    private ImageView salesReportBtn;

    public void openManageReport() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/reports.fxml"));
        Scene scene = manageReportBtn.getScene();
        scene.setRoot(root);
    }

    public void openSalesReport() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/sales_report.fxml"));
        Scene scene = salesReportBtn.getScene();
        scene.setRoot(root);
    }
}
