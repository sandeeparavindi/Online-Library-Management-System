package org.example.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.dto.BookDto;
import org.example.service.Custom.BookService;
import org.example.service.ServiceFactory;
import org.example.tm.BookTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ViewBookFormController {
    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTittle;

    @FXML
    private TableView<BookTm> tblBook;

    @FXML
    private AnchorPane pane;

    BookService bookService = (BookService) ServiceFactory.getServiceFactory()
            .getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize() {
        setCellValueFactory();
        loadAllBooks();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    try {
                        updateBookStatusInTable();
                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    }
                });
            }
        }, 0, 5000);
    }

    private void updateBookStatusInTable() throws SQLException {
        List<BookDto> bookDtoList = bookService.getAllBooks();
        ObservableList<BookTm> obList = FXCollections.observableArrayList();

        for (BookDto dto : bookDtoList) {
            String status = dto.getStatus();
            var tm = new BookTm(
                    dto.getId(),
                    dto.getTittle(),
                    dto.getGenre(),
                    dto.getAuthor(),
                    dto.getBranch(),
                    status
            );
            obList.add(tm);
        }

        tblBook.setItems(obList);
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    private void loadAllBooks() {

        ObservableList<BookTm> obList = FXCollections.observableArrayList();
        try {
            List<BookDto> dtoList = bookService.getAllBooks();

            for (BookDto dto : dtoList) {

                String status = "Available";
                var tm = new BookTm(
                        dto.getId(),
                        dto.getTittle(),
                        dto.getGenre(),
                        dto.getAuthor(),
                        dto.getBranch(),
                        status
                );
                obList.add(tm);
            }
            tblBook.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBorrowOnAction(ActionEvent event) throws IOException {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(
                FXMLLoader.load(this.getClass().getResource("/view/borrowing_form.fxml"))
        );
    }
}
