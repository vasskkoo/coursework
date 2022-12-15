package com.example.banksystem.controller;

import com.example.banksystem.Application;
import com.example.banksystem.dao.BankInfoDao;
import com.example.banksystem.dao.BankTypeDao;
import com.example.banksystem.dao.Dao;
import com.example.banksystem.database.config.DatabaseConnection;
import com.example.banksystem.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.banksystem.database.schema.tables.BankInfo.BANK_INFO;
import static com.example.banksystem.database.schema.tables.BankType.BANK_TYPE;

public class AdminBankController implements Initializable  {

    @FXML
    private Button bunkbutton;

    @FXML
    private Button editbutton;

    @FXML
    private Button logoutbutton;

    @FXML
    private TableColumn<BankInfoModel, Integer> maxsum;

    @FXML
    private TableColumn<BankInfoModel, Integer> maxterm;

    @FXML
    private TableColumn<BankInfoModel, Integer> minsum;

    @FXML
    private TableColumn<BankInfoModel, Integer> minterm;

    @FXML
    private TableColumn<BankInfoModel, String> name;

    @FXML
    private TableColumn<BankInfoModel, Double> percent;

    @FXML
    private TableColumn<BankInfoModel, Integer> idbank;

    @FXML
    private Button userbutton;

    @FXML
    private Button viewbutton;

    @FXML
    private TableView<BankInfoModel> banktable;


    private ObservableList<BankInfoModel> bankInfoModelObservableList = FXCollections.observableArrayList();

    private Dao<BankInfo> bankInfoDao;
    private Dao<BankType> bankTypeDao;

    private List<BankInfoModel> bankInfoModelArrayList = new ArrayList<>();
    private List<BankType> bankTypeList = new ArrayList<>();
    private List<BankInfo> bankInfoList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bankInfoDao = new BankInfoDao();
        bankTypeDao = new BankTypeDao();

        bankTypeList = bankTypeDao.getAll();
        bankInfoList = bankInfoDao.getAll();


        try {
            Connection connection = DatabaseConnection.getConnection();
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            Result<?> result = (Result<? extends Record>) context.select()
                    .from(BANK_INFO)
                    .innerJoin(BANK_TYPE)
                    .on(BANK_TYPE.ID.eq(BANK_INFO.BANK_ID))
                    .fetch();

            for (Record record: result) {
                BankInfoModel bankInfoModel = new BankInfoModel(
                        record.getValue(BANK_TYPE.ID),
                        record.getValue(BANK_TYPE.NAME),
                        record.getValue(BANK_INFO.MIN_SUM),
                        record.getValue(BANK_INFO.MAX_SUM),
                        record.getValue(BANK_INFO.MIN_TERM),
                        record.getValue(BANK_INFO.MAX_TERM),
                        record.getValue(BANK_INFO.PERCENT)
                );

                bankInfoModelArrayList.add(bankInfoModel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        bankInfoModelObservableList = FXCollections.observableArrayList(bankInfoModelArrayList);
        banktable.setItems(bankInfoModelObservableList);

        idbank.setCellValueFactory(new PropertyValueFactory<>("Idbank"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        minsum.setCellValueFactory(new PropertyValueFactory<>("min_sum"));
        maxsum.setCellValueFactory(new PropertyValueFactory<>("max_sum"));
        minterm.setCellValueFactory(new PropertyValueFactory<>("min_term"));
        maxterm.setCellValueFactory(new PropertyValueFactory<>("max_term"));
        percent.setCellValueFactory(new PropertyValueFactory<>("percent"));

        banktable.setItems(bankInfoModelObservableList);

    }



    public void userButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) userbutton.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/adminuser.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 790, 446);
            scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void logoutButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) logoutbutton.getScene().getWindow();
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
}
