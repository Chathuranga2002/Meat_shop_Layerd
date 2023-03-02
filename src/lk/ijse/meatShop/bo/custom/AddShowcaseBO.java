package lk.ijse.meatShop.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dto.ItemDTO;
import lk.ijse.meatShop.dto.StocksDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddShowcaseBO extends SuperBO {
    boolean addShowCase(int qty,String id,double unitePrice)throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    ArrayList<StocksDTO> getAllStocs() throws SQLException, ClassNotFoundException;

    ObservableList<String> getAllCode() throws SQLException, ClassNotFoundException;

    StocksDTO decStoks(String code)throws SQLException, ClassNotFoundException;
}
