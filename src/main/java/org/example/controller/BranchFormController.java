package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.dto.BranchDto;
import org.example.service.Custom.BranchService;
import org.example.service.ServiceFactory;
import org.example.tm.BranchTm;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class BranchFormController {
    @FXML
    private TableColumn<?, ?> colBranchName;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colManager;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<BranchTm> tblBranch;

    @FXML
    private TextField txtBranchName;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtManager;

    BranchService branchService = (BranchService) ServiceFactory.getServiceFactory().
            getService(ServiceFactory.ServiceTypes.BRANCH);

    public void initialize() {
        setCellValueFactory();
        loadAllBranches();
    }

    private void setCellValueFactory(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colManager.setCellValueFactory(new PropertyValueFactory<>("manager"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
    }

    private void loadAllBranches() {

        ObservableList<BranchTm> obList = FXCollections.observableArrayList();
        try {
            List<BranchDto> branchDtos = branchService.loadAllBranches();

            for (BranchDto dto : branchDtos) {
                obList.add(
                        new BranchTm(
                                dto.getCode(),
                                dto.getName(),
                                dto.getManager(),
                                dto.getLocation()
                        )
                );
            }
            tblBranch.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        boolean isBranchCodeValidated = ValidateBranch();
        boolean isBranchNameValidated = ValidateBranch();
        boolean isBranchManagerValidated = ValidateBranch();
        boolean isBranchLocationValidated = ValidateBranch();

        if (isBranchCodeValidated
                && isBranchNameValidated
                && isBranchManagerValidated
                && isBranchLocationValidated
        ) {
            int code = Integer.parseInt(txtCode.getText());
            String name = txtBranchName.getText();
            String manager = txtManager.getText();
            String location = txtLocation.getText();

            var dto = new BranchDto(code, name, manager, location);

            try {
                boolean isAdd = branchService.addBranch(dto);
                if (isAdd) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Added Branch!").show();
                    clearFields();
                }

            } catch (SQLException e){
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private boolean ValidateBranch() {

        String codeText = txtCode.getText();
        boolean isBranchCodeValidated = Pattern.compile("[0-9]{4}").matcher(codeText).matches();
        if (!isBranchCodeValidated) {
            new Alert(Alert.AlertType.ERROR, "invalid branch code!").show();
            return false;
        }

        String nameText = txtBranchName.getText();
        boolean isBranchNameValidated = Pattern.compile("[A-z](.*)").matcher(nameText).matches();
        if (!isBranchNameValidated) {
            new Alert(Alert.AlertType.ERROR, "invalid branch name").show();
            return false;
        }

        String managerText = txtManager.getText();
        boolean isBranchManagerValidated = Pattern.compile("[A-z](.*)").matcher(managerText).matches();
        if (!isBranchManagerValidated) {
            new Alert(Alert.AlertType.ERROR, "invalid branch manager name!").show();
            return false;
        }

        String locationText = txtLocation.getText();
        boolean isBranchLocationValidated = Pattern.compile("[A-z](.*)").matcher(locationText).matches();
        if (!isBranchLocationValidated) {
            new Alert(Alert.AlertType.ERROR, "invalid branch location!").show();
            return false;
        }
        return true;
    }

    private void fiilFields(BranchDto dto) {
        txtCode.setText(String.valueOf(dto.getCode()));
        txtBranchName.setText(dto.getName());
        txtManager.setText(dto.getManager());
        txtLocation.setText(dto.getLocation());
    }

    void clearFields() {
        txtCode.setText("");
        txtBranchName.setText("");
        txtManager.setText("");
        txtLocation.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
         String code = txtCode.getText();

        try {
            boolean isDeleted = branchService.deleteBranch(code);

            if (isDeleted) {
                tblBranch.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "branch deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        int code = Integer.parseInt(txtCode.getText());
        String name = txtBranchName.getText();
        String manager = txtManager.getText();
        String location = txtLocation.getText();

        var dto = new BranchDto(code, name, manager, location);

        try {
            boolean isUpdated = branchService.updateBranch(dto);
            System.out.println(isUpdated);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "branch updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String code = txtCode.getText();

        try {
            BranchDto dto = branchService.searchBranch(code);

            if(dto != null) {
                fiilFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "branch not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
