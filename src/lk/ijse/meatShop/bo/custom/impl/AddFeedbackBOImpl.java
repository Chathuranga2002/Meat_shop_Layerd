package lk.ijse.meatShop.bo.custom.impl;

import lk.ijse.meatShop.bo.custom.AddFeedbackBO;
import lk.ijse.meatShop.dao.DAOFactory;
import lk.ijse.meatShop.dao.SQLUtil;
import lk.ijse.meatShop.dao.custom.FeedbackDAO;
import lk.ijse.meatShop.dto.FeedbackDTO;
import lk.ijse.meatShop.entity.Customer;
import lk.ijse.meatShop.entity.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddFeedbackBOImpl implements AddFeedbackBO {
    FeedbackDAO feedbackDAO = (FeedbackDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FEEDBACK);
    @Override
    public boolean addFeedback(FeedbackDTO dto) throws SQLException, ClassNotFoundException {
        return feedbackDAO.add(new Feedback(dto.getCus_id(),dto.getComment(),dto.getRete()));
    }

    @Override
    public boolean updateFeedback(FeedbackDTO dto) throws SQLException, ClassNotFoundException {
        return feedbackDAO.update(new Feedback(dto.getCus_id(),dto.getComment(),dto.getRete()));
    }

    @Override
    public FeedbackDTO serchFeedback(String id) throws SQLException, ClassNotFoundException {
         Feedback feedback=feedbackDAO.search(id);
        return new FeedbackDTO(feedback.getCus_id(),feedback.getComment(),feedback.getRete());


    }
}
