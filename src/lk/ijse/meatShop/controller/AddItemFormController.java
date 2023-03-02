package lk.ijse.meatShop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.meatShop.bo.BOFactory;
import lk.ijse.meatShop.bo.custom.AddItemBO;
import lk.ijse.meatShop.dto.ItemDTO;
import lk.ijse.meatShop.dto.StocksDTO;
import lk.ijse.meatShop.util.Navigation;
import lk.ijse.meatShop.util.Routes;
import lk.ijse.meatShop.view.TM.StocksTm;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddItemFormController implements Initializable {

    @FXML
    private AnchorPane pane1;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private Label lblItemcode;

    @FXML
    private JFXComboBox combcatagry;

    @FXML
    private TableView tblStoks;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colcatagary;

    @FXML
    private TableColumn coldesc;

    @FXML
    private TableColumn colqty;

    AddItemBO addItemBO = (AddItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADDITEM);

    @FXML
    void AddItemOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        ItemDTO item=new ItemDTO();
        item.setItem_code(lblItemcode.getText());
        item.setCategory(String.valueOf(combcatagry.getValue()));
        item.setDescription(txtDescription.getText());
        item.setUnit_Price(0.00);
        item.setQty_onHand(0);


           if (addItemBO.saveItem(item)){
               new Alert(Alert.AlertType.CONFIRMATION, "Added Successful...!").show();
               txtDescription.clear();
               Nextid();
               setTable();
           }else {
               new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
           }


    }



    @FXML
    void AddToShowroomOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ADDTOSHOWCASE, pane1);

    }

    @FXML
    void BuyItemOnaction(ActionEvent event) throws IOException {
       // Navigation.navigate(Routes., pane1);
        Navigation.navigate(Routes.BUYITEM, pane1);


    }

    @FXML
    void BuyOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.BUYITEM, pane1);

    }
    private void Nextid() {

        try {

                lblItemcode.setText(addItemBO.generateNewCustomerID());



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTable() {
        tblStoks.getItems().clear();
        try {
            for (StocksDTO c : addItemBO.getAllStoks()) {
                tblStoks.getItems().add(new StocksTm(c.getCode(), c.getCategory(), c.getDescription(), c.getQty_onHand()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    String[] category ={"Meat", "Fish","Other"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combcatagry.getItems().addAll(category);
        Nextid();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colcatagary.setCellValueFactory(new PropertyValueFactory<>("category"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("QtyonHand"));
        setTable();



    }
}
