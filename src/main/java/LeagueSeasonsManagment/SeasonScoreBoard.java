package LeagueSeasonsManagment;

import Games.Game;
import Teams.Statistics;
import Teams.Team;
import com.sun.org.glassfish.external.statistics.Statistic;

import java.util.*;

public class SeasonScoreBoard {

    private ArrayList<Team> teams;
    private ArrayList<Team> table;
    private IScorePolicy policy;

    /**
     * constructor
     * @param teams
     * @param policy
     */
    public SeasonScoreBoard(ArrayList<Team> teams,IScorePolicy policy) {
        this.teams = teams;
        this.policy= policy;
        this.table = initTable();
    }

    public ArrayList<Team> getTable() {
        return table;
    }

    public void setTable(ArrayList<Team>table) {
        this.table = table;
    }

    public IScorePolicy getPolicy() {
        return policy;
    }

    public void setPolicy(IScorePolicy policy) {
        this.policy = policy;
    }

    /**
     * init table
     * @return
     */
    private ArrayList<Team> initTable() {
        ArrayList<Team> table= new ArrayList();
        for(Team team:teams){
            Statistics statistics= new Statistics(policy);
            team.setStatistics(statistics);
            table.add(team);
        }
        return table;
    }

    /**
     * sort table by chosen score policy
     */
    public void sortByValue()
    {
        Collections.sort(table);
    }


    public void showTable(){
        int i=1;
        for(Team team:table){
            System.out.println(1+". Name: "+ team.getName()+" score: "+team.getStatistics().getScore());
        }
    }
}

