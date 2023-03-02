package lk.ijse.meatShop.bo.custom.impl;

import lk.ijse.meatShop.bo.custom.ViewOrdersBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.custom.OrderDeatilsDAO;
import lk.ijse.meatShop.dto.Order_detailDTO;
import lk.ijse.meatShop.dto.SupplierDTO;
import lk.ijse.meatShop.entity.Order_detail;
import lk.ijse.meatShop.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewOrdersBOImpl implements ViewOrdersBO {
    OrderDeatilsDAO dao = (OrderDeatilsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public ArrayList<Order_detailDTO> getAllOrd(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Order_detailDTO> list= new ArrayList<>();
        ArrayList<Order_detail> all = dao.getAllorders(id);
        for (Order_detail c : all) {
            list.add(new Order_detailDTO(c.getOrd_id(),c.getItem_code(),c.getQty(),c.getUnitPrice()));
        }
        return list;
    }
}
