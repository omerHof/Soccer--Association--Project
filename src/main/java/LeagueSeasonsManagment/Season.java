package LeagueSeasonsManagment;

import Games.Game;
import Teams.Team;
import Users.Referee;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class Season {

    private int year;
    private ArrayList<Game> allGames;
    private ArrayList<Team> allTeams;
    private IGameInlayPolicy iGameInlayPolicy;
    private IScorePolicy iScorePolicy;
    private List<Referee> allReferees;

    public Season(int year) {
        this.year = year;
    }

    //getters
    public int getYear() {
        return year;
    }
    public List<Game> getAllGames() {
        return allGames;
    }

    public IGameInlayPolicy getiGameInlayPolicy() {
        return iGameInlayPolicy;
    }

    public IScorePolicy getiScorePolicy() {
        return iScorePolicy;
    }

    //setters
    public void setYear(int newYear){
        this.year=newYear;
    }
    public void setAllGames(ArrayList<Game>games){
        allGames=games;
    }

    public void setAllTeams(ArrayList<Team> allTeams) {
        this.allTeams = allTeams;
    }
    /*
    public void GameInlayPolicyAlgoImplementation(){
        //////to complete
    }

     */

    public void setiGameInlayPolicy(String iGameInlayPolicy) {
        switch(iGameInlayPolicy) {
            case "SimpleGamePolicy":
                this.iGameInlayPolicy = new SimpleGamePolicy(this.allTeams);
                break;
            case "OtherGamePolicy":
                // code block
                break;
            default:
                this.iGameInlayPolicy = new SimpleGamePolicy(this.allTeams);
        }
    }

    public void setIScorePolicy(String iScorePolicy) {

        switch(iScorePolicy) {
            case "SimpleScorePolicy":
                this.iScorePolicy = new SimpleScorePolicy();
                break;
            case "OtherScorePolicy":
                // code block
                break;
            default:
                this.iScorePolicy = new SimpleScorePolicy();
        }
    }
}
