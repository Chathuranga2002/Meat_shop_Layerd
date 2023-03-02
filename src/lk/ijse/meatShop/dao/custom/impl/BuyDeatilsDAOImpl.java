package lk.ijse.meatShop.dao.custom.impl;

import lk.ijse.meatShop.dao.SQLUtil;
import lk.ijse.meatShop.dao.custom.BuyDeatilsDAO;
import lk.ijse.meatShop.entity.Buy_detail;

import java.sql.SQLException;
import java.util.ArrayList;

public class BuyDeatilsDAOImpl implements BuyDeatilsDAO {
    @Override
    public ArrayList<Buy_detail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Buy_detail entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO buy_detail VALUES(?, ?, ?, ?)";
        return SQLUtil.execute(sql, entity.getBuy_id(), entity.getCode(), entity.getQty(),
                entity.getUnitPrice());
    }

    @Override
    public boolean update(Buy_detail entity) throws SQLException, ClassNotFoundException {
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
    public Buy_detail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveBuyDetail(ArrayList<Buy_detail> cartDetails) throws SQLException, ClassNotFoundException {
        for (Buy_detail cartDetail : cartDetails) {
            if (!add(cartDetail)) {
                return false;
            }
        }
        return true;
    }
}
