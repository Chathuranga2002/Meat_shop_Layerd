package lk.ijse.meatShop.bo.custom;

import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddSupplierBO extends SuperBO {
    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException ;

    boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException ;

    String generateNewSupId() throws SQLException, ClassNotFoundException;

    SupplierDTO serchSupplier(String id)throws SQLException, ClassNotFoundException;
}
