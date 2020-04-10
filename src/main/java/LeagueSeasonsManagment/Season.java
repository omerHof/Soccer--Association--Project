package LeagueSeasonsManagment;

import Games.Game;
import Teams.Team;
import Users.Referee;

import java.sql.Ref;
import java.util.List;

public class Season {

    private int year;
    private List<Game> allGames;
    private List<Team> allTeams;
    private IGameInlayPolicy iGameInlayPolicy;
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

    //setters
    public void setYear(int newYear){
        this.year=newYear;
    }
    public void setAllGames(List<Game>games){
        allGames=games;
    }

    public void setAllTeams(List<Team> allTeams) {
        this.allTeams = allTeams;
    }
    /*
    public void GameInlayPolicyAlgoImplementation(){
        //////to complete
    }

     */

    public void setiGameInlayPolicy(IGameInlayPolicy iGameInlayPolicy) {
        this.iGameInlayPolicy = iGameInlayPolicy;
    }
}
