package lk.ijse.meatShop.bo.custom;

import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dto.Order_detailDTO;
import lk.ijse.meatShop.dto.SupplierDTO;
import lk.ijse.meatShop.entity.Order_detail;

import java.sql.SQLException;
import java.util.ArrayList;


public interface ViewOrdersBO extends SuperBO {
    ArrayList<Order_detailDTO> getAllOrd(String id) throws SQLException, ClassNotFoundException;
}
