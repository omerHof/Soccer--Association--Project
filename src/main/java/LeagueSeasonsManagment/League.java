package LeagueSeasonsManagment;

import Teams.Team;

import java.util.LinkedList;
import java.util.List;

public class League {

    private String name;
    private int numOfTeams;
    private List<Season> allSeasons;


    private List<Team>teams;

    public League(String name, int numOfTeams) {
        this.name = name;
        this.numOfTeams = numOfTeams;
        allSeasons = new LinkedList<>();
    }
///getters
    public List<Season> getAllSeasons() {
        return allSeasons;
    }
    public String getName(){
        return name;
    }
    public int getNumOfTeams(){
        return numOfTeams;
    }

    //ido add this
    public List<Team>getTeams(){
        return teams;
    }

    //setters
    public void setAllSeasons(List<Season> allSeasons) {
        this.allSeasons = allSeasons;
    }
    public void setName(String newName){
        name = newName;
    }
    public void setNumOfTeams(int num){
        numOfTeams=num;
    }
    //ido add this
    public void setTeams(List<Team> allTeams){
        teams=allTeams;
    }

    /*
    public void scorePolicyAlgoImplementation(){
        //////to complete
    }

     */

    public Season getSeasonByYear (int year){
        if (year > 0){
            for (Season season : allSeasons){
                if (season.getYear() == year)
                    return season;
            }
        }
        return null;
    }


}
