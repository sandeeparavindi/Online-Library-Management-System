package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.dto.BookDto;
import org.example.service.Custom.BorrowingBookService;
import org.example.service.ServiceFactory;
import org.example.tm.BorrowingBookTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BorrowingFormController {

    @FXML
    private JFXComboBox<String> cmbBookTittle;

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
    private TableView<BorrowingBookTm> tblBorrowingBooks;

    BorrowingBookService borrowingBookService = (BorrowingBookService) ServiceFactory.getServiceFactory()
            .getService(ServiceFactory.ServiceTypes.BORROWING_BOOK);
    public void initialize() {
        setCellValueFactory();
        generateNextBorrowingBookId();
        setDate();
        loadBookTittle();
    }

    private void setCellValueFactory() {
        colBorrowingId.setCellValueFactory(new PropertyValueFactory<>("borrowing_id"));
        colTittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("due-date"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void generateNextBorrowingBookId() {
        try {
            String bookId = borrowingBookService.generateNextBorrowingBookId();
            lblBorrowingId.setText(bookId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadBookTittle() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<BookDto> bookDtos = borrowingBookService.loadAllBook();

            for (BookDto bookDto: bookDtos) {
                obList.add(bookDto.getTittle());
            }
            cmbBookTittle.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        String date = String.valueOf(LocalDate.now());
        lblBorrowingDate.setText(date);
    }


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

    @FXML
    void cmbBookOnAction(ActionEvent event) {
        String tittle = cmbBookTittle.getValue();

        try {
            BookDto bookDto = borrowingBookService.searchBookID(tittle);

            lblBookId.setText(String.valueOf(bookDto.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
