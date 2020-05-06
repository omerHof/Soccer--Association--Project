package UILayer.Controllers;

import UILayer.Main;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class LandingController extends Controller {

    @FXML
    JFXButton notification;

    @FXML
    JFXButton profileButton;

    @FXML
    JFXButton logInBTN;

    @FXML
    JFXButton signUpBtn;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        showUserButton();
    }

    public void showUserButton(){
        if(super.userName!=null){
            logInBTN.setVisible(false);
            signUpBtn.setVisible(false);
            notification.setVisible(true);
            profileButton.setText(userName);
            profileButton.setVisible(true);
        }
    }
}
