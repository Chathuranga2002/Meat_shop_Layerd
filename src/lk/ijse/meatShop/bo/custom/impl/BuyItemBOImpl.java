package lk.ijse.meatShop.bo.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.meatShop.bo.custom.BuyItemBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.custom.*;
import lk.ijse.meatShop.db.DBConnection;
import lk.ijse.meatShop.dto.BuyDTO;
import lk.ijse.meatShop.dto.StocksDTO;
import lk.ijse.meatShop.dto.SupplierDTO;
import lk.ijse.meatShop.entity.Buy;
import lk.ijse.meatShop.entity.Buyer_payment;
import lk.ijse.meatShop.entity.Stocks;
import lk.ijse.meatShop.entity.Supplier;

import java.sql.SQLException;

public class BuyItemBOImpl implements BuyItemBO {
    BuyDAO buyDAO = (BuyDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BUY);
    BuyDeatilsDAO buyDeatilsDAO = (BuyDeatilsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BUYDEATILS);
    StoksDAO stoksDAO = (StoksDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOKS);
    PaymentDAO pyamentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BUYPAYMENT);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIYER);

    @Override
    public boolean buyConfrom(BuyDTO dto) throws SQLException, ClassNotFoundException {

       boolean ret=false;
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isBuysave = buyDAO.add(new Buy(dto.getBuy_id(),dto.getDate(),dto.getSup_id()));
            if (isBuysave) {
                boolean isAddBuyDetails = buyDeatilsDAO.saveBuyDetail(dto.getBuyDeatils());
                if (isAddBuyDetails){
                    boolean isUpdateQty = stoksDAO.UpdateQty(dto.getBuyDeatils());
                    if (isUpdateQty){
                        boolean isSavePayment = pyamentDAO.add(new Buyer_payment(dto.getBuy_id(),dto.getDate(),dto.getTot(),dto.getPayed(),dto.getBalance()));
                        if (isSavePayment) {
                             DBConnection.getInstance().getConnection().commit();
                            ret =true;
                        }else {
                            DBConnection.getInstance().getConnection().rollback();
                        }
                    }else {
                        DBConnection.getInstance().getConnection().rollback();
                    }
                } else {
                    DBConnection.getInstance().getConnection().rollback();
                }
            } else {
                DBConnection.getInstance().getConnection().rollback();

            }
        } catch (SQLException | ClassNotFoundException ignored) {
            System.out.println(ignored);

        } finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (SQLException | ClassNotFoundException ignored) {
            }
        }
        return ret;
    }

    @Override
    public String generateNewBuyID() throws SQLException, ClassNotFoundException {
        return buyDAO.generateNewID();
    }

    @Override
    public ObservableList<String> getAllitemcode() throws SQLException, ClassNotFoundException {
        return  stoksDAO.getStoksCode();
    }

    @Override
    public ObservableList<String> getAllsupid() throws SQLException, ClassNotFoundException {
        return  supplierDAO.geSupId();
    }

    @Override
    public StocksDTO decStoks(String code) throws SQLException, ClassNotFoundException {
        Stocks stocks= stoksDAO.search(code);
        return new StocksDTO(stocks.getCode(),stocks.getCategory(),stocks.getDescription(),stocks.getQty_onHand());
    }

    @Override
    public SupplierDTO serchSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier= supplierDAO.search(id);
        return new SupplierDTO(supplier.getSup_id(),supplier.getName(),supplier.getAddress(),supplier.getNic(),supplier.getTel_no());
    }
}
