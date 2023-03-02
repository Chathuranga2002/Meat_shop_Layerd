package lk.ijse.meatShop.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.meatShop.dao.SQLUtil;
import lk.ijse.meatShop.dao.custom.PaymentDAO;
import lk.ijse.meatShop.entity.Buyer_payment;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Buyer_payment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Buyer_payment> payments = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("select * from buyer_payment where balance !=0");
        while (rst.next()) {
            Buyer_payment buyer_payment = new Buyer_payment(rst.getString(1), rst.getString(2), rst.getDouble(3),
                    rst.getDouble(4),rst.getDouble(5));
            payments.add(buyer_payment);
        }
        return payments;
    }

    @Override
    public boolean add(Buyer_payment entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO buyer_payment VALUES(?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, entity.getBuy_id(), entity.getDate(), entity.getPrice(),entity.getPayed(),
                entity.getBalance());
    }

    @Override
    public boolean update(Buyer_payment entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE buyer_payment SET payed = payed + ?,balance=balance-? WHERE buy_id = ?";
        return SQLUtil.execute(sql,entity.getPayed(),entity.getPayed(),entity.getBuy_id());
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
    public Buyer_payment search(String id) throws SQLException, ClassNotFoundException {
        String sql =("SELECT * FROM buyer_payment where buy_id=? ");
        ResultSet result = SQLUtil.execute(sql,id);

        if (result.next()) {
            return new Buyer_payment(

                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getDouble(4),
                    result.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public ObservableList<String> getPCode() throws SQLException, ClassNotFoundException {

        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rst = SQLUtil.execute("select buy_id from buyer_payment where balance !=0 ");
        while (rst.next()) {
            list.add(rst.getString(1));

        }

        return list;
    }
}
