package lk.ijse.meatShop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import lk.ijse.meatShop.bo.custom.AddShowcaseBO;
import lk.ijse.meatShop.dto.ItemDTO;
import lk.ijse.meatShop.dto.StocksDTO;
import lk.ijse.meatShop.view.TM.ShowkaseTm;
import lk.ijse.meatShop.view.TM.StocksTm;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddShowcaseFormController implements Initializable {

    public Label lblqtyonstoks;
    @FXML
    private AnchorPane pane1;

    @FXML
    private TableView <StocksTm> tblStoks;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colcatagary;

    @FXML
    private TableColumn coldesc;

    @FXML
    private TableColumn colqty;

    @FXML
    private TableView<ShowkaseTm> tblShowcase;

    @FXML
    private TableColumn colSitemcode;

    @FXML
    private TableColumn colScategory;

    @FXML
    private TableColumn colSdesc;

    @FXML
    private TableColumn colSuniteprice;

    @FXML
    private TableColumn colSqtyonhand;

    @FXML
    private JFXTextField txtqty;

    @FXML
    private JFXComboBox cobitemcode;

    @FXML
    private Label lblcategory;

    @FXML
    private Label lbldescription;

    @FXML
    private JFXTextField txtuniteprice;
    AddShowcaseBO addShowcaseBO = (AddShowcaseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADDTOSHOWCASE);

    @FXML
    void AddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemcode=icode;
        double uniteprice= Double.parseDouble(txtuniteprice.getText());
        int qty= Integer.parseInt(txtqty.getText());

        //trans...
        if (addShowcaseBO.addShowCase(qty,itemcode,uniteprice)){
            new Alert(Alert.AlertType.CONFIRMATION, "Added Successful...!").show();
            cler();
            setTable();
            setvalus();
        }else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }


    }
    ObservableList<StocksTm> stocksTms = FXCollections.observableArrayList();
    ObservableList<ShowkaseTm> showkaseTms = FXCollections.observableArrayList();

    private void setTable() {
        tblStoks.getItems().clear();
        tblShowcase.getItems().clear();
        try {
            for (StocksDTO c : addShowcaseBO.getAllStocs()) {
                tblStoks.getItems().add(new StocksTm(c.getCode(), c.getCategory(), c.getDescription(), c.getQty_onHand()));
            }
            for (ItemDTO c : addShowcaseBO.getAllItems()) {
                tblShowcase.getItems().add(new ShowkaseTm(c.getItem_code(), c.getCategory(), c.getDescription(),c.getUnit_Price(), c.getQty_onHand()));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setvalus(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colcatagary.setCellValueFactory(new PropertyValueFactory<>("category"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("QtyonHand"));

        colSitemcode.setCellValueFactory(new PropertyValueFactory<>("itemcode"));
        colScategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colSdesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSqtyonhand.setCellValueFactory(new PropertyValueFactory<>("QtyonHand"));
        colSuniteprice.setCellValueFactory(new PropertyValueFactory<>("uniteprice"));


    }
    public void combo(){
        try {

                cobitemcode.setItems(addShowcaseBO.getAllCode());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void cler(){
        lbldescription.setText("");
        lblqtyonstoks.setText("");
        lblcategory.setText("");
       txtuniteprice.setText("");
       txtqty.setText("");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setvalus();
        setTable();
        combo();

    }
        String icode;
    public void IsselectItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        icode=cobitemcode.getSelectionModel().getSelectedItem().toString();
        StocksDTO itemDesc = addShowcaseBO.decStoks(icode);
        lblcategory.setText(itemDesc.getCategory());
        lbldescription.setText(itemDesc.getDescription());
        lblqtyonstoks.setText(String.valueOf(itemDesc.getQty_onHand()));
    }
}
