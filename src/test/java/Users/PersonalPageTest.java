package Users;

import SystemLogic.DB;
import Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;

public class PersonalPageTest {

    Coach c1;
    Coach c2;
    Coach c3;
    Team t1;
    Team t2;
    Team t3;
    DB dbtest;
    PersonalPage pepPage;

    @Before
    public void setUp() throws Exception {
        dbtest= DB.getInstance();
        c1 = new Coach("pep","12345","pep guardiola","pep@gmail.com","Head coach");
        c2 = new Coach("klopp","12345","jorgen klopp","klopp@gmail.com","Head coach");
        c3 = new Coach("kling","12345","nir klinger","nk@gmail.com","Head coach");
        t1=new Team("Manchester city");
        t2=new Team("Liverpool");
        t3=new Team("Hapoel tel aviv");
        LocalDate localDate2 = LocalDate.of(1999,1,1);

        pepPage = c1.createCoachPersonalPage(localDate2,t1);



        dbtest.addTeam(t1);
        dbtest.addTeam(t2);
        dbtest.addTeam(t3);
        dbtest.addUser(c1);
        dbtest.addUser(c2);
        dbtest.addUser(c3);
    }

    @After
    public void tearDown() throws Exception {
        dbtest.removeUser(c1.getUserName());
        dbtest.removeUser(c2.getUserName());
        dbtest.removeUser(c3.getUserName());
        dbtest.removeTeam(t1.getName());
        dbtest.removeTeam(t2.getName());
        dbtest.removeTeam(t3.getName());
        pepPage=null;
    }

    @Test
    public void getName() {
        assertEquals(pepPage.getName(),c1.getUserFullName());

    }

    @Test
    public void getCurrentTeam() {
        assertEquals(pepPage.getCurrentTeam(),t1);

    }

    @Test
    public void setName() {
        assertEquals(pepPage.getName(),c1.getUserFullName());

        //after change
        pepPage.setName("Peps");
        assertFalse(pepPage.getName().equals("pep guardiola"));
    }

    @Test
    public void setCurrentTeam() {
        pepPage.setCurrentTeam(t2);
        assertFalse(pepPage.getCurrentTeam().equals(t1));
    }

    @Test
    public void getAge() {
    }

    @Test
    public void setAge() {
    }

    @Test
    public void getTeamHistory() {
    }

    @Test
    public void setTeamHistory() {
    }

    @Test
    public void getAge1() {
    }
}