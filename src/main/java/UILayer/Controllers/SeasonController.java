package UILayer.Controllers;

import ServiceLayer.LeagueSeasonManagement;
import UILayer.Main;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class SeasonController extends Controller {

    LeagueSeasonManagement seasonManagement = new LeagueSeasonManagement();
    List<String> arrayTeams;
    List<String> arrayLeagues;
    List<String> arrayrepresentatives;
    List<String> arrayReferees;

    List<String> arrayTeamsChosen;
    String leaguesChosen;
    List<String> arrayrepresentativesChosen;
    List<String> arrayRefereesChosen;
    int yearChosen;
    String scoreChosen;
    String gameChosen;


    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private ChoiceBox<String> choiceBox2;
    @FXML
    private ChoiceBox<String> choiceBox3;
    @FXML
    private ChoiceBox<String> choiceBox4;
    @FXML
    private ChoiceBox<String> choiceBox5;
    @FXML
    private ChoiceBox<String> choiceBox6;
    @FXML
    private ChoiceBox<String> choiceBox7;

    ObservableList<String> options = FXCollections.observableArrayList();
    ObservableList<String> options2 = FXCollections.observableArrayList();
    ObservableList<String> options3 = FXCollections.observableArrayList();
    ObservableList<String> options4 = FXCollections.observableArrayList();
    ObservableList<String> options5 = FXCollections.observableArrayList();
    ObservableList<String> options6 = FXCollections.observableArrayList();
    ObservableList<String> options7 = FXCollections.observableArrayList();

    ObservableList<String> league = FXCollections.observableArrayList();
    ObservableList<String> referees = FXCollections.observableArrayList();
    ObservableList<String> teams = FXCollections.observableArrayList();
    ObservableList<String> representatives = FXCollections.observableArrayList();


    public SeasonController() {


        arrayLeagues = seasonManagement.getLeagues();
        arrayTeams = seasonManagement.getTeams();
        arrayReferees = seasonManagement.getReferees();
        arrayrepresentatives = seasonManagement.getRepresentatives();

        setInfo();

        choiceBox1 = new ChoiceBox<>();
        choiceBox2 = new ChoiceBox<>();
        choiceBox3 = new ChoiceBox<>();
        choiceBox4 = new ChoiceBox<>();

        arrayTeamsChosen=new ArrayList<>();
        arrayrepresentativesChosen=new ArrayList<>();
        arrayRefereesChosen=new ArrayList<>();

    }

    public void setInfo() {
        for(String league:arrayLeagues){
            options.add(league);
        }
        for(String referee:arrayReferees){
            options2.add(referee);
        }
        for(String team:arrayTeams){
            options3.add(team);
        }
        for(String representative:arrayrepresentatives){
            options4.add(representative);
        }
        int curYear;
        for(int i=1; i<20; i++){
            curYear=2020+i;
            options5.add(""+curYear);
        }
        options7.add("RegularScorePolicy");
        options7.add("GoalScorePolicy");
        options6.add("OneRoundGamePolicy");
        options6.add("TwoRoundsGamePolicy");
        options6.add("RandomTwoRoundsGamePolicy");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        setOptions();
    }


    public void setOptions() {
        try {
            choiceBox1.setItems(options);
            choiceBox2.setItems(options2);
            choiceBox3.setItems(options3);
            choiceBox4.setItems(options4);
            choiceBox5.setItems(options5);
            choiceBox6.setItems(options6);
            choiceBox7.setItems(options7);

        } catch (Exception e) {

        }
    }

    @FXML
    ComboBox comboLeague;

    public void addLeague() {
        try {
            if (league.size() == 0) {
                league.add(choiceBox1.getValue());
            }
            if (league.size() == 1) {
                league.clear();
                league.add(choiceBox1.getValue());
                comboLeague.setItems(league);
            }
        } catch (Exception e) {

        }
    }

    @FXML
    ComboBox comboReferee;

    public void addReferee() {
        try {
            if (!referees.contains(choiceBox2.getValue())) {
                referees.add(choiceBox2.getValue());
            }
            comboReferee.setItems(referees);

        } catch (Exception e) {

        }
    }

    @FXML
    ComboBox comboTeams;

    public void addTeam() {
        try {
            if (!teams.contains(choiceBox3.getValue())) {
                teams.add(choiceBox3.getValue());
            }
            comboTeams.setItems(teams);
        } catch (Exception e) {

        }
    }

    @FXML
    ComboBox comboAsso;
    public void addAsso() {
        try {
            if (!representatives.contains(choiceBox4.getValue())) {
                representatives.add(choiceBox4.getValue());
            }
            comboAsso.setItems(representatives);
        } catch (Exception e) {

        }
    }
    public void createSeason(ActionEvent actionEvent) {
        if(check()) {
            assertInfo();
            seasonManagement.addSeasonToLeague(leaguesChosen, yearChosen, scoreChosen, gameChosen, arrayTeamsChosen, arrayRefereesChosen, arrayrepresentativesChosen);
        }
    }

    public boolean check(){
        if(comboLeague.getItems().size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose league\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;
        }
        if(comboReferee.getItems().size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose referees\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        if(comboTeams.getItems().size()<2){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose at least 2 teams\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        if(comboTeams.getItems().size()%2==1) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose even number of teams\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;
        }
        if(comboAsso.getItems().size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose asso\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;
        }
        if(choiceBox5.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose year\n", ButtonType.CLOSE);//todo
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        if(choiceBox6.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose game policy\n", ButtonType.CLOSE);//todo
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        if(choiceBox7.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose score policy\n", ButtonType.CLOSE);//todo
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        return true;

    }

    public void assertInfo() {
        for(String league:league){
            leaguesChosen=league;
        }
        for(String referee:referees){
            arrayRefereesChosen.add(referee);
        }

        for(String team:teams){
            arrayTeamsChosen.add(team);
        }
        for(String representative:representatives){
            arrayrepresentativesChosen.add(representative);
        }
        if(!choiceBox5.getValue().equals("")) {
            yearChosen = Integer.parseInt(choiceBox5.getSelectionModel().getSelectedItem());
        }
        if(!choiceBox6.getValue().equals("")) {
            gameChosen = choiceBox6.getSelectionModel().getSelectedItem();
        }
        if(!choiceBox7.getValue().equals("")) {
            scoreChosen = choiceBox7.getSelectionModel().getSelectedItem();
        }
    }

    public void gamePolicyInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "3 Game Policies:\n\n" +
                "1. One round of games between all teams\n" +
                "2. Two round of games between all teams- on both stadiums\n" +
                "3. Random two rounds- can't know the order of games", ButtonType.CLOSE);
        alert.setHeaderText("Game Policies Information");
        alert.showAndWait();

    }

    public void scorePolicyInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "2 Score Policies:\n\n" +
                "1. Regular- 3 points for win, 1 points for draw, 0 points for lose\n" +
                "this policy gives preference to high different goals\n\n" +
                "2. Goal- 2 points for win, 1 points for draw, 0 points for lose\n" +
                "this policy gives preference to more goals", ButtonType.CLOSE);        alert.setHeaderText("Score Policies Information");
        alert.showAndWait();
    }

}
