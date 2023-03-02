package lk.ijse.meatShop.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dto.Buyer_paymentDTO;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.dto.StocksDTO;
import lk.ijse.meatShop.entity.Buyer_payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    ObservableList<String> getAllCode() throws SQLException, ClassNotFoundException;

    Buyer_paymentDTO decPayment(String code)throws SQLException, ClassNotFoundException;

    ArrayList<Buyer_paymentDTO> getAllPAy() throws SQLException, ClassNotFoundException;

    boolean updatePay(Buyer_paymentDTO dto) throws SQLException, ClassNotFoundException ;
}
