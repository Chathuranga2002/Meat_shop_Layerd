package lk.ijse.meatShop.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.meatShop.dao.SQLUtil;
import lk.ijse.meatShop.dao.custom.SupplierDAO;
import lk.ijse.meatShop.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
            ArrayList<Supplier> List = new ArrayList<>();
            ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");
            while (rst.next()) {
                Supplier supplier = new Supplier(rst.getString(1), rst.getString(2),
                        rst.getString(3), rst.getString(4),rst.getString(5));
                List.add(supplier);
            }
            return List;



    }

    @Override
    public boolean add(Supplier entity) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO supplier VALUES (?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, entity.getSup_id(), entity.getName(), entity.getAddress(),
                entity.getNic(),entity.getTel_no());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        String sql ="Update supplier set name=?, address=?, nic=? ,tel_no=? where sup_id=?";
        return SQLUtil.execute(sql,entity.getName(),entity.getAddress(),entity.getNic(),entity.getTel_no(),entity.getSup_id()
        );

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        String supid;
        ResultSet Set = SQLUtil.execute("SELECT sup_id FROM supplier ORDER BY sup_id  DESC LIMIT 1");
        if (Set.next()) {
            String[] S00 = Set.getString(1).split("S00");
            int id = Integer.parseInt(S00[1]);
            id++;
            supid = "S00" + id;


        } else {
            supid = "S001";

        }
        return supid;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM supplier WHERE sup_id = ?||nic = ? || tel_no=? ";
        ResultSet result = SQLUtil.execute(sql, id,id,id);

        if (result.next()) {
            return new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)

            );
        }
        return null;
    }
    @Override
    public ObservableList<String> geSupId() throws SQLException, ClassNotFoundException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rst = SQLUtil.execute("SELECT sup_id FROM supplier");
        while (rst.next()) {
            list.add(rst.getString(1));

        }

        return list;
    }
}
