package LeagueSeasonsManagment;

import DataForTest.DataBase;
import Games.Game;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Statistics;
import Teams.Team;
import Users.AssociationRepresentative;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class SeasonScoreBoardTest {

    private DB db;
    private AssociationRepresentative assoTest;
    private MainSystem mainSystem;
    DataBase test = new DataBase();
    Game game;

    @Before
    public void setUp() throws Exception {
        db = DB.getInstance();
        mainSystem = MainSystem.getInstance();
        assoTest = new AssociationRepresentative("YS", "123", "Yiftah Szoke", "yszoke@gmail.com");
        game = db.getLeague("Alufot").getAllSeasons().get(1).getAllGames().get(1).get(0);
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

        }catch (Exception e) {
            System.out.println("error");
        }
    }
}