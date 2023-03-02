package lk.ijse.meatShop.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.meatShop.dao.CrudDAO;
import lk.ijse.meatShop.entity.Buyer_payment;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Buyer_payment> {
    ObservableList<String> getPCode() throws SQLException, ClassNotFoundException;
}
