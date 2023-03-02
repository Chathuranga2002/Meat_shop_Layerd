package lk.ijse.meatShop.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import lk.ijse.meatShop.bo.BOFactory;
import lk.ijse.meatShop.bo.custom.AddFeedbackBO;
import lk.ijse.meatShop.dto.FeedbackDTO;



import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddFeedbackFormController implements Initializable {

    AddFeedbackBO addEmployeeBO = (AddFeedbackBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADDFEEDBACK);

    @FXML
    private AnchorPane pane1;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXComboBox<String> combRate;

    @FXML
    private TextArea txtComment;
    String []rate={"1","2","3","4","5"};



    @FXML
    void AddOnaction(ActionEvent event) throws SQLException, ClassNotFoundException {
        FeedbackDTO feedbackDTO =new FeedbackDTO(txtCustomerId.getText(),txtComment.getText(),
                Integer.parseInt(combRate.getValue()));

        if (addEmployeeBO.addFeedback(feedbackDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Added Successful...!").show();
            cler();

        } else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }


    }

    private void cler() {
        txtComment.setText("");
        combRate.setPromptText("");
        txtCustomerId.setText("");

    }

    public void SerchOnAction(ActionEvent actionEvent) {
        try {
            FeedbackDTO feedback = addEmployeeBO.serchFeedback(txtCustomerId.getText());
            if (feedback!= null) {
                fillData(feedback);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateOnaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int rate=Integer.parseInt(combRate.getValue());
        FeedbackDTO feedback=new FeedbackDTO(txtCustomerId.getText(),txtComment.getText(),rate) ;
        boolean isUpdate = addEmployeeBO.updateFeedback(feedback);

        if (isUpdate) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successful...!").show();
            cler();

        } else {
            new Alert(Alert.AlertType.WARNING, "Something went Wrong...!").show();
        }
    }

    private void fillData(FeedbackDTO feedback) {

        txtComment.setText(feedback.getComment());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combRate.getItems().addAll(rate);

    }
}
