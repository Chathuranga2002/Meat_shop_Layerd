package lk.ijse.meatShop.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.meatShop.dao.CrudDAO;
import lk.ijse.meatShop.entity.Buy_detail;
import lk.ijse.meatShop.entity.Stocks;


import java.sql.SQLException;
import java.util.ArrayList;

public interface StoksDAO extends CrudDAO<Stocks> {
    ObservableList<String> getStoksCode() throws SQLException, ClassNotFoundException;
    boolean updateBuy(Stocks entity) throws SQLException, ClassNotFoundException;
    boolean UpdateQty(ArrayList<Buy_detail> stocks) throws SQLException, ClassNotFoundException;
}
