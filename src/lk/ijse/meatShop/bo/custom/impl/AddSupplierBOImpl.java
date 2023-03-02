package lk.ijse.meatShop.bo.custom.impl;

import lk.ijse.meatShop.bo.custom.AddSupplierBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.custom.CustomerDAO;
import lk.ijse.meatShop.dao.custom.SupplierDAO;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.dto.SupplierDTO;
import lk.ijse.meatShop.entity.Customer;
import lk.ijse.meatShop.entity.Supplier;

import java.sql.SQLException;

public class AddSupplierBOImpl implements AddSupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIYER);
    @Override
    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(new Supplier(dto.getSup_id(),dto.getName(),dto.getAddress(),dto.getNic(),dto.getTel_no()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getSup_id(),dto.getName(),dto.getAddress(),dto.getNic(),dto.getTel_no()));
    }

    @Override
    public String generateNewSupId() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewID();
    }

    @Override
    public SupplierDTO serchSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier= supplierDAO.search(id);
        return new SupplierDTO(supplier.getSup_id(),supplier.getName(),supplier.getAddress(),supplier.getNic(),supplier.getTel_no());
    }
}
