package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.dto.UserDto;
import org.example.service.Custom.SignUpFromService;
import org.example.service.ServiceFactory;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    SignUpFromService signUpFromService = (SignUpFromService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.SIGN_UP_FROM);

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        try {
            boolean userCheck = txtEmail.getText().equals(signUpFromService.getEmail(txtEmail.getText()));
            if (!userCheck){

                UserDto userDto = new UserDto();
                userDto.setEmail(txtEmail.getText());
                userDto.setName(txtName.getText());
                userDto.setPassword(txtPassword.getText());

                boolean saved = signUpFromService.addUser(userDto);
                if (saved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "user Saved").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "user  not saved").show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING, "Already exist ").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void hyperLoginHereOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/SignIn_form.fxml"));

        Scene scene = new Scene(rootNode);

        root.getChildren().clear();
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Sign-Up Form");
    }
}
