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

    private List<Team> allTeams = new LinkedList<>();

    Referee r1 = new Referee("r1", "rr", "bla bla", "ziv@ziv", "none");
    AssociationRepresentative a1 = new AssociationRepresentative("a1", "aa", "yiftah", "szoke@szoke");

    private Team t1 = new Team("Maccabi Tel Aviv");
    private Team t2 = new Team("Maccabi Haifa");

    private String a = "Maccabi Tel Aviv";
    private String b ="Maccabi Haifa";
    private String c = "Beitar";
    private String d = "Hapoel Beer Sheva";

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

       // dbTest = DB.getInstance();
       // mainSystem = MainSystem.getInstance();
       // assoTest = new AssociationRepresentative("talish94", "taata", "Tali", "tali@gmail");

        assertTrue("UC 9.1 OK! - added a new league.", assoTest.addLeague("Alufut", 10));
        assertFalse("UC 9.1 OK! - doesn't add an existing league.", assoTest.addLeague("Alufut", 5));
    }

    @Test
    public void addSeasonToLeague() {

        assoTest = new AssociationRepresentative("talish94", "taata", "Tali", "tali@gmail");
        assoTest.addLeague("Alufut", 2);

        teams.add(a);
        teams.add(b);
        //teams.add(c);
        //teams.add(d);

        allTeams.add(t1);
        allTeams.add(t2);

        Referee r1 = new Referee("r1", "rr", "ramzi ramzen", "ziv@ziv", "bla bla");
        AssociationRepresentative a1 = new AssociationRepresentative("a1", "aa", "yiftah", "szoke@szoke");

        referees.add("ramzi ramzen");
        repress.add("yiftah");
        dbTest.setTeam(new Team("Maccabi Tel Aviv"));
        dbTest.setTeam(new Team("Maccabi Haifa"));
        dbTest.getLeague("Alufut").setTeams(allTeams);


        dbTest.setUser(r1);
        dbTest.setUser(a1);
        //dbTest.setTeam(new Team("Maccabi Tel Aviv"));

        assoTest.addSeasonToLeague("Alufut", 2020, "SimpleScorePolicy", "SimpleGamePolicy", teams, referees, repress);
        List<Season> allSeasons = dbTest.getLeague("Alufut").getAllSeasons();
        Season newS = allSeasons.get(0); //gets the season object that was added.

        assertTrue(newS.getYear() == 2020);
        Referee testReferee = newS.getAllReferees().get(0);
        assertTrue(testReferee.getQualification().equals("bla bla"));

        assertTrue(newS.getiScorePolicy().getName().equals("SimpleScorePolicy"));   //  todo: check thissss /v/v/d/f//f//f/
        assertFalse(newS.getiGameInlayPolicy().getName().equals("SimpleGamePolicy")); // suppose to be notSimple   todo: check thissss

        List<Referee> allRef = newS.getAllReferees();
        assertTrue(allRef.get(0).userFullName.equals("ramzi ramzen"));

        List<AssociationRepresentative> allAss = newS.getAllRepresentatives();
        assertFalse(allAss.get(0).userFullName.equals("ramzi ramzen"));
        assertEquals(allAss.get(0).userFullName, ("yiftah"));

        List<Team> allTe = newS.getAllTeams();
        assertEquals(allTe.get(0).getName(), "Maccabi Tel Aviv");
    }

    @Test
    public void addReferee() {

        Fan fanTest = new Fan("f", "ff", "omer hof", "omer@hof");
        AssociationRepresentative assocTest = new AssociationRepresentative("a1", "aa", "tali", "tali@tali");
        dbTest.addUser(fanTest);
        dbTest.addUser(assocTest);

        assertNull(dbTest.getUserType("Referee")); //not exists yet.
        assertFalse(dbTest.getUserByFullName("omer hof") instanceof Referee);
        assertTrue(dbTest.getUserByFullName("omer hof") instanceof Fan);

        assocTest.addReferee("omer hof");
        assertEquals(dbTest.getUserByFullName("omer hof").password, "ff");
        assertTrue(dbTest.getUserByFullName("omer hof") instanceof Referee);

        assertFalse(assoTest.addReferee("notExistsUser"));
    }

    @Test
    public void removeReferee() {

        Referee r1 = new Referee("r1", "rr", "ramzi ramzen", "ziv@ziv", "bla bla");
        AssociationRepresentative assocTest = new AssociationRepresentative("a1", "aa", "tali", "tali@tali");
        dbTest.addUser(r1);
        dbTest.addUser(assocTest);

        assertNull(dbTest.getUserType("Referee")); //not exists yet.
        assertFalse(dbTest.getUserByFullName("omer hof") instanceof Referee);

        assocTest.removeReferee("omer hof");
        assertEquals(dbTest.getUserByFullName("omer hof").password, "ff");

        assertFalse(assoTest.addReferee("notExistsUser"));
    }


    @Test
    public void setLeagueReferees() {
    }
}