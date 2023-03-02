package lk.ijse.meatShop.dao.custom.impl;

import lk.ijse.meatShop.dao.SQLUtil;
import lk.ijse.meatShop.dao.custom.OrderDeatilsDAO;
import lk.ijse.meatShop.entity.Order_detail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDeatilsDAOImpl implements OrderDeatilsDAO {
    @Override
    public ArrayList<Order_detail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Order_detail entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO order_detail VALUES(?, ?, ?, ?)";
        return SQLUtil.execute(sql, entity.getOrd_id(), entity.getItem_code(), entity.getQty(),
                entity.getUnitPrice());
    }

    @Override
    public boolean update(Order_detail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order_detail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Order_detail> getAllorders(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Order_detail> order  =  new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT  * from order_detail where ord_id=? ",id);
        while (rst.next()) {
            Order_detail orderDetail = new Order_detail(rst.getString(1),rst.getString(2)
                    ,rst.getInt(3),rst.getDouble(4));
            order .add(orderDetail);
        }
        return order ;
    }

    @Override
    public boolean saveOrderDetail(ArrayList<Order_detail> cartDetails) throws SQLException, ClassNotFoundException {
        for (Order_detail cartDetail : cartDetails) {
            if (!add(cartDetail)) {
                return false;
            }
        }
        return true;
    }
}
