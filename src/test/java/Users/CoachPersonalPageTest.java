package Users;

import SystemLogic.DB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CoachPersonalPageTest {
    Coach c1;
    DB db = DB.getInstance();
    CoachPersonalPage coachPage;

    @Before
    public void setUp() throws Exception {
        c1 = new Coach("klopp7","1223","jorgen klopp","jor@gmail.com","head coach");
        db.addUser(c1);
        LocalDate date = LocalDate.of(1970,1,1);
       coachPage= c1.createCoachPersonalPage(date,"liverpool");
    }

    @After
    public void tearDown() throws Exception {
        db.removeUser(c1.getUserName());
        c1=null;

    }

    @Test
    public void getTeamRole() {
       assertEquals(c1.getTeamRole(),coachPage.getTeamRole());
    }

    @Test
    public void setTeamRole() {
        c1.setTeamRole("Manager");
        assertEquals(c1.getTeamRole(),coachPage.getTeamRole());
    }
}