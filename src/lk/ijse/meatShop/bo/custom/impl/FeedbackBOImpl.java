package lk.ijse.meatShop.bo.custom.impl;

import lk.ijse.meatShop.bo.custom.FeedBackBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.custom.FeedbackDAO;
import lk.ijse.meatShop.dto.CustomerDTO;
import lk.ijse.meatShop.dto.FeedbackDTO;
import lk.ijse.meatShop.entity.Customer;
import lk.ijse.meatShop.entity.Feedback;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackBOImpl implements FeedBackBO {
    FeedbackDAO feedbackDAO = (FeedbackDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FEEDBACK);

    @Override
    public ArrayList<FeedbackDTO> getAllFeedback() throws SQLException, ClassNotFoundException {
        ArrayList<FeedbackDTO> allFeedback= new ArrayList<>();
        ArrayList<Feedback> all = feedbackDAO.getAll();
        for (Feedback c : all) {
            allFeedback.add(new FeedbackDTO(c.getCus_id(),c.getComment(),c.getRete()));
        }
        return allFeedback;
    }
}
