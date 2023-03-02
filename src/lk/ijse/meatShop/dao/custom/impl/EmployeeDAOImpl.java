package lk.ijse.meatShop.dao.custom.impl;

import lk.ijse.meatShop.dao.SQLUtil;
import lk.ijse.meatShop.dao.custom.EmployeeDAO;
import lk.ijse.meatShop.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> list = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        while (rst.next()) {
            Employee customer = new Employee(rst.getString(1), rst.getString(2),
                    rst.getString(3), rst.getString(4),rst.getString(5),rst.getString(6),
                    rst.getString(7),rst.getString(8));
            list.add(customer);
        }
        return list;
    }

    @Override
    public boolean add(Employee entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql,entity.getEmp_id(),entity.getUser_name(),entity.getPassword(),
                entity.getNic(),entity.getName(),entity.getAddress()
                ,entity.getRool(),entity.getTel_no());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        String sql =" Update employee set user_name=?, password=?, nic=?, name=?, address=? ,rool=? ,tel_no=? where emp_id=?";
        return SQLUtil.execute(sql,entity.getUser_name(),entity.getPassword(),entity.getNic(),entity.getName(),
                entity.getAddress(),entity.getRool(),entity.getTel_no(),entity.getEmp_id());

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        String empid;
        ResultSet Set = SQLUtil.execute("SELECT emp_id FROM employee ORDER BY emp_id  DESC LIMIT 1");
        if (Set.next()) {
            String[] E00 = Set.getString(1).split("E00");
            int id = Integer.parseInt(E00[1]);
            id++;
            empid="E00" + id;


        } else {
            empid="E001";

        }
        return empid;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM employee WHERE emp_id = ?||nic = ?";
        ResultSet result = SQLUtil.execute(sql, id,id);

        if (result.next()) {
            return new Employee(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(6),
                    result.getString(5),
                    result.getString(7),
                    result.getString(8)
            );
        }
        return null;
    }

}
