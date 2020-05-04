package UILayer.Controllers;

import Teams.Team;
import UILayer.Main;
import Users.TeamOwner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamsController extends Controller {



    @FXML
    private TextField choosenTeamName;
    @FXML
    private TextField choosenTeamBudget;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });
    }

    public void CreateTeam(){

    }
}
