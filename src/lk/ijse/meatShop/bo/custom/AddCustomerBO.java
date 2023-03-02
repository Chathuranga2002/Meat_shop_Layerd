package lk.ijse.meatShop.bo.custom;

import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddCustomerBO extends SuperBO {
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    String generateNewCustomerID() throws SQLException, ClassNotFoundException;

    CustomerDTO serchCustomer(String id)throws SQLException, ClassNotFoundException;
}
