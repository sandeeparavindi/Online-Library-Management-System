package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashboardFormController {
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label txtBookCount;

    @FXML
    private Label txtBranchCount;

    @FXML
    private Label txtUserCount;

    @FXML
    void btnBorrowingOnAction(ActionEvent event) throws IOException {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(
                FXMLLoader.load(this.getClass().getResource("/view/borrowing_form.fxml"))
        );
    }

    @FXML
    void btnCredentialOnAction(ActionEvent event) {

    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.
                load(getClass().getResource("/view/UserDashboard_form.fxml"))));
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText("Are you sure you want to log out?");
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                Stage stage = (Stage) pane.getScene().getWindow();
                try {
                    stage.setScene(new Scene(FXMLLoader.
                            load(getClass().getResource("/view/SignIn_form.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.centerOnScreen();
                stage.show();

            } else if (buttonType == buttonTypeNo) {
                alert.close();
            }
        });
    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {

    }


    @FXML
    void btnViewOnAction(ActionEvent event) throws IOException {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(
                FXMLLoader.load(this.getClass().getResource("/view/ViewBook_form.fxml"))
        );
    }
}
