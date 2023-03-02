package lk.ijse.meatShop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.meatShop.bo.BOFactory;
import lk.ijse.meatShop.bo.custom.ViewOrdersBO;
import lk.ijse.meatShop.bo.custom.ViewSupplierBO;
import lk.ijse.meatShop.dto.Order_detailDTO;
import lk.ijse.meatShop.dto.SupplierDTO;
import lk.ijse.meatShop.view.TM.OrderTM;
import lk.ijse.meatShop.view.TM.SupplierTM;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewOrderFormController {

    @FXML
    private AnchorPane pane1;

    @FXML
    private JFXTextField txtordsid;

    @FXML
    private TableView<OrderTM> TalOrders;

    @FXML
    private TableColumn colOid;

    @FXML
    private TableColumn colitemcode;

    @FXML
    private TableColumn colqty;

    @FXML
    private TableColumn coluniteprice;
    ViewOrdersBO viewOrdersBO = (ViewOrdersBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VVIEWORDER);


    @FXML
    void searchOnaction(ActionEvent event) {
        colitemcode.setCellValueFactory(new PropertyValueFactory<>("item_code"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        coluniteprice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colOid.setCellValueFactory(new PropertyValueFactory<>("ord_id"));

        setTable();


    }
    ObservableList<OrderTM> orderTms= FXCollections.observableArrayList();
    private void setTable() {
        TalOrders.getItems().clear();
        try {

            for (Order_detailDTO c : viewOrdersBO.getAllOrd( txtordsid.getText())) {
                TalOrders.getItems().add(new OrderTM(c.getItem_code(), c.getOrd_id(),c.getQty(),c.getUnitPrice()));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
