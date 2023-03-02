package lk.ijse.meatShop.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.meatShop.dao.SQLUtil;
import lk.ijse.meatShop.dao.custom.ItemDAO;
import lk.ijse.meatShop.entity.Item;
import lk.ijse.meatShop.entity.Order_detail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<Item> all = new  ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM item");
        while (rst.next()) {
            Item item = new Item(rst.getString(1), rst.getString(2), rst.getString(3),rst.getDouble(4), rst.getInt(4));
            all.add(item);
        }
        return all;
    }

    @Override
    public boolean add(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO item VALUES (?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, entity.getItem_code(), entity.getCategory(), entity.getDescription(),
                entity.getUnit_Price(),entity.getQty_onHand());

    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET qty_onHand = qty_onHand + ?,unit_Price =?  WHERE item_code = ?";
        return SQLUtil.execute(sql,entity.getQty_onHand(),entity.getUnit_Price(),entity.getItem_code());
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
    public Item search(String id) throws SQLException, ClassNotFoundException {
        String sql =("SELECT * FROM item where item_code=? ");
        ResultSet result = SQLUtil.execute(sql,id);

        if (result.next()) {
            return new Item(

                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4),
                    result.getInt(5)
            );
        }
        return null;
    }

    @Override
    public boolean UpdateQty(ArrayList<Order_detail> stocks) throws SQLException, ClassNotFoundException {
        for (Order_detail cartDetail : stocks) {
            Item item=new Item() ;
            item.setItem_code(cartDetail.getItem_code());
            item.setQty_onHand(cartDetail.getQty());
            if (!updateBuy(item)) {
                return false;
            }

        }
        return true;
    }

    @Override
    public boolean updateBuy(Item entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET qty_onHand = qty_onHand - ? WHERE item_code = ?";
        return SQLUtil.execute(sql, entity.getQty_onHand(), entity.getItem_code());
    }

    @Override
    public ObservableList<String> getAllItemCode() throws SQLException, ClassNotFoundException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rst = SQLUtil.execute("SELECT item_code FROM item ");
        while (rst.next()) {
            list.add(rst.getString(1));

        }

        return list;
    }
}
