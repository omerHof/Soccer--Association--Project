package Users;

import LeagueSeasonsManagment.Season;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class AssociationRepresentativeTest {

    private DB dbTest;
    private AssociationRepresentative assoTest;
    private MainSystem mainSystem;
    private List<String> teams = new LinkedList<>();
    private List<String> referees = new LinkedList<>();
    private List<String> repress = new LinkedList<>();

    @Before
    public void setUp() throws Exception {
        dbTest = DB.getInstance();
        mainSystem = MainSystem.getInstance();
        assoTest = new AssociationRepresentative("talish94", "taata", "Tali", "tali@gmail");
    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void approveRegistration() {
    }

    @Test
    public void addLeague() {

        dbTest = DB.getInstance();
        mainSystem = MainSystem.getInstance();
        assoTest = new AssociationRepresentative("talish94", "taata", "Tali", "tali@gmail");

        assertTrue("UC 9.1 OK! - added a new league.", assoTest.addLeague("Alufut", 10));
        assertFalse("UC 9.1 OK! - doesn't add an existing league.", assoTest.addLeague("Alufut", 5));

    }

    @Test
    public void addSeasonToLeague() {

        dbTest = DB.getInstance();
        mainSystem = MainSystem.getInstance();
        assoTest = new AssociationRepresentative("talish94", "taata", "Tali", "tali@gmail");

        String a = "Maccabi Tel Aviv";
        String b ="Maccabi Haifa";
        String c = "Beitar";
        String d = "Hapoel Beer Sheva";

        teams.add(a);
        //teams.add(b);
        //teams.add(c);
        //teams.add(d);

        //Referee r1 = new Referee("r1", "rr", "bla bla", "ziv@ziv", "none");
        //AssociationRepresentative a1 = new AssociationRepresentative("a1", "aa", "yiftah", "szoke@szoke");

        referees.add("ramzi ramzen");
        repress.add("a1");

        assoTest.addSeasonToLeague("Alufut", 2020, "simple", "notSimple", teams, referees, repress);
        List<Season> allSeasons = dbTest.getLeague("Alufut").getAllSeasons();
        Season newS = allSeasons.remove(0); //gets the season object that was added.

        assertTrue(newS.getYear() == 2020);
        assertTrue(newS.getiScorePolicy().equals("simple"));
        assertFalse(newS.getiGameInlayPolicy().equals("simple")); // suppose to be notSimple

        List<Referee> allRef = newS.getAllReferees();
        assertTrue(allRef.remove(0).userFullName.equals("ramzi ramzen"));

        List<AssociationRepresentative> allAss = newS.getAllRepresentatives();
        assertFalse(allAss.remove(0).userFullName.equals("ramzi ramzen"));
        assertTrue(allAss.remove(0).userFullName.equals("a1"));

        List<Team> allTe = newS.getAllTeams();
        assertTrue(allTe.remove(0).getName().equals("a"));
    }

    @Test
    public void addReferee() {
    }

    @Test
    public void removeReferee() {
    }

    @Test
    public void setLeagueReferees() {
    }
}