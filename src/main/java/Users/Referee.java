package Users;

import Games.Game;
import SystemLogic.MainSystem;
import javafx.scene.control.Alert;

import java.util.LinkedList;
import java.util.List;

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
/*

    public void updatePersonalDetails(String fullName, String qualification){

        if (fullName != "" && qualification != ""){
            this.userFullName = fullName;
            this.qualification = qualification;
            MainSystem.LOG.info(userName + ": referee's details were updated.");
        }
        else if (fullName != "")
            setUserFullName(fullName);
        else if(qualification != "")
            this.qualification = qualification;
        else
            //displayError("Nothing to update!");
        System.out.println("Nothing to update!");
    }
*/

    public String getQualification() {
        return qualification;
    }

    private void displayError(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(error);
        alert.show();
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
        MainSystem.LOG.info(this.userName + ": referee's details were updated.");
    }


    public void addGame (Game g){ //adds a game to the referee's list of games.
        myGames.add(g);
    }


    public List<String> watchGamesList (){

        LinkedList<String> allGames = new LinkedList<>();

        if (myGames != null) {
            for (Game g : myGames) {
                if( g != null)
                    allGames.add(g.getGameDate() + " , " + g.getHomeTeam().getName() + "-" + g.getAwayTeam().getName());
            }
            return allGames;
        }
        return null;
    }
}
