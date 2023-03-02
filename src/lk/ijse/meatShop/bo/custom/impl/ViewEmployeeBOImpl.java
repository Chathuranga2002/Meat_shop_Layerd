package lk.ijse.meatShop.bo.custom.impl;

import lk.ijse.meatShop.bo.custom.ViewEmployeeBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.custom.EmployeeDAO;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.dto.EmployeeDTO;
import lk.ijse.meatShop.entity.Customer;
import lk.ijse.meatShop.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewEmployeeBOImpl implements ViewEmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDTO> getAllEmp() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> list= new ArrayList<>();
        ArrayList<Employee> all = employeeDAO.getAll();
        for (Employee c : all) {
            list.add(new EmployeeDTO(c.getEmp_id(),c.getUser_name(),c.getNic(),c.getName(),c.getAddress(),c.getRool(),c.getTel_no()));
        }
        return list;
    }
}
