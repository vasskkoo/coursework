package com.example.banksystem.controller;

import com.example.banksystem.Application;
import com.example.banksystem.context.UserContext;
import com.example.banksystem.dao.Dao;
import com.example.banksystem.dao.UserDao;
import com.example.banksystem.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button signInButton, signUpButton;

    private List<User> users;
    private static Dao<User> userDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDao = new UserDao();
        users = userDao.getAll();

    }

    public void signInButtonOnAction(ActionEvent event) {
        if (!usernameTextField.getText().isBlank() || !passwordPasswordField.getText().isBlank()) {
            if (isLoginValid(usernameTextField.getText(), passwordPasswordField.getText())) {

                Optional<User> user = users.stream()
                        .filter(t -> t.getUsername().equals(usernameTextField.getText()))
                        .findAny();

                UserContext.getInstance().setUser(user.get());

                Stage stage = (Stage) signInButton.getScene().getWindow();
                stage.close();


                if (usernameTextField.getText().equals("admin") && passwordPasswordField.getText().equals("admin")) {
                    try {
                        Parent root = FXMLLoader.load(Application.class.getResource("view/adminuser.fxml"));
                        Stage mainStage = new Stage();
                        Scene scene = new Scene(root, 790, 446);
                        scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
                        mainStage.setScene(scene);
                        mainStage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Parent root = FXMLLoader.load(Application.class.getResource("view/overView.fxml"));
                        Stage mainStage = new Stage();
                        Scene scene = new Scene(root, 725, 514);
                        scene.getStylesheets().add(Application.class.getResource("css/style.css").toExternalForm());
                        mainStage.setScene(scene);
                        mainStage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void signUpButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Application.class.getResource("view/register.fxml"));
            Stage registerStage = new Stage();
            Scene scene = new Scene(root,790, 447);
            registerStage.setScene(scene);
            registerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private boolean isLoginValid(String username, String password) {
        users = userDao.getAll();
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

}
