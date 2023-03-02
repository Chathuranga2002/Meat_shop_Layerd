package lk.ijse.meatShop.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dto.*;
import lk.ijse.meatShop.entity.Item;

import java.sql.SQLException;

public interface ChashierDashBordBO extends SuperBO {
    boolean OrderConfrom(OrdersDTO dto)throws SQLException, ClassNotFoundException;
    String generateNewOrderID() throws SQLException, ClassNotFoundException;
    ObservableList<String> getAllitemcode() throws SQLException, ClassNotFoundException;
    ObservableList<String> getAllcusid() throws SQLException, ClassNotFoundException;
    ItemDTO decItem(String code)throws SQLException, ClassNotFoundException;
    CustomerDTO serchCustomer(String id)throws SQLException, ClassNotFoundException;
}
