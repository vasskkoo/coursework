package com.example.banksystem.controller;

import com.example.banksystem.dao.Dao;
import com.example.banksystem.dao.UserDao;
import com.example.banksystem.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable  {

    @FXML
    private Button cancelButton, saveButton;

    @FXML
    private TextField firstnameTextField, lastnameTextField, locationTextField, ageTextField, usernameTextField;

    @FXML
    private PasswordField passwordPasswordField, confirmPasswordField;

    @FXML
    private RadioButton maleRadioButton, femaleRadioButton;

    @FXML
    private Label messageLabel;

    private ToggleGroup toggleGroup;

    private List<User> users;
    private Dao<User> userDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDao = new UserDao();
        messageLabel.setText("");

        users = userDao.getAll();

        toggleGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(toggleGroup);
        femaleRadioButton.setToggleGroup(toggleGroup);
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void saveButtonOnAction(ActionEvent event) {

        users.stream().forEach(System.out::println);

        boolean created = users.stream()
                .anyMatch(user -> user.getUsername().equals(usernameTextField.getText()));

        if (created) {
            messageLabel.setText(String.format("User with the %s user name already exists!", usernameTextField.getText()));
            messageLabel.setStyle("-fx-text-fill: red");
        } else  if (!isFormValid()) {
            messageLabel.setText("Fill the form!");
            messageLabel.setStyle("-fx-text-fill: red");
        } else if (!passwordPasswordField.getText().equals(confirmPasswordField.getText())) {
            messageLabel.setText("Password does not match!");
            messageLabel.setStyle("-fx-text-fill: red");
        } else if (toggleGroup.getSelectedToggle() == null) {
            messageLabel.setText("Choose a gender!");
            messageLabel.setStyle("-fx-text-fill: red");
        } else {
            try {
                String gender = "M";

                if (toggleGroup.getSelectedToggle() == femaleRadioButton)
                    gender = "F";

                Integer age = Integer.parseInt(ageTextField.getText());
                registerUser(firstnameTextField.getText(), lastnameTextField.getText(), usernameTextField.getText(), passwordPasswordField.getText(),
                        locationTextField.getText(), age, gender);
                messageLabel.setText("You are set!");
                messageLabel.setStyle("-fx-text-fill: green");
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                messageLabel.setText("Enter correct age!");
                messageLabel.setStyle("-fx-text-fill: red");
            }

        }
    }

    private void registerUser(String firstname, String lastname, String username, String password, String location, Integer age, String gender) {
        userDao.insert(new User(firstname, lastname, username, password, location, age, gender));
    }

    private boolean isFormValid() {
        return !firstnameTextField.getText().isBlank() && !lastnameTextField.getText().isBlank() && !usernameTextField.getText().isBlank() &&
                    !locationTextField.getText().isBlank() && !ageTextField.getText().isBlank() &&
                    !passwordPasswordField.getText().isBlank() && !confirmPasswordField.getText().isBlank();
    }




}
