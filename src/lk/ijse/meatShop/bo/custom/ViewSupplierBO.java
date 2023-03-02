package lk.ijse.meatShop.bo.custom;

import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dao.SuperDAO;
import lk.ijse.meatShop.dto.EmployeeDTO;
import lk.ijse.meatShop.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ViewSupplierBO extends SuperBO {
    ArrayList<SupplierDTO> getAllSup() throws SQLException, ClassNotFoundException;

}
