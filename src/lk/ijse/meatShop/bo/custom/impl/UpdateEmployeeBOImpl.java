package lk.ijse.meatShop.bo.custom.impl;

import lk.ijse.meatShop.bo.custom.UpdaetEmployeeBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.custom.EmployeeDAO;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.dto.EmployeeDTO;
import lk.ijse.meatShop.entity.Customer;
import lk.ijse.meatShop.entity.Employee;

import java.sql.SQLException;

public class UpdateEmployeeBOImpl implements UpdaetEmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean updateEmp(EmployeeDTO dto)  throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmp_id(),dto.getUser_name(),dto.getPassword(),dto.getNic(),dto.getName(),
                dto.getAddress(),dto.getRool(),dto.getTel_no()));

    }

    @Override
    public EmployeeDTO serchEmp(String id) throws SQLException, ClassNotFoundException {
        Employee employee= employeeDAO.search(id);
        return new EmployeeDTO(employee.getEmp_id(),employee.getUser_name(),employee.getNic(),employee.getName(),employee.getAddress(),employee.getRool(),
                employee.getTel_no());
    }
}
