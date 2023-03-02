package lk.ijse.meatShop.bo.custom.impl;

import lk.ijse.meatShop.bo.custom.ViewSupplierBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.custom.SupplierDAO;
import lk.ijse.meatShop.dto.EmployeeDTO;
import lk.ijse.meatShop.dto.SupplierDTO;
import lk.ijse.meatShop.entity.Employee;
import lk.ijse.meatShop.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewSupplierBOImpl implements ViewSupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIYER);
    @Override
    public ArrayList<SupplierDTO> getAllSup() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> list= new ArrayList<>();
        ArrayList<Supplier> all = supplierDAO.getAll();
        for (Supplier c : all) {
            list.add(new SupplierDTO(c.getSup_id(),c.getName(),c.getAddress(),c.getNic(),c.getTel_no()));
        }
        return list;
    }
}
