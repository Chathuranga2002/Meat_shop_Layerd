package lk.ijse.meatShop.bo.custom;

import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;


public interface ViewEmployeeBO extends SuperBO {
    ArrayList<EmployeeDTO> getAllEmp() throws SQLException, ClassNotFoundException;

}
