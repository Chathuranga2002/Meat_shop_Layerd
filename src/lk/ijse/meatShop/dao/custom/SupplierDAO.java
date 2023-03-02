package lk.ijse.meatShop.dao.custom;

import javafx.collections.ObservableList;
import lk.ijse.meatShop.dao.CrudDAO;
import lk.ijse.meatShop.entity.Supplier;

import java.sql.SQLException;

public interface SupplierDAO  extends CrudDAO<Supplier> {
    ObservableList<String> geSupId() throws SQLException, ClassNotFoundException;
}
