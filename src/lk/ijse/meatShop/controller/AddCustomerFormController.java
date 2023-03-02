package lk.ijse.meatShop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.meatShop.bo.BOFactory;
import lk.ijse.meatShop.bo.custom.AddCustomerBO;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.view.TM.CustomerTM;

import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    @FXML
    private AnchorPane pane1;

    @FXML
    private JFXTextField txtname;

    @FXML
    private JFXTextField txtaddres;

    @FXML
    private JFXTextField txtmobileno;

    @FXML
    private JFXTextField txtcusid;

    @FXML
    private TableView<CustomerTM> Tablecustomer;

    @FXML
    private TableColumn colid;

    @FXML
    private TableColumn colname;

    @FXML
    private TableColumn Addrescoladdress;

    @FXML
    private TableColumn colcontactno;

    AddCustomerBO customerBO  = (AddCustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @FXML
    void AddToDbOnaction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id=txtcusid.getText();
        String name=txtname.getText();
        String address=txtaddres.getText();
        String telno=txtmobileno.getText();

        //boolean isAdded = CustomerModel.save(customer);
    //add customer
        if (customerBO.addCustomer(new CustomerDTO(id,name,address,telno))) {
            new Alert(Alert.AlertType.CONFIRMATION, "Added Successful...!").show();
            cler();
            Nextid();
            setTable();
            setvalus();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }

    }

    @FXML
    void FeedbackOnaction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/ijse/meatShop/view/AddFeedbackForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Feedback");

    }

    @FXML
    void SearchOnaction(ActionEvent event) {
        try {
            //serch
           CustomerDTO customer=customerBO.serchCustomer(txtcusid.getText());
            if (customer!= null) {
                fillData(customer);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void UpdateOnaction(ActionEvent event) throws SQLException, ClassNotFoundException {
        CustomerDTO customer=new CustomerDTO(txtcusid.getText(),txtname.getText(),txtaddres.getText(),txtmobileno.getText());
        if (customerBO.updateCustomer(customer)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successful...!").show();
            cler();
            Nextid();
            setTable();
            setvalus();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }

    }
    private void Nextid() {

        try {

                txtcusid.setText(customerBO.generateNewCustomerID());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void cler(){
        txtmobileno.setText("");
        txtaddres.setText("");
        txtname.setText("");
        txtname.setText("");
    }
    private void setTable() {
        Tablecustomer.getItems().clear();
        try {
            //get all customer
            for (CustomerDTO c : customerBO.getAllCustomers()) {
                Tablecustomer.getItems().add(new CustomerTM(c.getCus_id(), c.getName(), c.getAddress(), c.getTel_no()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setvalus(){
        colid.setCellValueFactory(new PropertyValueFactory<>("cus_id"));
        colcontactno.setCellValueFactory(new PropertyValueFactory<>("tel_no"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        Addrescoladdress.setCellValueFactory(new PropertyValueFactory<>("address"));

    }
    private void fillData(CustomerDTO customer) {

        txtname.setText(customer.getName());
        txtaddres.setText(customer.getAddress());
        txtmobileno.setText(customer.getTel_no());

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Nextid();
        setTable();
        setvalus();
    }
}
