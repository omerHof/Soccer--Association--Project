package SystemLogic;

import Games.Game;
import LeagueSeasonsManagment.*;
import Teams.Statistics;
import Teams.Team;
import Users.*;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DBTest {
    private User user;
    private League league;
    private ArrayList<Season> seasons;
    private Season season;
    private ArrayList<Team> teams;
    private ArrayList<Referee>referees;
    private ArrayList<AssociationRepresentative>representatives;
    private IGameInlayPolicy gameInlayPolicy;
    private IScorePolicy scorePolicy;
    DB db=DB.getInstance();

    private Referee referee1;
    private Referee referee2;
    private Referee referee3;
    private MainReferee mainReferee;

    private AssociationRepresentative representative;
    private Team a= new Team("barca");
    private Team b= new Team("real");


    private Statistics statisticsA;
    private Statistics statisticsB;


    IScorePolicy policy;

    Game game;

    @Before
    public void setUp() throws Exception {
        user= new Administrator("The King","1234","Oren Hason","OrenHason@gmail.com");
        league= new League("premier league",10);
        seasons = new ArrayList<>();
        teams = new ArrayList<>();
        referees= new ArrayList<>();
        representatives = new ArrayList<>();
        gameInlayPolicy = new TwoRoundsGamePolicy(teams,2020);
        scorePolicy = new RegularScorePolicy();

        policy= new RegularScorePolicy();

        referee1 = new Referee("a","a","a","a","a");
        referees.add(referee1);
        referee2 = new Referee("a","a","a","a","a");
        referees.add(referee2);
        referee3 = new Referee("a","a","a","a","a");
        referees.add(referee3);
        mainReferee = new MainReferee("a","a","a","a","a");
        DB.getInstance().setUser(mainReferee);
        representative = new AssociationRepresentative("a","a","a","a");
        representatives.add(representative);

        statisticsA= new Statistics(policy);
        a.setStatistics(statisticsA);
        teams.add(a);
        statisticsB= new Statistics(policy);
        b.setStatistics(statisticsB);
        teams.add(b);



        season = new Season(2020,teams,referees,representatives,scorePolicy.getName(),gameInlayPolicy.getName());
        seasons.add(season);
        league.setAllSeasons(seasons);

        db.setUser(user);
        db.setLeague(league);
        db.setTeam(a);
        db.setTeam(b);

        a.setBudget(100);
        System.out.println(db.getTeam("barca").getBudget());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getResultsInstance() {
    }

    @Test
    public void getUser() {
        try {
            assertEquals("test failed",user.getUserName(),db.getUser("The King").getUserName());
            assertEquals("test failed",null,db.getUser("The Queen"));
        }catch (Exception e){
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void getUserByFullName() {
        try {
            assertEquals("test failed",user.getUserFullName(),db.getUserByFullName("Oren Hason").getUserFullName());
            assertEquals("test failed",null,db.getUserByFullName("The Queen"));
        }catch (Exception e){
            java.lang.System.out.println("test failed");
        }

    }

    @Test
    public void userExist() {
        try {
            assertTrue("test failed",db.userExist("The King"));
            assertFalse("test failed",db.userExist("The Queen"));
        }catch (Exception e){
            java.lang.System.out.println("test failed");
        }

    }
    @Test
    public void setUser() {
        try {
            User goodUser = new Administrator("testUser", "1111", "good","e");
            User badUser = new Administrator("testUser", "1111", "bad","e");

            db.setUser(goodUser);
            db.setUser(badUser);
            assertEquals("test failed",goodUser.getUserName(),db.getUserByFullName("good").getUserName());
            assertEquals(null,db.getUserByFullName("bad"));

        }catch (Exception e){
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void addUser() {
        try {
            User goodUser = new Administrator("testUser", "1111", "good", "e");

            assertTrue("test failed",db.addUser(goodUser));
            assertFalse("test failed",db.addUser(null));
            assertFalse("test failed",db.addUser(goodUser));

        }catch (Exception e){
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void removeUser() {
        try {
            assertTrue("test failed",db.removeUser("The King"));
            assertFalse("test failed",db.removeUser("The King"));

        }catch (Exception e){
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void getLeague() {
        try {
            assertEquals("test failed",league.getName(),db.getLeague("premier league").getName());
            assertEquals("test failed",null,db.getLeague("a"));
        }catch (Exception e){
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void leagueExist() {
        try {
            assertTrue("test failed",db.leagueExist("premier league"));
            assertFalse("test failed",db.leagueExist("a"));
        }catch (Exception e){
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void setLeague() {
        try {
            League goodLeague = new League("A", 6);
            //League badLeague = new League("A", 6);

            db.setLeague(goodLeague);
            //db.setLeague(badLeague);
            assertEquals("test failed", goodLeague.getName(), db.getLeague("A").getName());
            //assertNotEquals("test failed", badLeague, db.getLeague("A"));
        } catch (Exception e) {
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void addLeague() {
        try {
            League goodLeague = new League("A", 6);

            assertTrue("test failed", db.addLeague(goodLeague));
            assertFalse("test failed", db.addLeague(null));
            assertFalse("test failed", db.addLeague(goodLeague));

        } catch (Exception e) {
            java.lang.System.out.println("test failed");
        }
    }
    @Test
    public void addSeason() {
        try {
            ArrayList<Referee>referees= new ArrayList<>();
            ArrayList<AssociationRepresentative>representatives= new ArrayList<>();
            ArrayList<Team>teams= new ArrayList<>();
            Season goodSeason = new Season(2020,teams,referees,representatives,"RegularScorePolicy","TwoRoundsGamePolicy");


            assertTrue("test failed", db.addSeason("premier league",goodSeason));
            assertFalse("test failed", db.addSeason("a",goodSeason));
            assertFalse("test failed", db.addSeason("premier league",goodSeason));
            assertFalse("test failed", db.addSeason("premier league",null));

        } catch (Exception e) {
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void removeLeague() {
        try {
            assertTrue("test failed",db.removeLeague("premier league"));
            assertFalse("test failed",db.removeLeague("premier league"));

        }catch (Exception e){
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void getTeam() {
        try {
            assertEquals("test failed",a.getName(),db.getTeam("Barca").getName());
            assertEquals("test failed",null,db.getTeam("a"));
        }catch (Exception e){
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void teamExist() {
        try {
            assertTrue("test failed",db.teamExist("barca"));
            assertFalse("test failed",db.teamExist("a"));
        }catch (Exception e){
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void setTeam() {
        try {
            Team goodTeam = new Team("A");
            //Team badTeam = new Team("A");

            db.setTeam(goodTeam);
            //db.setTeam(badTeam);
            assertEquals("test failed", goodTeam.getName(), db.getTeam("A").getName());
            //assertNotEquals("test failed", badTeam.getName(), db.getTeam("A").getName());
        } catch (Exception e) {
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void addTeam() {
        try {
            Team goodTeam = new Team("A");

            assertTrue("test failed", db.addTeam(goodTeam));
            assertFalse("test failed", db.addTeam(null));
            assertFalse("test failed", db.addTeam(goodTeam));

        } catch (Exception e) {
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void removeTeam() {
        try {
            assertTrue("test failed",db.removeTeam("barca"));
            assertFalse("test failed",db.removeTeam("barca"));

        }catch (Exception e){
            java.lang.System.out.println("test failed");
        }
    }

    @Test
    public void getUserType() {
        try {
            User result=db.getUserType("Administrator");
            assertNotEquals("test failed",null,result);

        }
        catch (Exception e){
            java.lang.System.out.println("test failed");
        }
    }


}