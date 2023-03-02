package lk.ijse.meatShop.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.meatShop.dao.SQLUtil;
import lk.ijse.meatShop.dao.custom.StoksDAO;
import lk.ijse.meatShop.entity.Buy_detail;
import lk.ijse.meatShop.entity.Stocks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StoksDAOImpl implements StoksDAO {
    @Override
    public ArrayList<Stocks> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<Stocks> allstocks = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM stocks");
        while (rst.next()) {
            Stocks stocks = new Stocks(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4));
            allstocks.add(stocks);
        }
        return allstocks;
    }

    @Override
    public boolean add(Stocks entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO stocks VALUES (?, ?, ?, ?)";
        return SQLUtil.execute(sql, entity.getCode(), entity.getCategory(), entity.getDescription()
                ,entity.getQty_onHand());
    }

    @Override
    public boolean update(Stocks entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE stocks SET qty_onHand = qty_onHand - ? WHERE code = ?";
        return SQLUtil.execute(sql,entity.getQty_onHand(),entity.getCode());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        String itmecode;
        ResultSet Set = SQLUtil.execute("SELECT code FROM stocks ORDER BY code  DESC LIMIT 1");
        if (Set.next()) {
            String[] I00 = Set.getString(1).split("I00");
            int id = Integer.parseInt(I00[1]);
            id++;
            itmecode = "I00" + id;


        } else {
            itmecode = "I001";

        }
        return itmecode;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Stocks search(String id) throws SQLException, ClassNotFoundException {
        String sql =("SELECT * FROM stocks where code=? ");
        ResultSet result = SQLUtil.execute(sql,id);

        if (result.next()) {
            return new Stocks(

                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4)
            );
        }
        return null;
    }

    @Override
    public ObservableList<String> getStoksCode() throws SQLException, ClassNotFoundException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rst = SQLUtil.execute("SELECT code FROM stocks ");
        while (rst.next()) {
            list.add(rst.getString(1));

        }

        return list;
    }

    @Override
    public boolean updateBuy(Stocks entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE stocks SET qty_onHand = qty_onHand + ? WHERE code = ?";
        return SQLUtil.execute(sql, entity.getQty_onHand(), entity.getCode());
    }

    @Override
    public boolean UpdateQty(ArrayList<Buy_detail> stocks) throws SQLException, ClassNotFoundException {
        for (Buy_detail cartDetail : stocks) {
            Stocks stocks1=new Stocks() ;
            stocks1.setCode(cartDetail.getCode());
            stocks1.setQty_onHand(cartDetail.getQty());
            if (!updateBuy(stocks1)) {
                return false;
            }

        }
        return true;
    }
}
