package lk.ijse.meatShop.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.meatShop.dao.SQLUtil;
import lk.ijse.meatShop.dao.custom.CustomerDAO;
import lk.ijse.meatShop.entity.Customer;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CstomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        while (rst.next()) {
            Customer customer = new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
            allCustomers.add(customer);
        }
        return allCustomers;

    }

    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO customer VALUES (?, ?, ?, ?)";
        return SQLUtil.execute(sql, entity.getCus_id(), entity.getName(), entity.getAddress(),
                entity.getTel_no());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {

        String sql ="Update customer set  name=?, address=?, tel_no=? where cus_id=?";
        return SQLUtil.execute(sql,entity.getName(),entity.getAddress(),entity.getTel_no(),entity.getCus_id()
        );

    }



    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        String cusiD;
        ResultSet Set = SQLUtil.execute("SELECT cus_id FROM customer ORDER BY cus_id  DESC LIMIT 1");
        if (Set.next()) {
            String[] C00 = Set.getString(1).split("C00");
            int id = Integer.parseInt(C00[1]);
            id++;
            cusiD = "C00" + id;


        } else {
            cusiD = "C001";

        }
        return cusiD;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        String sql=("SELECT * FROM customer where cus_id=? || tel_no=? ");
        ResultSet result = SQLUtil.execute(sql, id,id);

        if (result.next()) {
            return new Customer(

                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)

            );
        }
        return null;
    }

    @Override
    public ObservableList<String> getAllCusId() throws SQLException, ClassNotFoundException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rst = SQLUtil.execute("SELECT cus_id FROM customer ");
        while (rst.next()) {
            list.add(rst.getString(1));

        }

        return list;
    }
}