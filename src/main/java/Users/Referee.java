package Users;

import Games.Game;
import SystemLogic.MainSystem;
import javafx.scene.control.Alert;

import java.util.LinkedList;

public class Referee extends User {


    private String qualification;
    private LinkedList<Game> myGames;

    public Referee(String userName, String password, String fullName,String userEmail, String qualification) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
        this.qualification = qualification;
        myGames = new LinkedList<>();
    }

    public void updatePersonalDetails(String fullName, String qualification){

        if (fullName != "" && qualification != ""){
            this.userFullName = fullName;
            this.qualification = qualification;
            MainSystem.LOG.info(userName + ": referee's details were updated.");
        }
        else if (fullName != "")
            this.userFullName = fullName;
        else if(qualification != "")
            this.qualification = qualification;
        else
            //displayError("Nothing to update!");
        System.out.println("Nothing to update!");
    }


    private void displayError(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(error);
        alert.show();
    }

    public void addGame (Game g){ //adds a game to the referee's list of games.
        myGames.add(g);
    }

    public void watchGamesList (){

        for (Game g : myGames){
            System.out.println(g.getGameDate() + " , " + g.getGameHour() + " , " + g.getHomeTeam().getName() + "-" + g.getAwayTeam().getName());
        }
    }
}
