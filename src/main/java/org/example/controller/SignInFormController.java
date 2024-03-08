package org.example.controller;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import org.example.service.Custom.SignInFormService;
//import org.example.service.ServiceFactory;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//public class SignInFormController {
//    @FXML
//    private AnchorPane root;
//
//    @FXML
//    private PasswordField txtPassword;
//
//    @FXML
//    private TextField txtUserName;
//
//    private final String adminUserName = "admin";
//    private final String adminPassword = "a1234";
//
//    SignInFormService signInFormService = (SignInFormService) ServiceFactory.getServiceFactory()
//            .getService(ServiceFactory.ServiceTypes.SIGN_IN_FROM);
//
//    @FXML
//    void btnLoginOnAction(ActionEvent event) throws IOException {
//
//        String enteredUserName = txtUserName.getText();
//        String enteredPassword = txtPassword.getText();
//
//        if(enteredUserName.equals(adminUserName) && enteredPassword.equals(adminPassword)){
//            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/AdminDashboard_form.fxml"));
//            Scene scene = new Scene(anchorPane);
//            Stage stage = (Stage) root.getScene().getWindow();
//            stage.setScene(scene);
//            stage.setTitle("Admin Form");
//            stage.centerOnScreen();
//            stage.show();
//
//        }
//
//        try {
//            boolean userIsExist = signInFormService.isExistUser(enteredUserName,enteredPassword);
//            if (userIsExist){
//                navigateToUserWindow();
//            }
//        } catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
//        }
//
//    }
//
//    @FXML
//    void btnSignUpOnAction(ActionEvent event) throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SignUp_form.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("SignUp Form");
//        stage.centerOnScreen();
//        stage.show();
//    }
//
//    private void navigateToUserWindow() throws IOException {
//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/UserDashboard_form.fxml"));
//        Scene scene = new Scene(anchorPane);
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(scene);
//        stage.setTitle("User Dashboard");
//        stage.centerOnScreen();
//        stage.show();
//    }
//}
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.service.Custom.SignInFormService;
import org.example.service.ServiceFactory;

import java.io.IOException;
import java.sql.SQLException;

public class SignInFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    private final String adminUserName = "admin";
    private final String adminPassword = "a1234";

    SignInFormService signInFormService = (SignInFormService) ServiceFactory.getServiceFactory()
            .getService(ServiceFactory.ServiceTypes.SIGN_IN_FROM);

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String enteredUserName = txtUserName.getText();
        String enteredPassword = txtPassword.getText();

        try {
            if (enteredUserName.equals(adminUserName) && enteredPassword.equals(adminPassword)) {
                openAdminDashboard();
            } else {
                boolean userExists = signInFormService.isExistUser(enteredUserName, enteredPassword);
                if (userExists) {
                    openUserDashboard();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
                }
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        openSignUpForm();
    }

    private void openAdminDashboard() throws IOException {
        openDashboard("/view/AdminDashboard_form.fxml", "Admin Form");
    }

    private void openUserDashboard() throws IOException {
        openDashboard("/view/UserDashboard_form.fxml", "User Dashboard");
    }

    private void openSignUpForm() throws IOException {
        openDashboard("/view/SignUp_form.fxml", "Sign Up Form");
    }

    private void openDashboard(String fxmlPath, String title) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.centerOnScreen();
        stage.show();
    }
}
