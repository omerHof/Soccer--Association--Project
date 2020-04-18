package LeagueSeasonsManagment;

import DataForTest.DataBase;
import Games.Game;
import SystemLogic.DB;
import Teams.Statistics;
import Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class SeasonScoreBoardTest {

    DB db = DB.getInstance();
    DataBase test = new DataBase();
    Season season;

    @Before
    public void setUp() throws Exception {
        season = db.getLeague("Champions league").getSeasonByYear(2021);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sortByValue() {
        try{
            //season.getSeasonScoreBoard().sortByValue();
            Thread.sleep(60000);
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void showTable() {
        try{
            SeasonScoreBoard seasonScoreBoard = season.getSeasonScoreBoard();
            seasonScoreBoard.getTeamByName("barca").getStatistics().setScore(5);
            seasonScoreBoard.getTeamByName("man u").getStatistics().setScore(4);
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