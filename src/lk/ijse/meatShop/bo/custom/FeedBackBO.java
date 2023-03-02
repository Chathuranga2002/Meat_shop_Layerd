package lk.ijse.meatShop.bo.custom;

import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.dto.FeedbackDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FeedBackBO extends SuperBO {
    ArrayList<FeedbackDTO> getAllFeedback() throws SQLException, ClassNotFoundException;
}
