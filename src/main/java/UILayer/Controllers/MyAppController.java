package UILayer.Controllers;

import ServiceLayer.LeagueSeasonManagement;
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

    User user= new AssociationRepresentative("","","","");

    @FXML
    private Button initNewSeasonButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        setButton();
    }

    public void setButton(){
        try {
            if (!(user instanceof AssociationRepresentative)) {
                initNewSeasonButton.setVisible(false);
            }
        }catch (Exception e){

        }
    }

}
