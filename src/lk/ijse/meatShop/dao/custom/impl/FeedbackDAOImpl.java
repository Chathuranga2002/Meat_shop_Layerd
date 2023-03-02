package lk.ijse.meatShop.dao.custom.impl;


import lk.ijse.meatShop.dao.SQLUtil;
import lk.ijse.meatShop.dao.custom.FeedbackDAO;
import lk.ijse.meatShop.entity.Customer;
import lk.ijse.meatShop.entity.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackDAOImpl implements FeedbackDAO {

    @Override
    public ArrayList<Feedback> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Feedback> list = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM feedback");
        while (rst.next()) {
            Feedback feedback = new Feedback(rst.getString(1), rst.getString(2), rst.getInt(3));
            list.add(feedback);
        }
        return list;
    }

    @Override
    public boolean add(Feedback entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO feedback VALUES (?, ?, ?)";
        return SQLUtil.execute(sql, entity.getCus_id(), entity.getComment(), entity.getRete());
    }

    @Override
    public boolean update(Feedback entity) throws SQLException, ClassNotFoundException {
        String sql ="Update feedback set  comment=?, rete=? where cus_id=?";
        return SQLUtil.execute(sql, entity.getComment(), entity.getRete(), entity.getCus_id());
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
    public Feedback search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM feedback WHERE cus_id = ?";
        ResultSet result = SQLUtil.execute(sql, id);


            if (result.next()) {
                return new Feedback(
                        result.getString(1),
                        result.getString(2),
                        result.getInt(3)


                );
            }


        return null;
    }
}
