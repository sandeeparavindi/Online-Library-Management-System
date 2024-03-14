package org.example.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.dto.BookDto;
import org.example.dto.BorrowingBookDto;

import org.example.service.Custom.BorrowingBookService;
import org.example.service.ServiceFactory;
import org.example.tm.BorrowingBookTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BorrowingFormController {

    private final ObservableList<BorrowingBookTm> obList = FXCollections.observableArrayList();

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
    private TableColumn<?, ?> colReturnBook;

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
        loadBorrowingBooks();
    }

    private void setCellValueFactory() {
        colBorrowingId.setCellValueFactory(new PropertyValueFactory<>("borrowing_id"));
        colTittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        colReturnBook.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void generateNextBorrowingBookId() {

        try {
            String bookId = borrowingBookService.generateNextBorrowingBookId();
            lblBorrowingId.setText(bookId);

            LocalDate dueDate = LocalDate.now().plusWeeks(2);
            lblDueDate.setText(dueDate.toString());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to generate next borrowing ID: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
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
    void btnBorrowBookOnAction(ActionEvent event) throws SQLException {
        String borrowing_id = lblBorrowingId.getText();
        String tittle = cmbBookTittle.getValue();
        String dueDate = lblDueDate.getText();
        int book_id = Integer.parseInt(lblBookId.getText());


        BorrowingBookDto bookDto = new BorrowingBookDto(
            borrowing_id,
            tittle,
            dueDate,
            book_id
    );

    boolean isSaved = borrowingBookService.addBorrowBook(bookDto);
    if (isSaved) {
        new Alert(Alert.AlertType.CONFIRMATION,"saved").show();

        loadBorrowingBooks();
    } else {
        new Alert(Alert.AlertType.ERROR, "can't saved").show();
    }
}

    private void loadBorrowingBooks() {
        try {
            List<BorrowingBookDto> borrowingBooks = borrowingBookService.loadAllBorrowBook();
            tblBorrowingBooks.getItems().clear();

            for (BorrowingBookDto dto : borrowingBooks) {
                Button btn = new Button("Return");
                btn.setCursor(Cursor.HAND);

                btn.setOnAction(event -> {

                    new Alert(Alert.AlertType.INFORMATION, "Book returned: " + dto.getTittle()).show();
                });

                BorrowingBookTm tm = new BorrowingBookTm(
                        dto.getBorrowing_id(),
                        dto.getTittle(),
                        dto.getDueDate(),
                        dto.getBook_id(),
                        btn
                );

                tblBorrowingBooks.getItems().add(tm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
