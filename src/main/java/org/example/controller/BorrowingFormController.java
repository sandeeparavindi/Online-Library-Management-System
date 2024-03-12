package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class BorrowingFormController {

    @FXML
    private JFXComboBox<?> cmbBookTittle;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBorrowingId;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colTittle;

    @FXML
    private TableColumn<?, ?> colUserEmail;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblBorrowingDate;

    @FXML
    private Label lblBorrowingId;

    @FXML
    private Label lblDueDate;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<?> tblBorrowingBooks;

    @FXML
    void btnBorrowBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewBookOnAction(ActionEvent event) throws IOException {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(
                FXMLLoader.load(this.getClass().getResource("/view/ViewBook_form.fxml"))
        );
    }
}
