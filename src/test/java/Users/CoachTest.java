package Users;

import SystemLogic.DB;
import Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CoachTest {
    Coach c1;
    Coach c2;
    Coach c3;
    Team t1;
    Team t2;
    Team t3;
    DB dbtest;


    @Before
    public void setUp() throws Exception {
        dbtest=DB.getInstance();
         c1 = new Coach("pep","12345","pep guardiola","pep@gmail.com","Head coach");
        c2 = new Coach("klopp","12345","jorgen klopp","klopp@gmail.com","Head coach");
        c3 = new Coach("kling","12345","nir klinger","nk@gmail.com","Head coach");
        t1=new Team("Manchester city");
        t2=new Team("Liverpool");
        t3=new Team("Hapoel tel aviv");
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

    }

    @Test
    public void createPersonalPage() {
        PersonalPage pepPage = c1.createCoachPersonalPage(new Date(),t1);
        assertEquals(pepPage.getCurrentTeam().getName(),"Manchester city");
        assertEquals(pepPage.getName(),c1.getUserFullName());

        /*
        Date date = new Date();

        CoachPersonalPage page = new CoachPersonalPage("pep guardiola",date,"Head coach",t1 );
        c1.setPage(page);
        assertEquals(page.getCurrentTeam().getName(),"Manchester city");

         */

    }

    @Test
    public void getUserName() {

    }

    @Test
    public void setUserName() {

        c1.setUserName("pepo");
        assertEquals(c1.getUserName(),"pepo");
        /*
        c2.userName="jk1";
        assertEquals(c1.getUserName(),"jk1");

         */

    }

    @Test
    public void getPassword() {
    }

    @Test
    public void setPassword() {

    }

    @Test
    public void getFullName() {
    }

    @Test
    public void setFullName() {
    }

    @Test
    public void getQualification() {
    }

    @Test
    public void setQualification() {
    }

    @Test
    public void getTeamRole() {
    }

    @Test
    public void setTeamRole() {

    }

    @Test
    public void getSalary() {
    }

    @Test
    public void setSalary() {
    }
    @Test
    public void setPage() {
    }

    @Test
    public void getPage() {
    }

    @Test
    public void setCurrentTeam() {

    }

    @Test
    public void setTeamToHistoryOfTeams() {
    }
}