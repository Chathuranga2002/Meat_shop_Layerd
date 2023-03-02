package lk.ijse.meatShop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.meatShop.bo.BOFactory;
import lk.ijse.meatShop.bo.custom.BuyItemBO;
import lk.ijse.meatShop.db.DBConnection;
import lk.ijse.meatShop.dto.BuyDTO;
import lk.ijse.meatShop.dto.StocksDTO;
import lk.ijse.meatShop.dto.SupplierDTO;
import lk.ijse.meatShop.entity.Buy_detail;
import lk.ijse.meatShop.util.DateAndTime;
import lk.ijse.meatShop.util.Navigation;
import lk.ijse.meatShop.util.Routes;
import lk.ijse.meatShop.view.TM.BuyCartTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class BuyItemFormController implements Initializable {
    public JFXButton btnrecept;
    public JFXTextField txtContact;
    ObservableList<BuyCartTM> buyCartTMS= FXCollections.observableArrayList();
    public Label lbldate;
    public TableColumn colSAction;
    public TableColumn coltotal;
    @FXML
    private AnchorPane pane1;

    @FXML
    private TableView<BuyCartTM> tblcart;

    @FXML
    private TableColumn colitemcode;

    @FXML
    private TableColumn colScategory;

    @FXML
    private TableColumn colSdesc;

    @FXML
    private TableColumn colSuniteprice;

    @FXML
    private TableColumn colSqty;

    @FXML
    private Label lblcontactno;

    @FXML
    private Label lbladdres;

    @FXML
    private Label lblname;

    @FXML
    private Label lblbuyingid;

    @FXML
    private Label lbluniteprice;

    @FXML
    private Label lbldesc;


    @FXML
    private JFXTextField txtqty;

    @FXML
    private Label lbltatal;

    @FXML
    private JFXComboBox cobsupid;

    @FXML
    private JFXComboBox cobitemcode;

    @FXML
    private JFXTextField txtuniteprice;

    @FXML
    private Label lblnic;

    @FXML
    private Label lblamuntpayble;

    @FXML
    private JFXTextField txtadvance;

    BuyItemBO buyItemBO  = (BuyItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BUYITEM);

    static double tot = 0;
    @FXML
    void AddToCartOnAction(ActionEvent event) {

        String itemcode=icode;
        String catagry=lbluniteprice.getText();
        String desc=lbldesc.getText();
        double price= Double.parseDouble(txtuniteprice.getText());
        int qty= Integer.parseInt(txtqty.getText());
        double total=price * qty;
        Button btnDelete = new Button("Delete");


        tot=tot+(price*qty);
        lbltatal.setText(String.valueOf(tot));





        txtqty.setText("");
        txtuniteprice.setText("");

        if (!buyCartTMS.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tblcart.getItems().size(); i++) {
                if (colitemcode.getCellData(i).equals(itemcode)) {
                    qty += (int) colSqty.getCellData(i);
                    total = price * qty;


                    buyCartTMS.get(i).setQty(qty);
                    buyCartTMS.get(i).setToatal(total);
                    tblcart.refresh();
                    return;
                }

            }
        }

        /* set delete button to some action before it put on obList */
        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                BuyCartTM tm = tblcart.getSelectionModel().getSelectedItem();
                tblcart.getItems().removeAll(tblcart.getSelectionModel().getSelectedItem());
            }
        });
        buyCartTMS.add(new BuyCartTM(itemcode,catagry,desc, price, total,qty, btnDelete));
        System.out.println(itemcode+" "+catagry+" "+desc+ " "+price+" "+ total +" "+qty);
        tblcart.setItems(buyCartTMS);




    }

    @FXML
    void AddnewItemOnaction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ADDITEM, pane1);

    }

    @FXML
    void BuyItemOnaction(ActionEvent event) throws IOException {


    }

    @FXML
    void ClearOrderOnAction(ActionEvent event) throws IOException {
        cler();
    }




    @FXML
    void ConfirmOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Buyid = lblbuyingid.getText();
        String Supid = String.valueOf(cobsupid.getValue());
         String date=lbldate.getText();
        double advance= Double.parseDouble(txtadvance.getText());
        double topayvalue= Double.parseDouble(lblamuntpayble.getText());


        ArrayList<Buy_detail> cartDetails = new ArrayList<>();

        /* load all cart items' to orderDetails arrayList */
        for (int i = 0; i < tblcart.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the orderDetails */
            BuyCartTM tm = buyCartTMS.get(i);
            cartDetails.add(new Buy_detail(Buyid, tm.getItemcode(), tm.getQty(), tm.getUniteprice()));
        }
        BuyDTO buyDTO = new BuyDTO( Buyid,date,Supid,tot,advance,topayvalue,cartDetails);

            ;
            if (buyItemBO.buyConfrom(buyDTO)){

                new Alert(Alert.AlertType.CONFIRMATION, "Added Successful...!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
            }


    }

    @FXML
    void PrintReceiptOnAction(ActionEvent event) {
        InputStream resourse = this.getClass()
                .getResourceAsStream("/lk/ijse/meatShop/view/report/Recipt.jrxml");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Total", String.valueOf(tot));
        hashMap.put("Buyid", lblbuyingid.getText());
        hashMap.put("Advance", txtadvance.getText());
        hashMap.put("Supname", lblname.getText());
        hashMap.put("blance", lblamuntpayble.getText());

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resourse);
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport(jasperReport, hashMap, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

            //  JasperPrintManager.printReport(jasperPrint,true);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
    private void Nextid() {

        try {

                lblbuyingid.setText(buyItemBO.generateNewBuyID());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setCellValueFactory();
        lbldate.setText(DateAndTime.dateNow());




        try {

                cobsupid.setItems(buyItemBO.getAllsupid());

                cobitemcode.setItems(buyItemBO.getAllitemcode());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Nextid();

    }
        String sid;
    public void SupidcomOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
         sid=cobsupid.getSelectionModel().getSelectedItem().toString();
        SupplierDTO supDesc =buyItemBO.serchSupplier(sid);
        lblname.setText(supDesc.getName());
        lbladdres.setText(supDesc.getAddress());
        lblnic.setText(supDesc.getNic());
        txtContact.setText(supDesc.getTel_no());

    }
    String icode;
    public void ItemcodeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
         icode=cobitemcode.getSelectionModel().getSelectedItem().toString();
        StocksDTO itemDesc = buyItemBO.decStoks(icode);
        lbldesc.setText(itemDesc.getDescription());
        lbluniteprice.setText(itemDesc.getCategory());
    }
    private void setCellValueFactory() {
        colitemcode.setCellValueFactory(new PropertyValueFactory("itemcode"));
        colScategory.setCellValueFactory(new PropertyValueFactory("category"));
        colSdesc.setCellValueFactory(new PropertyValueFactory("desccrip"));
        colSqty.setCellValueFactory(new PropertyValueFactory("qty"));
        colSuniteprice.setCellValueFactory(new PropertyValueFactory("uniteprice"));
        coltotal.setCellValueFactory(new PropertyValueFactory("toatal"));
        colSAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));

    }
    private void Setpay(){
        double advance = Double.parseDouble(txtadvance.getText());
        advance=tot-advance;
        lblamuntpayble.setText(String.valueOf(advance));
    }


    public void AdvanceAction(ActionEvent actionEvent) {
        Setpay();
    }

    public void cler(){
        Nextid();
        tot=0;
        tblcart.getItems().clear();
        buyCartTMS.clear();
        lblamuntpayble.setText("0.0");
        lbltatal.setText("0.0");
        txtadvance.setText("");
    }

    public void SerchCusOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       String id=txtContact.getText();
        SupplierDTO supDesc = buyItemBO.serchSupplier(id);
        lblname.setText(supDesc.getName());
        lbladdres.setText(supDesc.getAddress());
        lblnic.setText(supDesc.getNic());
        cobsupid.setValue(supDesc.getSup_id());
    }
}
