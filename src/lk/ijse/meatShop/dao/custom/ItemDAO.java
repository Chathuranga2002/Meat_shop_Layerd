package lk.ijse.meatShop.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.meatShop.dao.CrudDAO;
import lk.ijse.meatShop.entity.Buy_detail;
import lk.ijse.meatShop.entity.Item;
import lk.ijse.meatShop.entity.Order_detail;
import lk.ijse.meatShop.entity.Stocks;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item> {
    boolean UpdateQty(ArrayList<Order_detail> stocks) throws SQLException, ClassNotFoundException;
    boolean updateBuy(Item entity) throws SQLException, ClassNotFoundException;
    ObservableList<String> getAllItemCode() throws SQLException, ClassNotFoundException;

}
