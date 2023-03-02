package lk.ijse.meatShop.dao.custom;

import lk.ijse.meatShop.dao.CrudDAO;
import lk.ijse.meatShop.entity.Buy_detail;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BuyDeatilsDAO extends CrudDAO<Buy_detail> {
    boolean saveBuyDetail(ArrayList<Buy_detail> cartDetails) throws SQLException, ClassNotFoundException;
}
