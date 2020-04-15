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

    /**
     * get table
     * @return
     */
    public ArrayList<Team> getTable() {
        return table;
    }

    /**
     * set table
     * @param table
     */
    public void setTable(ArrayList<Team>table) {
        this.table = table;
    }

    /**
     * get policy
     * @return
     */
    public IScorePolicy getPolicy() {
        return policy;
    }

    /**
     * set policy
     * @param policy
     */
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
            //Statistics statistics= new Statistics(policy);
            team.getStatistics().setNewSeasonStatistics(policy);
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

    public Team getTeamByName(String name){
        for(Team team:table){
            if(team.getName().equals(name)){
                return team;
            }
        }
        return null;
    }

    /**
     * print table
     */
    public void showTable(){
        int i=1;
        System.out.println("Table:");
        for(Team team:table){
            System.out.println(i+". Name: "+ team.getName()+" | score: "+team.getStatistics().getScore()+
                    " | w:"+ team.getStatistics().getWins()+" | d: "+ team.getStatistics().getTie()+" | l: "+ team.getStatistics().getLoses()+
                    " | goals:"+team.getStatistics().getGoals()+
                    " | goals c:"+ team.getStatistics().getGc()+" | dif: "+ team.getStatistics().getDif());
            i++;
        }
    }
}

