package lk.ijse.meatShop.bo.custom.impl;

import lk.ijse.meatShop.bo.custom.AddEmployeeBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.custom.EmployeeDAO;
import lk.ijse.meatShop.dto.EmployeeDTO;
import lk.ijse.meatShop.entity.Employee;

import java.sql.SQLException;

public class AddEmployeeBOImpl implements AddEmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(dto.getEmp_id(),dto.getUser_name(),dto.getPassword(),dto.getNic(),dto.getName(),
                dto.getAddress(),dto.getRool(),dto.getTel_no()));
    }

    @Override
    public String generateNewEmpID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewID();
    }
}
