package com.example.banksystem.controller;

import com.example.banksystem.Application;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RaifController implements Initializable {

    @FXML
    private Label Maxsum;

    @FXML
    private Text Name;

    @FXML
    private Label Names;

    @FXML
    private Button backbutton;

    @FXML
    private Button choose;

    @FXML
    private Button conytinuebutton;

    @FXML
    private Text iduser;

    @FXML
    private Button logout;

    @FXML
    private Label maxterm;

    @FXML
    private Label minsum;

    @FXML
    private Label minterm;

    @FXML
    private Button overview;

    @FXML
    private Label percent;

    @FXML
    private TextField entersum;

    @FXML
    private TextField enterterm;

    @FXML
    private Text erorrtext;

    private List<BankInfo> bankInfoList;
    private Dao<BankInfo> bankInfoDao;
    private Dao<BankType> bankTypeDao;

    private Dao<UserInfo> userInfoDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name.setText(UserContext.getInstance().getUser().getFirstname().concat(" ").concat(UserContext.getInstance().getUser().getLastname()));
        iduser.setText(String.valueOf(UserContext.getInstance().getUser().getId()));

        userInfoDao = new UserInfoDao();
        bankInfoDao = new BankInfoDao();
        bankTypeDao = new BankTypeDao();            // <=====
        bankInfoList = bankInfoDao.getAll();

        Optional<BankType> bankType = bankTypeDao.get(6);       // <=====

        Names.setText("Name bank : " + bankType.get().getName());       // <=====
        minsum.setText("Minimal sum : " + bankInfoList.get(5).getMinSum() );
        Maxsum.setText("Maximal sum : " + + bankInfoList.get(5).getMaxSum());
        minterm.setText("Minimal term : " + + bankInfoList.get(5).getMinTerm());
        maxterm.setText("Maximal term : " + + bankInfoList.get(5).getMaxTerm());
        percent.setText("Percent : " + bankInfoList.get(5).getPercent());
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
        Stage stage = (Stage) overview.getScene().getWindow();
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

    public void chooseButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) choose.getScene().getWindow();
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
    public void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) backbutton.getScene().getWindow();
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
    public void continueButtonOnAction(ActionEvent event) {
        try {
            Integer enterSum = Integer.parseInt(entersum.getText());
            Integer enterTerm = Integer.parseInt(enterterm.getText());

            Optional<BankInfo> bankInfoOpt = bankInfoList.stream().filter(t -> t.getBankId() == 6)      // <=== Міняєш АЙДІ банка
                    .findAny();

            BankInfo bankInfo = bankInfoOpt.get();

            System.out.println(enterSum);
            System.out.println(enterTerm);
            System.out.println(bankInfo);

            if (enterSum < bankInfo.getMinSum() || enterSum > bankInfo.getMaxSum() ||
                    enterTerm < bankInfo.getMinTerm() || enterTerm > bankInfo.getMaxTerm())
                throw new NumberFormatException();

            userInfoDao.insert(new UserInfo(
                    UserContext.getInstance().getUser().getId(),
                    6,                                                          // <=== Міняєш АЙДІ банка
                    enterSum,
                    enterTerm,
                    0
            ));

            Stage stage = (Stage) conytinuebutton.getScene().getWindow();
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

        } catch (NumberFormatException e) {
            erorrtext.setText("Enter correct data!");
            erorrtext.setStyle("-fx-text-fill: red");
            e.getCause();
        }                                                   // <=====

    }
}

