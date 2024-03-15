package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.dto.HistoryDto;
import org.example.entity.HistoryTable;
import org.example.service.Custom.Impl.QueryServiceImpl;
import org.example.service.Custom.QueryService;
import org.example.service.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryFormController {

    @FXML
    private TableColumn<HistoryTable, Integer> colBookId;

    @FXML
    private TableColumn<HistoryTable, Integer> colBorrowingId;

    @FXML
    private TableColumn<HistoryTable, String> colStatus;

    @FXML
    private TableColumn<HistoryTable, String> colTittle;

    @FXML
    private TableColumn<HistoryTable, String> colUserName;

    @FXML
    private TableView<HistoryTable> tblDetails;


    QueryService queryService = (QueryService) ServiceFactory.
            getServiceFactory().getService(ServiceFactory.ServiceTypes.HISTORY);

    @FXML
    public void initialize() {
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colBorrowingId.setCellValueFactory(new PropertyValueFactory<>("borrowingId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colTittle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("bookStatus"));


        List<HistoryDto> historyDtoList = queryService.getUserHistory("sanu@gmail.com");

        ObservableList<HistoryTable> observableList = FXCollections.observableArrayList();
        for (HistoryDto historyDto : historyDtoList) {
            HistoryTable historyTable = new HistoryTable();
            historyTable.setUserName(historyDto.getUserName());
            historyTable.setBorrowingId(Integer.parseInt(historyDto.getBorrowingId()));
            historyTable.setBookId(historyDto.getBookId());
            historyTable.setBookTitle(historyDto.getBookTitle());
            historyTable.setBookStatus(historyDto.getBookStatus());
            observableList.add(historyTable);
        }

        tblDetails.setItems(observableList);
    }

}
