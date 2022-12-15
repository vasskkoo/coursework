package com.example.banksystem.controller;

import com.example.banksystem.Application;
import com.example.banksystem.context.UserContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BankInfoController implements Initializable  {
    public static String chenge ;

    @FXML
    private Button Choose;

    @FXML
    private Text Name;

    @FXML
    private Button Overview;

    @FXML
    private Button alfa;

    @FXML
    private Text alfapercent;

    @FXML
    private Text alfasum;

    @FXML
    private Text alfaterm;

    @FXML
    private Button idea;

    @FXML
    private Text ideapercent;

    @FXML
    private Text ideasum;

    @FXML
    private Text ideaterm;

    @FXML
    private Text iduser;

    @FXML
    private Button logout;

    @FXML
    private Button mono;

    @FXML
    private Text monopecent;

    @FXML
    private Text monosum;

    @FXML
    private Text monoterm;

    @FXML
    private Button opt;

    @FXML
    private Text optpercent;

    @FXML
    private Text optsum;

    @FXML
    private Text optterm;

    @FXML
    private Button pumb;

    @FXML
    private Text pumbpercent;

    @FXML
    private Text pumbsum;

    @FXML
    private Text pumbterm;

    @FXML
    private Button raifa;

    @FXML
    private Text raifpercent;

    @FXML
    private Text raifsum;

    @FXML
    private Text raifterm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name.setText(UserContext.getInstance().getUser().getFirstname().concat(" ").concat(UserContext.getInstance().getUser().getLastname()));
        iduser.setText(String.valueOf(UserContext.getInstance().getUser().getId()));

    }

    public void logoutOnAction(ActionEvent event) {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/login.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 779, 520);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void OverviewOnAction(ActionEvent event) {
        Stage stage = (Stage) Overview.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/overView.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 864, 514);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void monoOnAction(ActionEvent event) {
        BankInfoController.chenge= "1";
        Stage stage = (Stage) mono.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/mono.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 864, 524);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void alfaOnAction(ActionEvent event) {
        BankInfoController.chenge= "2";
        Stage stage = (Stage) alfa.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/alfa.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 864, 524);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void raifOnAction(ActionEvent event) {
        BankInfoController.chenge= "6";
        Stage stage = (Stage) raifa.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/raif.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 864, 524);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void pumbOnAction(ActionEvent event) {
        BankInfoController.chenge= "3";
        Stage stage = (Stage) pumb.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/pumb.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 864, 524);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void optOnAction(ActionEvent event) {
        BankInfoController.chenge= "5";
        Stage stage = (Stage) opt.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/otp.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 864, 524);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void ideaOnAction(ActionEvent event) {
        BankInfoController.chenge= "4";
        Stage stage = (Stage) idea.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/idea.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 864, 524);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}
