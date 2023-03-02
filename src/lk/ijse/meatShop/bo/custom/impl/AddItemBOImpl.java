package lk.ijse.meatShop.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.meatShop.bo.custom.AddItemBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.custom.CustomerDAO;
import lk.ijse.meatShop.dao.custom.ItemDAO;
import lk.ijse.meatShop.dao.custom.StoksDAO;
import lk.ijse.meatShop.db.DBConnection;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.dto.ItemDTO;
import lk.ijse.meatShop.dto.StocksDTO;
import lk.ijse.meatShop.entity.Customer;
import lk.ijse.meatShop.entity.Item;
import lk.ijse.meatShop.entity.Stocks;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddItemBOImpl implements AddItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    StoksDAO stoksDAO = (StoksDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOKS);

//    @Override
//    public boolean addItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
//        return itemDAO.add(new Item(dto.getItem_code(),dto.getCategory(),dto.getDescription(),dto.getUnit_Price(),dto.getQty_onHand()));
//    }
//
//    @Override
//    public boolean addStoks(StocksDTO dto) throws SQLException, ClassNotFoundException {
//        return stoksDAO.add(new Stocks(dto.getCode(),dto.getCategory(),dto.getDescription(),dto.getQty_onHand()));
//    }

    @Override
    public ArrayList<StocksDTO> getAllStoks() throws SQLException, ClassNotFoundException {
        ArrayList<StocksDTO> allStoks=  new ArrayList<>();
        ArrayList<Stocks> all = stoksDAO.getAll();
        for (Stocks c : all) {
            allStoks.add(new StocksDTO(c.getCode(),c.getCategory(),c.getDescription(), c.getQty_onHand()));
        }
        return allStoks;
    }

    @Override
    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return  stoksDAO.generateNewID();
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        boolean last = false;
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);

            boolean isStoksSaved = stoksDAO.add(new Stocks(dto.getItem_code(),dto.getCategory(),dto.getDescription(),dto.getQty_onHand()));

            if (isStoksSaved) {
                boolean isItemSaved = itemDAO.add(new Item(dto.getItem_code(),dto.getCategory(),dto.getDescription(),dto.getUnit_Price(),dto.getQty_onHand()));

                if (isItemSaved) {
                    DBConnection.getInstance().getConnection().commit();
                    last= true;
                } else {
                    DBConnection.getInstance().getConnection().rollback();
                }
            } else {
                DBConnection.getInstance().getConnection().rollback();
            }
        } catch (SQLException | ClassNotFoundException ignored) {

        } finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException ignored) { }

        }
       return last;
    }
}
