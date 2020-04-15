package UserGenerator;

import SystemLogic.DB;
import SystemLogic.MainSystem;
import Users.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PremiumUserGenertatorTest {

    private DB dbTest = DB.getInstance();
    private AssociationRepresentative assoTest = new AssociationRepresentative("aa", "", "alal", "a@a");
    private MainSystem mainSystem = MainSystem.getInstance();
    private PremiumUserGenertator preG = new PremiumUserGenertator();

    @Before
    public void setUp() throws Exception {
        dbTest.addUser(assoTest);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void generate() {

        Player testPlayer = (Player) preG.generate("p1", "94929", "", "player", "Aviv Alush", "tali@aviv", new Date(), "", "balam", "");
        assertNotNull(testPlayer);

        Manager testManager = (Manager) preG.generate("m1", "94929", "", "manager", "omer hof", "o@o", null, "", "", "");
        assertNotNull(testManager);

        Coach testCoach = (Coach) preG.generate("c1", "94929", "", "coach", "roi katz", "r@r", null, "none", "", "whattt");
        assertNotNull(testCoach);

        TeamOwner testTeamOwner = (TeamOwner) preG.generate("o1", "94", "", "teamOwner", "ido katsan", "i@i", null, "", "", "");
        assertNotNull(testTeamOwner);

        Referee testReferee = (Referee) preG.generate("r1", "9", "", "referee", "tali me", "t@t", null, "what??", "", "");
        assertNotNull(testReferee);

        assertTrue((testReferee) instanceof Referee);
        assertTrue((testPlayer) instanceof Player);
        assertTrue((testTeamOwner) instanceof TeamOwner);
        assertTrue((testCoach) instanceof Coach);
        assertTrue((testManager) instanceof Manager);

    }

    @Test
    public void whichUserAmI() {
    }

    @Test
    public void askForApproval() {
    }
}