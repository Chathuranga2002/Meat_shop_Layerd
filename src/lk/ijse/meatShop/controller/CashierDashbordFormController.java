package lk.ijse.meatShop.controller;

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
import javafx.scene.text.Text;
import lk.ijse.meatShop.bo.BOFactory;
import lk.ijse.meatShop.bo.custom.ChashierDashBordBO;
import lk.ijse.meatShop.db.DBConnection;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.dto.ItemDTO;
import lk.ijse.meatShop.dto.OrdersDTO;
import lk.ijse.meatShop.entity.Order_detail;


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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CashierDashbordFormController implements Initializable {

    public static String empid="E001";
    public Text txttime;
    public TableColumn colSaction;
    public TableColumn colStatal;
    public JFXTextField txtContactno;
    @FXML
    private AnchorPane Pane;

    @FXML
    private Label lbltime;

    @FXML
    private Label lblChname;

    @FXML
    private Label lbltitile;

    @FXML
    private AnchorPane pane;

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
    private Label lbloderid;

    @FXML
    private Label lblqtyonhand;

    @FXML
    private Label lbluniteprice;

    @FXML
    private Label lbldesc;

    @FXML
    private Label lbldate;

    @FXML
    private JFXTextField txtqty;

    @FXML
    private JFXComboBox<String> cobtokenno;

    @FXML
    private Label lbltatal;

    @FXML
    private JFXComboBox<String> cobcusid;
    ChashierDashBordBO dashBordBO  = (ChashierDashBordBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACEORDER);

    @FXML
    private JFXComboBox<String> cobitemcode;
    ObservableList<BuyCartTM> CartTMS= FXCollections.observableArrayList();
    public static double tot=0;
    String cata;

    @FXML
    void AddToCartOnAction(ActionEvent event) {
       String code=itemcode;
       String catagry=cata;
        String desc=lbldesc.getText();
        double price= Double.parseDouble(lbluniteprice.getText());
        int qty= Integer.parseInt(txtqty.getText());
        double total=price * qty;
        Button btnDelete = new Button("Delete");


        tot=tot+(price*qty);
        lbltatal.setText(String.valueOf(tot));





        txtqty.setText("");


        if (!CartTMS.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tblcart.getItems().size(); i++) {
                if (colitemcode.getCellData(i).equals(code)) {
                    qty += (int) colSqty.getCellData(i);
                    total = price * qty;


                    CartTMS.get(i).setQty(qty);
                    CartTMS.get(i).setToatal(total);
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
        CartTMS.add(new BuyCartTM(code,catagry,desc, price, total,qty, btnDelete));
        System.out.println(code+" "+catagry+" "+desc+ " "+price+" "+ total +" "+qty);
        tblcart.setItems(CartTMS);




    }

    @FXML
    void AddnewCusOnaction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, pane);


    }
    public void cler(){
        tot=0;
        tblcart.getItems().clear();
        CartTMS.clear();
        lbltatal.setText("0.0");
        cobcusid.getItems().clear();
        cobitemcode.getItems().clear();
       // cobtokenno.getItems().clear();

    }

    @FXML
    void ClearOrderOnAction(ActionEvent event) {
        cler();
        Nextid();



    }

    @FXML
    void ConfirmOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String oderid = lbloderid.getText();
        String cusid = String.valueOf(cobcusid.getValue());
        String date=lbldate.getText();
        ArrayList<Order_detail> cartDetails = new ArrayList<>();




        /* load all cart items' to orderDetails arrayList */
        for (int i = 0; i < tblcart.getItems().size(); i++) {
            /* get each row details to (PlaceOrderTm)tm in each time and add them to the orderDetails */
            BuyCartTM tm = CartTMS.get(i);
            cartDetails.add(new Order_detail(oderid, tm.getItemcode(), tm.getQty(), tm.getUniteprice()));
        }


        if (dashBordBO.OrderConfrom(new OrdersDTO(oderid,date,cusid,empid,cartDetails))){
            new Alert(Alert.AlertType.CONFIRMATION, "Added Successful...!").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }

//



    }

    @FXML
    String Cid;
    @FXML
    void CusidCombOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            Cid=cobcusid.getSelectionModel().getSelectedItem().toString();
        }catch (Exception e){
            System.out.println(e);
        }

        CustomerDTO customer = dashBordBO.serchCustomer(Cid);
        lblname.setText(customer.getName());
        lbladdres.setText(customer.getAddress());
        txtContactno.setText(customer.getTel_no());


    }

    @FXML
    void CustomerOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.CUSTOMER,pane);



    }
    String itemcode;
    @FXML
    void ItemcodeOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            itemcode=cobitemcode.getSelectionModel().getSelectedItem().toString();
        }catch (Exception e){
            System.out.println(e);
        }

        ItemDTO item = dashBordBO.decItem(itemcode);
        lbldesc.setText(item.getDescription());
        lbluniteprice.setText(String.valueOf(item.getUnit_Price()));
        lblqtyonhand.setText(String.valueOf(item.getQty_onHand()));
        cata=item.getCategory();

    }

    @FXML
    void LogoutOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.LOGIN,Pane);

    }

    @FXML
    void OrdersOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ORDERVIEW,pane);

    }

    @FXML
    void PlaceorderOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.CHASHIER,Pane);

    }

    @FXML
    void PrintBillOnAction(ActionEvent event) {
        InputStream resourse = this.getClass()
                .getResourceAsStream("/lk/ijse/meatShop/view/report/Bill.jrxml");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Total",String.valueOf(tot));
        hashMap.put("C_name", lblname.getText());
        hashMap.put("OrdId", lbloderid.getText());
        hashMap.put("Chasiername", lblChname.getText());

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

    @FXML
    void tokenOnAction(ActionEvent event) {

    }
    private void setTime() {
        Thread clock = new Thread() {
            public void run() {
                while (true) {
                    DateFormat hour = new SimpleDateFormat("hh:mm:ss");
                    txttime.setText(hour.format(new Date()));

                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        clock.start();
    }
    private void Nextid() {
        try {

                lbloderid.setText(dashBordBO.generateNewOrderID());


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void setCellValueFactory() {
        colitemcode.setCellValueFactory(new PropertyValueFactory("itemcode"));
        colScategory.setCellValueFactory(new PropertyValueFactory("category"));
        colSdesc.setCellValueFactory(new PropertyValueFactory("desccrip"));
        colSqty.setCellValueFactory(new PropertyValueFactory("qty"));
        colSuniteprice.setCellValueFactory(new PropertyValueFactory("uniteprice"));
        colStatal.setCellValueFactory(new PropertyValueFactory("toatal"));
        colSaction.setCellValueFactory(new PropertyValueFactory("btnDelete"));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTime();
        lbldate.setText(DateAndTime.dateNow());
        Nextid();
        setCellValueFactory();
        combo();
        cobtokenno.setVisible(false);



    }
    private void combo(){
        try {

                cobcusid.setItems(dashBordBO.getAllcusid());

                cobitemcode.setItems(dashBordBO.getAllitemcode());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void SerchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=txtContactno.getText();
        CustomerDTO customer = dashBordBO.serchCustomer(id);
        cobcusid.setValue(customer.getCus_id());
        lblname.setText(customer.getName());
        lbladdres.setText(customer.getAddress());
        txtContactno.setText(customer.getTel_no());
    }
}
