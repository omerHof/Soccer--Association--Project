package UILayer.Controllers;

import ServiceLayer.TeamManagement;
import Teams.Team;
import UILayer.Main;
import Users.TeamOwner;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class TeamsController extends Controller {

    TeamManagement teamManagement = new TeamManagement();
    String teamName;
    String budget;
    boolean openTeam=false;


    @FXML
    private TextField choosenTeamName;
    @FXML
    private TextField  choosenTeamBudget;
    @FXML
    private TextField choosenNation;
    @FXML
    private TextField choosenHistory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });
    }



    public boolean validationBudget(String budget){

        String regex = "[0-9]+";
        if(!budget.matches(regex)){
            return false;
        }
        Double budgetDuoble = Double.parseDouble(budget);
        if(budgetDuoble==0||budgetDuoble==null){
            return false;
        }

        return true;
    }

        public boolean validationName(String name) {
        if (name == null || name.length() == 0) {
            return false;
        }
        if (name.matches(".*\\d.*")) {
            return false;
        }
        if (!name.matches("[a-z A-Z]+")) {
            return false;
        }
        return true;

    }
    public boolean validationNation(String name) {
        if (name == null || name.length() == 0) {
            return false;
        }
        if (name.matches(".*\\d.*")) {
            return false;
        }
        if (!name.matches("[a-z A-Z]+")) {
            return false;
        }
        return true;

    }
    public boolean validationHistory(String name) {
        if (name == null || name.length() == 0) {
            return false;
        }

        return true;

    }

            public void submitOpenTeam(){
                if(validationBudget(choosenTeamBudget.getText())==false){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(" budget not valid ");
                    alert.show();

                }
                if(validationName(choosenTeamName.getText())==false){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(" name not valid ");
                    alert.show();
                }
                ///need to add check if the name of the team exist!!!

                //create team
                openTeam =true;
                teamName = choosenTeamName.getText();
                String message = teamManagement.openTeam(choosenTeamName.getText(),Double.parseDouble(choosenTeamBudget.getText()));
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(message);
                alert.show();

            }


            public void createTeamPage(){
        if(validationHistory(choosenHistory.getText())==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(" history not valid ");
            alert.show();
        }
        if(validationNation(choosenNation.getText())==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(" nation not valid ");
            alert.show();
        }
        if(openTeam==true){
            String message =  teamManagement.openTeamPage(choosenHistory.getText(),choosenNation.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(message);
            alert.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The owner has no active team");
            alert.show();
        }

    }


}
