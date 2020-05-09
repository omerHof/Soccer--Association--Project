package UILayer.Controllers;

import ServiceLayer.LeagueSeasonManagement;
import ServiceLayer.UserManagement;
import UILayer.Main;
import Users.AssociationRepresentative;
import Users.Fan;
import Users.User;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class MyAppController extends Controller {


    @FXML
    private Button createNewTeamButton;
    @FXML
    private Button initNewSeasonButton;
    @FXML
    private Button openTeamPage;
    @FXML
    private Button openPlayerPage;
    @FXML
    private Button openCoachPage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        UserManagement userManagement = new UserManagement();
        //userManagement.getUserType(userName);
        setButton();

    }

    public void setButton() {

        switch (userType) {
            case "Fan":

                break;
            case "Player":
                openPlayerPage.setVisible(true);

                break;
            case "Coach":
                openCoachPage.setVisible(true);

                break;
            case "AssociationRepresentative":
                initNewSeasonButton.setVisible(true);
                break;
            case "TeamOwner":
                createNewTeamButton.setVisible(true);
                openTeamPage.setVisible(true);
                break;
            case "Manager":
                openTeamPage.setVisible(true);
                break;
            case "Referee":

                break;
            case "MainReferee":

                break;
        }
    }

}
