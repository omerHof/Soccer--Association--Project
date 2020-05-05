package UILayer.Controllers;

import ServiceLayer.SystemManagement;
import UILayer.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.rowset.internal.Row;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

public class HomePageController extends Controller {

    SystemManagement systemManagement;
    ArrayList<String> leaguesNames;

    @FXML
    TableView<String[]> tab = new TableView();




    public HomePageController() {
        systemManagement = new SystemManagement();
        leaguesNames = new ArrayList<>();
        leaguesNames = systemManagement.getLeagueNames();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Stage s = Main.getStage();
        s.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });

        setText();

    }

    public void setText() {

        String[][] data = new String[10][8];
        int i=0;
        for(String leagueString:leaguesNames) {
            ArrayList<String> games = systemManagement.closestGames(leagueString);
            for (String game : games) {
                data[i] = new String[]{leagueString, game.split("%")[0], game.split("%")[1], game.split("%")[2],
                        game.split("%")[3], game.split("%")[4], game.split("%")[5], game.split("%")[6]};
                i++;
            }
        }

        TableColumn<String[], String> columnOne = new TableColumn();
        columnOne.setText("League");

        TableColumn<String[], String> columnTwo = new TableColumn();
        columnTwo.setText("Home Team");

        TableColumn<String[], String> columnThree = new TableColumn();
        columnThree.setText("Away Team");

        TableColumn<String[], String> columnFour = new TableColumn();
        columnFour.setText("Date");

        TableColumn<String[], String> columnFive = new TableColumn();
        columnFive.setText("Hour");

        TableColumn<String[], String> columnSix = new TableColumn();
        columnSix.setText("Minute");

        TableColumn<String[], String> columnSeven = new TableColumn();
        columnSeven.setText("Stadium");

        TableColumn<String[], String> columneight = new TableColumn();
        columneight.setText("Status");

        tab.getColumns().addAll(columnOne, columnTwo,columnThree,columnFour,columnFive,columnSix,columnSeven,columneight);

        // Add cell value factories
        columnOne.setCellValueFactory((p) -> {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 0 ? x[0] : "<no name>");
        });

        columnTwo.setCellValueFactory((p) -> {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[1] : "<no value>");
        });

        columnThree.setCellValueFactory((p) -> {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[2] : "<no value>");
        });

        columnFour.setCellValueFactory((p) -> {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[3] : "<no value>");
        });

        columnFive.setCellValueFactory((p) -> {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[4] : "<no value>");
        });

        columnSix.setCellValueFactory((p) -> {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[5] : "<no value>");
        });
        columnSeven.setCellValueFactory((p) -> {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[6] : "<no value>");
        });

        columneight.setCellValueFactory((p) -> {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[7] : "<no value>");
        });
        columnOne.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 0) {
                    return new SimpleStringProperty(x[0]);
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });

        columnTwo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[1]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        columnThree.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[2]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        columnFour.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[3]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        columnFive.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[4]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        columnSix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[5]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        columnSeven.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[6]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        columneight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[7]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        tab.getItems().addAll(Arrays.asList(data));

    }

}
