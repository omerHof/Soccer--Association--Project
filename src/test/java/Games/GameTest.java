package Games;

import DataForTest.DataBase;
import SystemLogic.DB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import static org.junit.Assert.*;

public class GameTest {

    DB db = DB.getInstance();
    DataBase test = new DataBase();
    Game game;

    @Before
    public void setUp() throws Exception {

        game = db.getLeague("Champions league").getSeasonByYear(2020).getAllGames().get(1).get(0);
        //db.getTeam("barca").setBudget(100);
        //double money = db.getLeague("Champions league").getSeasonByYear(2020).getSeasonScoreBoard().getTeamByName("barca").getBudget();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setAlarms() {
    }

    @Test
    public void getTimeOfGame() {
        try {
            LocalDateTime time= LocalDateTime.of(2020, Month.JANUARY,1,19,0,0);
            assertEquals("same time", time,game.getTimeOfGame());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void setTimeOfGame() {
    }

    @Test
    public void getHomeTeam() {
    }

    @Test
    public void getAwayTeam() {
    }

    @Test
    public void getGameDate() {
    }

    @Test
    public void getStatus() {
    }

    @Test
    public void getScore() {
    }

    @Test
    public void getGameReferees() {
    }

    @Test
    public void getRepresentative() {
    }

    @Test
    public void setAssociationRepresentative() {
    }

    @Test
    public void getEventBook() {
    }

    @Test
    public void setEventBook() {
    }

    @Test
    public void getFinalReport() {
    }

    @Test
    public void setFinalReport() {
    }

    @Test
    public void setHomeTeam() {
    }

    @Test
    public void setAwayTeam() {
    }

    @Test
    public void setGameDate() {
    }

    @Test
    public void setScore() {
    }

    @Test
    public void setGameReferees() {
    }

    @Test
    public void setStatus() {
    }

    @Test
    public void addEvent() {
    }
}