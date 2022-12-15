package com.example.banksystem.controller;
import com.example.banksystem.Application;
import com.example.banksystem.dao.Dao;
import com.example.banksystem.dao.UserDao;
import com.example.banksystem.dao.UserInfoDao;
import com.example.banksystem.database.config.DatabaseConnection;
import com.example.banksystem.database.schema.tables.records.UserInfoRecord;
import com.example.banksystem.database.schema.tables.records.UserRecord;
import com.example.banksystem.model.User;
import com.example.banksystem.model.UserInfo;
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

import static com.example.banksystem.database.schema.tables.UserInfo.USER_INFO;
import static com.example.banksystem.database.schema.tables.User.USER;


public class AdminUserController implements Initializable {

    @FXML
    private TableColumn<UserInfoModel, Integer> age;

    @FXML
    private Button bunksbutton;

    @FXML
    private Button editbutton;

    @FXML
    private TableColumn<UserInfoModel, Integer> idUser;

    @FXML
    private TableColumn<UserInfoModel, String> lastName;

    @FXML
    private TableColumn<UserInfoModel, Integer> loan;

    @FXML
    private TableColumn<UserInfoModel, String> login;

    @FXML
    private Button logoutbutton;

    @FXML
    private TableColumn<UserInfoModel, String> name;

    @FXML
    private TableColumn<UserInfoModel, String> password;

    @FXML
    private TableColumn<UserInfoModel, Integer> sum;

    @FXML
    private TableColumn<UserInfoModel, Integer> term;

    @FXML
    private Button userbutton;

    @FXML
    private TableView<UserInfoModel> usertable;

    @FXML
    private Button viewbutton;

    private ObservableList<UserInfoModel> userInfoModelObservableList = FXCollections.observableArrayList();
    private List<UserInfoModel> userInfoModelList = new ArrayList<>();

    private Dao<UserInfo> userInfoDao;
    private Dao<User> userDao;

    private List<UserInfo> userInfoList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userInfoDao = new UserInfoDao();
        userDao = new UserDao();

        try {
            Connection connection = DatabaseConnection.getConnection();
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);

            Result<UserInfoRecord> userInfoRecords = context.selectFrom(USER_INFO)
                    .fetch();

            for (UserInfoRecord userInfoRecord : userInfoRecords) {
                Integer id = userInfoRecord.getValue(USER_INFO.ID);
                Integer userId = userInfoRecord.getValue(USER_INFO.USER_ID);

                userInfoList.add(new UserInfo(id, userId,
                        userInfoRecord.getValue(USER_INFO.BANK_ID),
                        userInfoRecord.getValue(USER_INFO.SUM),
                        userInfoRecord.getValue(USER_INFO.TERM),
                        userInfoRecord.getValue(USER_INFO.LOAN)));
            }

            Result<UserRecord> userRecords = context.selectFrom(USER)
                    .fetch();

            for (UserRecord userRecord : userRecords)
                userList.add(new User(userRecord.getValue(USER.ID), userRecord.getValue(USER.NAME), userRecord.getValue(USER.SURNAME),
                        userRecord.getValue(USER.USERNAME), userRecord.getValue(USER.PASSWORD), userRecord.getValue(USER.LOCATION),
                        userRecord.getValue(USER.AGE), userRecord.getValue(USER.GENDER)));

        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (UserInfo user : userInfoList) {
            Integer userId = user.getUserId();
            System.out.println("USER ID == " + userId);
            for (User currentUser : userList) {
                System.out.println(currentUser);
                if (currentUser.getId() == userId) {
                    System.out.println(currentUser);

                    String firstname = currentUser.getFirstname();
                    String lastname = currentUser.getLastname();
                    Integer age = currentUser.getAge();
                    String username = currentUser.getUsername();
                    String password = currentUser.getPassword();
                    Integer sum = user.getSum();
                    Integer term = user.getTerm();
                    Integer loan = user.getLoan();

                    UserInfoModel temp = new UserInfoModel(userId, firstname, lastname, age, username, password, sum, term, loan);
                    userInfoModelList.add(temp);
                }
            }
        }


        // end of user info model list init...
        userInfoModelObservableList = FXCollections.observableArrayList(userInfoModelList);
     //   usertable.setItems(userInfoModelObservableList);

        idUser.setCellValueFactory(new PropertyValueFactory<>("userId"));
        name.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        login.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        term.setCellValueFactory(new PropertyValueFactory<>("term"));
        loan.setCellValueFactory(new PropertyValueFactory<>("loan"));

        usertable.setItems(userInfoModelObservableList);

    }

    public void bankButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) bunksbutton.getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/adminbank.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root, 680, 430);
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