package lk.ijse.meatShop.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.meatShop.bo.custom.PaymentBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.custom.PaymentDAO;
import lk.ijse.meatShop.dto.Buyer_paymentDTO;
import lk.ijse.meatShop.entity.Buyer_payment;
import lk.ijse.meatShop.entity.Feedback;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO pyamentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BUYPAYMENT);
    @Override
    public ObservableList<String> getAllCode() throws SQLException, ClassNotFoundException {
        return  pyamentDAO.getPCode();
    }

    @Override
    public Buyer_paymentDTO decPayment(String code) throws SQLException, ClassNotFoundException {
        Buyer_payment payment= pyamentDAO.search(code);
        return new Buyer_paymentDTO(payment.getBuy_id(),payment.getDate(),payment.getPrice(),payment.getPayed(),payment.getBalance());
    }

    @Override
    public ArrayList<Buyer_paymentDTO> getAllPAy() throws SQLException, ClassNotFoundException {
        ArrayList<Buyer_paymentDTO> allpay= new ArrayList<>();
        ArrayList<Buyer_payment> all = pyamentDAO.getAll();
        for (Buyer_payment c : all) {
            allpay.add(new Buyer_paymentDTO(c.getBuy_id() ,c.getDate(),c.getPrice(), c.getPayed(),c.getBalance()));
        }
        return allpay;
    }

    @Override
    public boolean updatePay(Buyer_paymentDTO dto) throws SQLException, ClassNotFoundException {
        Buyer_payment payment=new Buyer_payment();
        payment.setBuy_id(dto.getBuy_id());
        payment.setPayed(dto.getPrice());
        return pyamentDAO.update(payment);
    }
}
