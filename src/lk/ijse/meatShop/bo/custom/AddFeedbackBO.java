package lk.ijse.meatShop.bo.custom;

import lk.ijse.meatShop.bo.SuperBO;
import lk.ijse.meatShop.dto.FeedbackDTO;

import java.sql.SQLException;

public interface AddFeedbackBO extends SuperBO {
    boolean addFeedback(FeedbackDTO dto) throws SQLException, ClassNotFoundException ;

    boolean updateFeedback(FeedbackDTO dto) throws SQLException, ClassNotFoundException ;

    FeedbackDTO serchFeedback(String id)throws SQLException, ClassNotFoundException;
}
