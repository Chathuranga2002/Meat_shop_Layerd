package lk.ijse.meatShop.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.meatShop.dao.CrudDAO;
import lk.ijse.meatShop.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
    ObservableList<String> getAllCusId() throws SQLException, ClassNotFoundException;

}
