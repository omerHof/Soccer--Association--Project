package Teams;

import SystemLogic.DB;
import Users.Coach;
import Users.Manager;
import Users.Player;
import Users.TeamOwner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TeamTest {
    Team hapoel;
    Team maccabi;
    Player vermut;
    Player zahavi;
    Coach klinger;
    DB dbtest;



    @Before
    public void setUp() throws Exception {
        LocalDate date1 = LocalDate.of(1985,1,12);
        LocalDate date2 = LocalDate.of(1987,6,12);
        LocalDate date3 = LocalDate.of(1968,6,12);

        hapoel = new Team("hapoel tel aviv");
        maccabi = new Team("maccabi tel aviv");
        vermut = new Player("ver","123","gili vermut","g",date1,"midelfer");
        zahavi = new Player("ez7","123","rean zahavi","eeee",date2,"striker");
        klinger = new Coach("kling","1212","nir klinger","dsdasd","head coach");
        dbtest = DB.getInstance();
        dbtest.addTeam(hapoel);
        dbtest.addTeam(maccabi);
        dbtest.addUser(vermut);
        dbtest.addUser(zahavi);
        dbtest.addUser(klinger);

    }

    @After
    public void tearDown() throws Exception {
        dbtest.removeUser(zahavi.getUserName());
        dbtest.removeUser(vermut.getUserName());
        dbtest.removeUser(klinger.getUserName());
        dbtest.removeTeam(maccabi.getName());
        dbtest.removeTeam(hapoel.getName());


    }

    @Test
    public void createPage() {

        //before
        assertEquals(null,hapoel.getPage());

        //after
        hapoel.createPage("team in israel, play in red","israel");
        assertFalse(hapoel.getPage()==null);
        assertEquals(hapoel.getPage().getName(),hapoel.getName());

    }

    @Test
    public void getPage() {

        hapoel.createPage("team in israel, play in red","israel");
        maccabi.createPage("team in israel, play in yellow","israel");
        assertFalse(hapoel.getPage()==maccabi.getPage());

    }

    @Test
    public void getStatistics() {
    }

    @Test
    public void setStatistics() {
    }

    @Test
    public void containsAssent() {
    }

    @Test
    public void addAssent() {
    }

    @Test
    public void removeAssent() {
    }

    @Test
    public void setStatus() {
        maccabi.setStatus(Team.teamStatus.close);
        assertEquals(maccabi.getStatus(),Team.teamStatus.close);
        maccabi.setStatus(Team.teamStatus.active);
        assertEquals(maccabi.getStatus(),hapoel.getStatus());

    }

    @Test
    public void addPlayer() {
        ///before
        assertEquals(hapoel.getPlayers().size(),0);

        ///after
        hapoel.addPlayer(vermut);
        assertEquals(hapoel.getPlayers().size(),1);
        hapoel.addPlayer(zahavi);
        assertEquals(hapoel.getPlayers().size(),2);

        maccabi.addPlayer(zahavi);
        assertEquals(hapoel.getPlayers().size(),1);
        assertEquals(maccabi.getPlayers().size(),1);



    }

    @Test
    public void removePlayer() {

        ///before
        assertEquals(hapoel.getPlayers().size(),0);

        ///after
        hapoel.addPlayer(vermut);
        assertEquals(hapoel.getPlayers().size(),1);
        hapoel.removePlayer(vermut);
        assertEquals( vermut.getCurrentTeam(),null);
        assertEquals(hapoel.getPlayers().size(),0);


        /*
        hapoel.addPlayer(zahavi);
        assertEquals(hapoel.getPlayers().size(),2);
        maccabi.addPlayer(zahavi);
        assertEquals(hapoel.getPlayers().size(),1);
        assertEquals(maccabi.getPlayers().size(),1);
        System.out.println(zahavi.getCurrentTeam().getName());

         */

    }

    @Test
    public void addCoach() {
        ///before
        assertEquals(hapoel.getCoaches().size(),0);

        //aftet
        hapoel.addCoach(klinger);
        assertEquals(hapoel.getCoaches().size(),1);

        maccabi.addCoach(klinger);
        assertEquals(hapoel.getCoaches().size(),0);
        assertEquals(maccabi.getCoaches().size(),1);



    }

    @Test
    public void removeCoach() {
        assertEquals(hapoel.getCoaches().size(),0);

        //aftet
        hapoel.addCoach(klinger);
        assertEquals(hapoel.getCoaches().size(),1);
        hapoel.removeCoach(klinger);
        assertEquals(hapoel.getCoaches().size(),0);




    }

    @Test
    public void addManager() {
        Manager manager = new Manager("aa","aa","aaa","aaaa");
        hapoel.addManager(manager);
        assertEquals(hapoel.getManagers().size(),1);
        maccabi.addManager(manager);
        assertEquals(hapoel.getManagers().size(),0);
        assertEquals(maccabi.getManagers().size(),1);



    }

    @Test
    public void removeManager() {
        Manager manager = new Manager("aa","aa","aaa","aaaa");
        hapoel.addManager(manager);
        hapoel.removeManager(manager);
        assertEquals(hapoel.getManagers().size(),0);
    }

    @Test
    public void addTeamOwner() {
        TeamOwner teamOwner = new TeamOwner("rerere","rerer","rrrr","rere");
        hapoel.addTeamOwner(teamOwner);
        assertEquals(hapoel.getTeamOwners().size(),1);
        maccabi.addTeamOwner(teamOwner);
        assertEquals(hapoel.getTeamOwners().size(),0);
        assertEquals(maccabi.getTeamOwners().size(),1);


    }

    @Test
    public void removeTeamOwner() {
        TeamOwner teamOwner = new TeamOwner("rerere","rerer","rrrr","rere");
        hapoel.addTeamOwner(teamOwner);
        assertEquals(hapoel.getTeamOwners().size(),1);
        hapoel.removeTeamOwner(teamOwner);
        assertEquals(hapoel.getTeamOwners().size(),0);

    }


    @Test
    public void getStadium() {
        assertEquals(null,hapoel.getStadium());

        Stadium stadium = new Stadium(20000,15000,10000000);
        hapoel.setStadium(stadium);
        assertEquals(hapoel.getStadium().getCapacity(),15000);
    }

    @Test
    public void setStadium() {
        Stadium stadium = new Stadium(20000,15000,10000000);
        hapoel.setStadium(stadium);
        assertEquals(hapoel.getStadium().getCapacity(),15000);
        Stadium stadium2 = new Stadium(20000,30000,10000000);
        hapoel.setStadium(stadium2);
        assertEquals(hapoel.getStadium().getCapacity(),30000);

    }

    @Test
    public void containsPlayer() {
    }

    @Test
    public void getGameList() {
    }

    @Test
    public void setGameList() {
    }

    @Test
    public void getName() {
        assertEquals(hapoel.getName(),"hapoel tel aviv");
    }

    @Test
    public void setName() {
        maccabi.setName("macccabi");
        assertFalse(maccabi.getName()=="maccabi tel aviv");
        assertEquals(maccabi.getName(),"macccabi");
    }

    @Test
    public void addGame() {
    }

    @Test
    public void getStatus() {
        assertEquals(hapoel.getStatus(), Team.teamStatus.active);
    }

    @Test
    public void getBudget() {
        assertTrue(hapoel.getBudget()==0);

    }

    @Test
    public void setBudget() {
        hapoel.setBudget(1);
        assertTrue(hapoel.getBudget()==1);
    }


}