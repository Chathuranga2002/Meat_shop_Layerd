package lk.ijse.meatShop.bo.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.meatShop.bo.custom.AddShowcaseBO;
import lk.ijse.meatShop.dao.DAOFactory;
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

public class AddShowcaseBOImpl implements AddShowcaseBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    StoksDAO stoksDAO = (StoksDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOKS);
    @Override
    public boolean addShowCase(int qty, String id, double unitePrice) throws SQLException, ClassNotFoundException {
        Stocks stocks =new Stocks();
        Item item=new Item();
        stocks.setCode(id);
        stocks.setQty_onHand(qty);
        item.setItem_code(id);
        item.setUnit_Price(unitePrice);
        item.setQty_onHand(qty);

        boolean ret=false;
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isStokUpdate = stoksDAO.update(stocks);
            if (isStokUpdate) {
                boolean isItemupdate =itemDAO.update(item);
                if (isItemupdate) {
                    DBConnection.getInstance().getConnection().commit();
                    ret= true;
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
            } catch (SQLException | ClassNotFoundException ignored) {
            }
        }
        return ret;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItem=  new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item c : all) {
            allItem.add(new ItemDTO(c.getItem_code(),c.getCategory(),c.getDescription(),c.getUnit_Price(), c.getQty_onHand()));
        }
        return allItem;
    }

    @Override
    public ArrayList<StocksDTO> getAllStocs() throws SQLException, ClassNotFoundException {
        ArrayList<StocksDTO> allStok=  new ArrayList<>();
        ArrayList<Stocks> all = stoksDAO.getAll();
        for (Stocks c : all) {
            allStok.add(new StocksDTO(c.getCode(),c.getCategory(),c.getDescription(), c.getQty_onHand()));
        }
        return allStok;
    }

    @Override
    public ObservableList<String> getAllCode() throws SQLException, ClassNotFoundException {
        return  stoksDAO.getStoksCode();
    }

    @Override
    public StocksDTO decStoks(String code) throws SQLException, ClassNotFoundException {
        Stocks stocks= stoksDAO.search(code);
        return new StocksDTO(stocks.getCode(),stocks.getCategory(),stocks.getDescription(),stocks.getQty_onHand());
    }
}
