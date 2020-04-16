package Games;

import LeagueSeasonsManagment.*;
import SystemLogic.DB;
import Teams.Statistics;
import Teams.Team;
import Users.AssociationRepresentative;
import Users.MainReferee;
import Users.Referee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {
    Season season;
    ArrayList<Team> teams;
    ArrayList<Referee> referees;
    Referee referee1;
    Referee referee2;
    Referee referee3;
    MainReferee mainReferee;

    ArrayList<AssociationRepresentative> representatives;
    AssociationRepresentative representative;
    Team a= new Team("barca");
    Team b= new Team("real");


    Statistics statisticsA;
    Statistics statisticsB;


    IScorePolicy policy;

    Game game;

    @Before
    public void setUp() throws Exception {
        DB.getInstance();

        teams=new ArrayList<>();
        policy= new RegularScorePolicy();

        referees = new ArrayList<>();
        referee1 = new Referee("a","a","a","a","a");
        referees.add(referee1);
        referee2 = new Referee("a","a","a","a","a");
        referees.add(referee2);
        referee3 = new Referee("a","a","a","a","a");
        referees.add(referee3);
        mainReferee = new MainReferee("a","a","a","a","a");
        DB.getInstance().setUser(mainReferee);
        representatives = new ArrayList<>();
        representative = new AssociationRepresentative("a","a","a","a");
        representatives.add(representative);

        statisticsA= new Statistics(policy);
        a.setStatistics(statisticsA);
        teams.add(a);
        statisticsB= new Statistics(policy);
        b.setStatistics(statisticsB);
        teams.add(b);

        season= new Season(2020,teams,referees,representatives, "RegularScorePolicy","TwoRoundsGamePolicy");
        game = season.getAllGames().get(1).get(0);
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