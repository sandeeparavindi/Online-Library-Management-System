package org.example.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.dto.BookDto;
import org.example.dto.BranchDto;
import org.example.service.Custom.BookService;
import org.example.service.ServiceFactory;
import org.example.tm.BookTm;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class BookFormController {
    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colTittle;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableView<BookTm> tblBook;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtTittle;

    @FXML
    private JFXComboBox<String> cmbBranchName;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colStatus;

    BookService bookService = (BookService) ServiceFactory.getServiceFactory()
            .getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize() {
        setCellValueFactory();
        loadAllBooks();
        loadAllBranchNames();
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
//                obList.add(
//                        new BookTm(
//                                dto.getId(),
//                                dto.getTittle(),
//                                dto.getGenre(),
//                                dto.getAuthor(),
//                                dto.getBranch()
//                        )
//                );
//                Button btn = new Button("Available");
                String status = "Available";
                var tm = new BookTm(
                        dto.getId(),
                        dto.getTittle(),
                        dto.getGenre(),
                        dto.getAuthor(),
                        dto.getBranch(),
                        status
//                        btn
                );
                obList.add(tm);
            }
            tblBook.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        boolean isBookIDValidated = ValidateBook();
        boolean isBookTittleValidated = ValidateBook();
        boolean isBookGenresValidated = ValidateBook();
        boolean isBookAuthorValidated = ValidateBook();

        if (isBookIDValidated
        && isBookTittleValidated
        && isBookGenresValidated
        && isBookAuthorValidated) {

            int id = Integer.parseInt(txtId.getText());
            String tittle = txtTittle.getText();
            String genre = txtGenre.getText();
            String author = txtAuthor.getText();
            String branch = cmbBranchName.getValue();
            String status = "Available";

            var dto = new BookDto(id, tittle, genre, author, branch, status);

            try {
                boolean isAdd = bookService.addBook(dto);
                if (isAdd) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Added Book!").show();
                    clearFields();
                }

            } catch (SQLException e){
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    private boolean ValidateBook(){

        String idText = txtId.getText();
        boolean isBookIDValidated = Pattern.compile("[0-9]{4}").matcher(idText).matches();
        if (!isBookIDValidated) {
            new Alert(Alert.AlertType.ERROR, "invalid book id!").show();
            return false;
        }

        String tittleText = txtTittle.getText();
        boolean isBookTittleValidated = Pattern.compile("[A-z](.*)").matcher(tittleText).matches();
        if (!isBookTittleValidated) {
            new Alert(Alert.AlertType.ERROR, "invalid book tittle").show();
            return false;
        }

        String genreText = txtGenre.getText();
        boolean isBookGenresValidated = Pattern.compile("[A-z](.*)").matcher(genreText).matches();
        if (!isBookGenresValidated) {
            new Alert(Alert.AlertType.ERROR, "invalid book genre!").show();
            return false;
        }

        String authorText = txtAuthor.getText();
        boolean isBookAuthorValidated = Pattern.compile("[A-z](.*)").matcher(authorText).matches();
        if (!isBookAuthorValidated) {
            new Alert(Alert.AlertType.ERROR, "invalid book author!").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = bookService.deleteBook(id);

            if (isDeleted) {
                tblBook.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "book deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String tittle = txtTittle.getText();
        String genre = txtGenre.getText();
        String author = txtAuthor.getText();
        String branch = cmbBranchName.getValue();
        String status = "Available";

        var dto = new BookDto(id, tittle, genre, author, branch,status);

        try {
            boolean isUpdated = bookService.updateBook(dto);
            System.out.println(isUpdated);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "book updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            BookDto dto = bookService.searchBook(id);

            if(dto != null) {
                fiilFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "book not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void fiilFields(BookDto dto){
        txtId.setText(String.valueOf(dto.getId()));
        txtTittle.setText(dto.getTittle());
        txtGenre.setText(dto.getGenre());
        txtAuthor.setText(dto.getAuthor());
        cmbBranchName.setValue(dto.getBranch());
    }

    private void loadAllBranchNames(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<BranchDto> branchDtoList = bookService.loadAllBranches();

            for (BranchDto dto:branchDtoList) {
                obList.add(dto.getName());
            }
            cmbBranchName.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void clearFields() {
        txtId.setText("");
        txtTittle.setText("");
        txtGenre.setText("");
        txtAuthor.setText("");
        cmbBranchName.setValue("");
    }

}
