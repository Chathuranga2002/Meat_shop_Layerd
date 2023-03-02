package lk.ijse.meatShop.bo.custom;

import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.dto.EmployeeDTO;

import java.sql.SQLException;

public interface UpdaetEmployeeBO extends SuperBO {
    boolean updateEmp(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;
    EmployeeDTO serchEmp(String id)throws SQLException, ClassNotFoundException;
}
