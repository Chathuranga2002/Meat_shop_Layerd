package lk.ijse.meatShop.dao.custom.impl;


import lk.ijse.meatShop.dao.SQLUtil;
import lk.ijse.meatShop.dao.custom.BuyDAO;
import lk.ijse.meatShop.entity.Buy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuyDAOImpl implements BuyDAO {

    @Override
    public ArrayList<Buy> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Buy entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO buy VALUES(?, ?, ?)";
        return SQLUtil.execute(sql, entity.getBuy_id(), entity.getDate(),entity.getSup_id());
    }

    @Override
    public boolean update(Buy entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        String BuyiD;
        ResultSet Set = SQLUtil.execute("SELECT buy_id FROM buy ORDER BY buy_id  DESC LIMIT 1");
        if (Set.next()) {
            String[] B00 = Set.getString(1).split("B00");
            int id = Integer.parseInt(B00[1]);
            id++;
            BuyiD = "B00" + id;


        } else {
            BuyiD = "B001";

        }
        return BuyiD;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Buy search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }





}
