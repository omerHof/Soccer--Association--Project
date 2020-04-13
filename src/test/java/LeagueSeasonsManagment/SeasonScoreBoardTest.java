package LeagueSeasonsManagment;

import SystemLogic.DB;
import Teams.Statistics;
import Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class SeasonScoreBoardTest {

    SeasonScoreBoard seasonScoreBoard;
    ArrayList<Team> teams=new ArrayList<>();

    Team a= new Team("barca");
    Team b= new Team("real");
    Team c= new Team("man u");
    Team d= new Team("man city");
    Team e= new Team("liverpool");

    Statistics statisticsA;
    Statistics statisticsB;
    Statistics statisticsC;
    Statistics statisticsD;
    Statistics statisticsE;

    IScorePolicy policy;


    @Before
    public void setUp() throws Exception {
        policy= new SimpleScorePolicy();
        statisticsA= new Statistics(policy);
        a.setStatistics(statisticsA);
        teams.add(a);
        statisticsB= new Statistics(policy);
        b.setStatistics(statisticsB);
        teams.add(b);
        statisticsC= new Statistics(policy);
        c.setStatistics(statisticsC);
        teams.add(c);
        statisticsD= new Statistics(policy);
        d.setStatistics(statisticsD);
        teams.add(d);
        statisticsE= new Statistics(policy);
        e.setStatistics(statisticsE);
        teams.add(e);
        seasonScoreBoard= new SeasonScoreBoard(teams,policy);
        DB.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sortByValue() {
        try{
            seasonScoreBoard.sortByValue();
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void showTable() {
        try{
            seasonScoreBoard.getTeamByName("barca").getStatistics().setScore(3);
            seasonScoreBoard.getTeamByName("man u").getStatistics().setScore(3);
            seasonScoreBoard.getTeamByName("barca").getStatistics().setGoals(3);
            seasonScoreBoard.getTeamByName("man u").getStatistics().setGoals(4);
            seasonScoreBoard.getTeamByName("barca").getStatistics().setGc(1);
            seasonScoreBoard.getTeamByName("man u").getStatistics().setGc(3);

            seasonScoreBoard.sortByValue();
            seasonScoreBoard.showTable();
        }catch (Exception e) {
            System.out.println("error");
        }
    }
}