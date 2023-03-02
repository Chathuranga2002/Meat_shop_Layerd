package lk.ijse.meatShop.dao.custom;

import lk.ijse.meatShop.dao.CrudDAO;
import lk.ijse.meatShop.entity.Buy_detail;
import lk.ijse.meatShop.entity.Order_detail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDeatilsDAO extends CrudDAO<Order_detail> {
    ArrayList<Order_detail> getAllorders(String id) throws SQLException, ClassNotFoundException;
    boolean saveOrderDetail(ArrayList<Order_detail> cartDetails) throws SQLException, ClassNotFoundException;
}
