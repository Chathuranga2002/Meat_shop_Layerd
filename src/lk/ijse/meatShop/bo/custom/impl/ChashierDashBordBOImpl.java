package lk.ijse.meatShop.bo.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.meatShop.bo.custom.ChashierDashBordBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.custom.*;
import lk.ijse.meatShop.db.DBConnection;
import lk.ijse.meatShop.dto.*;
import lk.ijse.meatShop.entity.*;

import java.sql.SQLException;

public class ChashierDashBordBOImpl implements ChashierDashBordBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDeatilsDAO orderDeatilsDAO = (OrderDeatilsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean OrderConfrom(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        boolean last=false;
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean issaveOrder = orderDAO.add(new Orders(dto.getOrd_id(), dto.getDate(), dto.getCus_id(),dto.getEmp_id()));
            if (issaveOrder) {
                boolean isaddOrderDeatils = orderDeatilsDAO.saveOrderDetail(dto.getCartDeatils());
                if (isaddOrderDeatils){
                    boolean isUpdateqty = itemDAO.UpdateQty(dto.getCartDeatils());
                    if (isUpdateqty) {
                        DBConnection.getInstance().getConnection().commit();
                       last=true;
                    } else {
                        DBConnection.getInstance().getConnection().rollback();
                    }
                }else {
                    DBConnection.getInstance().getConnection().rollback();

                }
            } else {
                DBConnection.getInstance().getConnection().rollback();
            }
        } catch (SQLException | ClassNotFoundException ignored) {

        } finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException | ClassNotFoundException ignored) {
            }
        }

        return last;
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public ObservableList<String> getAllitemcode() throws SQLException, ClassNotFoundException {
        return  itemDAO.getAllItemCode();
    }

    @Override
    public ObservableList<String> getAllcusid() throws SQLException, ClassNotFoundException {
        return  customerDAO.getAllCusId();
    }

    @Override
    public ItemDTO decItem(String code) throws SQLException, ClassNotFoundException {
        Item item= itemDAO.search(code);
        return new ItemDTO(item.getItem_code(),item.getCategory(),item.getDescription(),item.getUnit_Price(),item.getQty_onHand());
    }

    @Override
    public CustomerDTO serchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer= customerDAO.search(id);
        return new CustomerDTO(customer.getCus_id(),customer.getName(),customer.getAddress(),customer.getTel_no());

    }
}
