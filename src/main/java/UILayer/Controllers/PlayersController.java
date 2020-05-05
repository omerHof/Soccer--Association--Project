package UILayer.Controllers;

import ServiceLayer.UserManagement;
import UILayer.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayersController extends Controller {
    UserManagement userManagement = new UserManagement();

    @FXML
    TableView<String> players_table = new TableView<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });
        showPlayers();
    }

    private void showPlayers() {
        ArrayList<String> players_name = userManagement.getAllPlayers();

        TableColumn columnOne = new TableColumn("One");
        TableColumn columnTwo = new TableColumn("Two");

        players_table.getColumns().addAll(columnOne, columnTwo);
        for(String player_name: players_name){
            columnOne.setCellValueFactory(c -> new SimpleStringProperty(player_name));
        }



        players_table.getItems().addAll("Column one's data", "Column two's data");
    }


}
