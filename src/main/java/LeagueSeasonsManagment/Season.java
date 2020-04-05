package LeagueSeasonsManagment;

import Games.Game;

import java.util.List;

public class Season {

    private int year;
    private List<Game> allGames;
    private IGameInlayPolicy iGameInlayPolicy;

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
   /*
    public void GameInlayPolicyAlgoImplementation(){
        //////to complete
    }

     */

}
