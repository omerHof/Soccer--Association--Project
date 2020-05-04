package UILayer.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController extends Controller {

    String userFullName;
    String courtRole;
    String teamRole;
    String qualification;

    @FXML
    JFXTextField userNameDetail;

    @FXML
    JFXTextField password;

    @FXML
    JFXTextField fullName;

    @FXML
    JFXTextField email;

    @FXML
    JFXTextArea fullNameEditor;

    @FXML
    JFXTextArea emailEditor;

    @FXML
    JFXButton submitFullName;

    @FXML
    JFXButton submitEmail;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //find the user type
        switch (userType) {
            case "Fan":

                break;
            case "Player":

                break;
            case "Coach":

                break;
            case "AssociationRepresentative":

                break;
            case "TeamOwner":

                break;
            case "Manager":

                break;
            case "Referee":

                break;
            case "MainReferee":

                break;
        }

        //display his correct details
        userNameDetail.appendText("omer123");
        password.appendText("wooo123");
        fullName.appendText("omer hofman");
        email.appendText("omer@gmail.com");



        //create edit button for each detail beside password and username
        //open text field after press edit button
        //validate new details
        //submit to db


    }

    public void showEmailEditor(ActionEvent actionEvent) {
        emailEditor.setVisible(true);
        submitEmail.setVisible(true);

    }

    public void showFullNameEditor(ActionEvent actionEvent) {
        fullNameEditor.setVisible(true);
        submitFullName.setVisible(true);
    }

    public void submitFullName(ActionEvent actionEvent) {
    }

    public void submitEmail(ActionEvent actionEvent) {
    }
}
