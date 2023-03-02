package lk.ijse.meatShop.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dto.BuyDTO;
import lk.ijse.meatShop.dto.ItemDTO;
import lk.ijse.meatShop.dto.StocksDTO;
import lk.ijse.meatShop.dto.SupplierDTO;

import java.sql.SQLException;

public interface BuyItemBO extends SuperBO {
    boolean buyConfrom(BuyDTO dto)throws SQLException, ClassNotFoundException;
    String generateNewBuyID() throws SQLException, ClassNotFoundException;
    ObservableList<String> getAllitemcode() throws SQLException, ClassNotFoundException;
    ObservableList<String> getAllsupid() throws SQLException, ClassNotFoundException;
    StocksDTO decStoks(String code)throws SQLException, ClassNotFoundException;
    SupplierDTO serchSupplier(String id)throws SQLException, ClassNotFoundException;

}
