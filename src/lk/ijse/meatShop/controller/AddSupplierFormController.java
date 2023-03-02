package lk.ijse.meatShop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.meatShop.bo.BOFactory;
import lk.ijse.meatShop.bo.custom.AddShowcaseBO;
import lk.ijse.meatShop.bo.custom.AddSupplierBO;
import lk.ijse.meatShop.dto.SupplierDTO;
import lk.ijse.meatShop.util.Navigation;
import lk.ijse.meatShop.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSupplierFormController implements Initializable {
    AddSupplierBO supplierBO = (AddSupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADDSUPPLIER);
    public Label lblId;
    @FXML
    private AnchorPane pane1;

    @FXML
    private JFXTextField txtname;

    @FXML
    private JFXTextField txtaddres;

    @FXML
    private JFXTextField txtnic;

    @FXML
    private JFXTextField txtmobileno;

    @FXML
    private JFXTextField txtid;


    @FXML
    void AddSupplierOnaction(ActionEvent event) {

    }

    @FXML
    void SearchOnaction(ActionEvent event) {
        try {
            SupplierDTO supplier = supplierBO.serchSupplier(txtid.getText());
            if (supplier!= null) {
                fillData(supplier);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void ViewSupplierOnaction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VIEWSUP, pane1);


    }
    @FXML
    public void UpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        new SupplierDTO(lblId.getText(),txtname.getText(),txtaddres.getText(),txtnic.getText(),
                txtmobileno.getText());

        boolean isUpdate = supplierBO.updateSupplier(new SupplierDTO(lblId.getText(),txtname.getText(),txtaddres.getText(),txtnic.getText(),
                txtmobileno.getText()));

        if (isUpdate) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successful...!").show();
            reset();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }
    }
    @FXML
    public void AddSuoOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        boolean isAdded = supplierBO.addSupplier(new SupplierDTO(lblId.getText(),txtname.getText(),txtaddres.getText(),txtnic.getText(),
                txtmobileno.getText()));
        if (isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION, "Added Successful...!").show();
            reset();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }
    }
    private void fillData(SupplierDTO supplier) {

        lblId.setText(supplier.getSup_id());
        txtnic.setText(supplier.getNic());
        txtname.setText(supplier.getName());
        txtaddres.setText(supplier.getAddress());
        txtmobileno.setText(supplier.getTel_no());

    }
    private void Nextid() {

        try {

                lblId.setText(supplierBO.generateNewSupId());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void reset(){
        Nextid();
        txtid.setText("");
        txtaddres.setText("");
        txtmobileno.setText("");
        txtname.setText("");
        txtnic.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Nextid();
    }

    public void SearchonAction(ActionEvent actionEvent) {
        try {
            SupplierDTO supplier = supplierBO.serchSupplier(txtid.getText());
            if (supplier!= null) {
                fillData(supplier);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
