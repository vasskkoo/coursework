package com.example.banksystem.controller;

import com.example.banksystem.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadController {
    public static Integer sum ;


    @FXML
    private Button buttonapply;

    @FXML
    private Button buutonback;

    @FXML
    private TextField entermounth;


    public void applyButtonOnButton(ActionEvent event) {
        Stage stage = (Stage) buttonapply.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/overView.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 725, 514);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void closeButtonOnButton(ActionEvent event) {
        Stage stage = (Stage) buutonback.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/overView.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 725, 514);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}