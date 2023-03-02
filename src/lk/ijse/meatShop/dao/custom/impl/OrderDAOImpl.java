package lk.ijse.meatShop.dao.custom.impl;

import lk.ijse.meatShop.dao.SQLUtil;
import lk.ijse.meatShop.dao.custom.OrderDAO;
import lk.ijse.meatShop.entity.Buyer_payment;
import lk.ijse.meatShop.entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {

        return null;
    }

    @Override
    public boolean add(Orders entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES(?, ?, ? ,?)";
        return SQLUtil.execute(sql, entity.getOrd_id(), entity.getDate(), entity.getCus_id(),entity.getEmp_id());
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        String BuyiD;
        ResultSet Set = SQLUtil.execute("SELECT ord_id FROM orders ORDER BY ord_id  DESC LIMIT 1");
        if (Set.next()) {
            String[] O00 = Set.getString(1).split("O00");
            int id = Integer.parseInt(O00[1]);
            id++;
            BuyiD = "O00" + id;


        } else {
            BuyiD = "O001";

        }
        return BuyiD;

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Orders search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
