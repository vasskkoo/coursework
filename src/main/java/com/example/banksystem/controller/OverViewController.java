package com.example.banksystem.controller;

import com.example.banksystem.Application;
import com.example.banksystem.backend.CreditService;
import com.example.banksystem.context.UserContext;
import com.example.banksystem.dao.BankInfoDao;
import com.example.banksystem.dao.BankTypeDao;
import com.example.banksystem.dao.Dao;
import com.example.banksystem.dao.UserInfoDao;
import com.example.banksystem.model.BankInfo;
import com.example.banksystem.model.BankType;
import com.example.banksystem.model.UserInfo;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class OverViewController implements Initializable  {

    @FXML
    private Button chooseButton;

    @FXML
    private Text credit;

    @FXML
    private Button creditlinebutton;

    @FXML
    private Text creditname;

    @FXML
    private Button loadbutton;

    @FXML
    private Button logoutButton;

    @FXML
    private Text mounthpay;

    @FXML
    private Text nameText;

    @FXML
    private Text nameofbank;

    @FXML
    private Text overpay;

    @FXML
    private Button overviewButton;

    @FXML
    private Text userIdText;

    private Dao<BankInfo> bankInfoDao;
    private Dao<BankType> bankTypeDao;
    private Dao<UserInfo> userInfoDao;
    private List<UserInfo> userInfoList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameText.setText(UserContext.getInstance().getUser().getFirstname().concat(" ").concat(UserContext.getInstance().getUser().getLastname()));
        userIdText.setText(String.valueOf(UserContext.getInstance().getUser().getId()));

        bankTypeDao = new BankTypeDao();
        bankInfoDao = new BankInfoDao();
        userInfoDao = new UserInfoDao();
        userInfoList = userInfoDao.getAll();

        if (userInfoList != null && userInfoList.size() != 0) {
            UserInfo userInfo = userInfoList.get(userInfoList.size() - 1);
            Optional<BankType> bankTypeOptional = bankTypeDao.get(userInfo.getBankId());
            bankTypeOptional.ifPresent(t -> nameofbank.setText("Bank name = " + t.getName()));

            double creditSumOpt = CreditService.getCreditSum(userInfo.getSum(), userInfo.getTerm(), userInfo.getBankId());
            credit.setText("Total credit = " + String.valueOf(creditSumOpt));

            mounthpay.setText("Months to pay = " + String.valueOf(CreditService.getMontroothsToPay(creditSumOpt)));
            overpay.setText("Overpay = " + String.valueOf(creditSumOpt - userInfo.getSum()));
        }
    }

    public void logoutButtonOnButton(ActionEvent event) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
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

    public void chooseButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) chooseButton.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/bankInfo.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 1065, 658);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void creditlineButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) creditlinebutton.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/addcreditline.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 451, 180);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void loadButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) loadbutton.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/load.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 451, 180);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }




}
