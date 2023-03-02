package lk.ijse.meatShop.bo.custom.impl;

import lk.ijse.meatShop.bo.custom.AddCustomerBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.custom.CustomerDAO;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.entity.Customer;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddCustomerBOImpl implements AddCustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);


    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(dto.getCus_id(),dto.getName(),dto.getAddress(),dto.getTel_no()));

    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        ArrayList<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getCus_id(),c.getName(),c.getAddress(), c.getTel_no()));
        }
        return allCustomers;
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCus_id(),dto.getName(),dto.getAddress(),dto.getTel_no()));
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    @Override
    public CustomerDTO serchCustomer(String id) throws SQLException, ClassNotFoundException {
       Customer customer= customerDAO.search(id);
        return new CustomerDTO(customer.getCus_id(),customer.getName(),customer.getAddress(),customer.getTel_no());
    }


}
