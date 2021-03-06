package UILayer.Controllers;

import ServiceLayer.UserManagement;
import UILayer.Main;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LoginController extends Controller {

    private UserManagement userManagement;

    private String username;
    private String password;

    private String userType;

    @FXML
    TextField usernameTF;
    @FXML
    PasswordField passwordTF;

    @FXML
    JFXButton login;

    @FXML
    JFXButton backBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        userManagement = new UserManagement();
        backBTN.setFocusTraversable(false);
        login.setFocusTraversable(false);


    }

    public void verifyDetails() throws IOException {

        if(usernameTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "You must fill in your username.");
            return;
        }

        if(passwordTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "You must fill in your password.");
            return;
        }

        username = usernameTF.getText();
        password = passwordTF.getText();

        String loginResult = userManagement.logIn(username, password);

        if (loginResult.equals("name")){
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Username doesn't exist. Please try again.");
            return;
        }
        else if (loginResult.equals("password")){
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Your password is incorrect. Please try again.");
            return;
        }
        else if (loginResult.equals("null")){
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Other error occured. Katzi doesn't know. Please try again.");
            return;
        }
        else if (loginResult.equals("occupied")){
            showAlert(Alert.AlertType.ERROR, "Form Error!", "The system is occupied. Please try again later.");
            return;
        }
        else{
            showAlert(Alert.AlertType.INFORMATION, "Form Information", "Now you logged in. Enjoy our system :)"); //todo: checkkkkkk !!!!!!!!! ..//////////////////////////////////////////
            super.userName = username;
            userType = userManagement.getUserType(username);
            super.userType = userType;
            goToLanding();
        }

    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}


