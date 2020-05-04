package UILayer.Controllers;

import ServiceLayer.TeamManagement;
import UILayer.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Controller implements Initializable {

    @FXML
    protected String userName;
    protected String userType;


    /**
     *  close the program with confirming
     *  close the servers
     */
    protected void closeProgram() {
        Stage s = Main.getStage();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the system?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Leaving so soon?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            s.close();
        }
    }


    @FXML
    protected void goToLanding() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Landing.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        LandingController lc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }


    @FXML
    protected void goToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Login.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);

        s.setScene(scene);
        LoginController lg = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    @FXML
    public void goToLeagues() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Leagues.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        LeaguesController lc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    @FXML
    protected void goToSignUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/SignUp.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        SignUpController suc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }
    @FXML
    public void goToPlayers() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Players.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        PlayersController pc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }
    @FXML
    public void goToCoaches() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Coaches.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        CoachesController cc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }
    @FXML
    public void goToTeams() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Teams.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        TeamsController cc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    public void goToProfile() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Profile.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        ProfileController cc = fxmlLoader.getController();
        Main.setStage(s);
        s.show();
    }


    public void openMyApps() throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(getClass().getResource("/Views/RepMyApp.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        MyAppController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    public void openAddSeason() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/addSeason.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        SeasonController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }


    public void openNewTeam() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/addTeam.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        TeamsController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    public void openTeamPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/addTeamPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        TeamsController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }


}
