package lk.ijse.meatShop.bo.custom;

import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dto.ItemDTO;
import lk.ijse.meatShop.dto.StocksDTO;


import java.sql.SQLException;
import java.util.ArrayList;

public interface AddItemBO extends SuperBO {
//     boolean addItem(ItemDTO dto) throws SQLException, ClassNotFoundException ;
//
//     boolean addStoks(StocksDTO dto) throws SQLException, ClassNotFoundException ;

    ArrayList<StocksDTO> getAllStoks() throws SQLException, ClassNotFoundException;

    String generateNewCustomerID() throws SQLException, ClassNotFoundException;

    boolean saveItem(ItemDTO dto)throws SQLException, ClassNotFoundException;
}
