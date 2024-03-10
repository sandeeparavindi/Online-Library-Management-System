package org.example.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.dto.BookDto;
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

    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize() {
        setCellValueFactory();
        loadAllBooks();
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
    }

    private void loadAllBooks() {

        ObservableList<BookTm> obList = FXCollections.observableArrayList();
        try {
            List<BookDto> dtoList = bookService.getAllBooks();

            for (BookDto dto : dtoList) {
                obList.add(
                        new BookTm(
                                dto.getId(),
                                dto.getTittle(),
                                dto.getGenre(),
                                dto.getAuthor()
                        )
                );
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

            var dto = new BookDto(id, tittle, genre, author);

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

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    private void fiilFields(BookDto dto){
        txtId.setText(String.valueOf(dto.getId()));
        txtTittle.setText(dto.getTittle());
        txtGenre.setText(dto.getGenre());
        txtAuthor.setText(dto.getAuthor());
    }

    void clearFields() {
        txtId.setText("");
        txtTittle.setText("");
        txtGenre.setText("");
        txtAuthor.setText("");
    }

}
