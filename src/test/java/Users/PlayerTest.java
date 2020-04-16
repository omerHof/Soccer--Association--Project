package Users;

import SystemLogic.DB;
import Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PlayerTest {
   Player p1;
   Player p2;
    Team t1;
    Team t2;
    DB dbtest;

    @Before
    public void setUp() throws Exception {
        p1 = new Player("cr7","777","cristiano ronaldo","cr7@gmail.com",null,"striker");
        p2 = new Player("neymarrrr","7757","neymar","nnn@gmail.com",null,"striker");
        t1=new Team("juventus");
        t2=new Team("psg");
        dbtest = DB.getInstance();
        dbtest.addTeam(t1);
        dbtest.addTeam(t2);
        dbtest.addUser(p1);
        dbtest.addUser(p2);
    }

    @After
    public void tearDown() throws Exception {
        dbtest.removeTeam(t1.getName());
        dbtest.removeTeam(t2.getName());
        dbtest.removeUser(p1.getUserName());
        dbtest.removeUser(p2.getUserName());
    }

    @Test
    public void createPersonalPage() {
        PersonalPage ronaldoPage = p1.createPersonalPage(187,85,7,t1);
        assertFalse(ronaldoPage.getCurrentTeam().getName()=="Manchester city");
        assertEquals(ronaldoPage.getName(),"cristiano ronaldo");
    }

    @Test
    public void getAge() {
    }

    @Test
    public void setAge() {
    }



    @Test
    public void setCourtRole() {
        assertEquals(p1.getCourtRole(),"striker");
        p1.setCourtRole("midelfer");
        assertFalse(p1.getCourtRole()=="striker");
    }

    @Test
    public void getCourtRole() {
        assertEquals(p1.getCourtRole(),"striker");
        p1.setCourtRole("midelfer");
        assertFalse(p1.getCourtRole()=="striker");
    }

    @Test
    public void getSalary() {
        assertEquals(0,p1.getSalary());
        assertEquals(p1.getSalary(),p2.getSalary());
    }

    @Test
    public void setSalary() {
        p1.setSalary(100000);
        assertFalse(p1.getSalary()==p2.getSalary());



    }


    @Test
    public void getPage() {
        //before crtating a page
        assertEquals(p1.getPage(),null);

        //after
        PersonalPage ronaldoPage = p1.createPersonalPage(187,85,7,t1);
        assertFalse(p1.getPage()==null);
        assertEquals(p1.getPage().getCurrentTeam(),t1);


    }

    @Test
    public void setCurrentTeam() {
        //before crtating a page
        assertEquals(p1.getCurrentTeam(),null);

        //after create a page
        PersonalPage ronaldoPage = p1.createPersonalPage(187,85,7,t1);
        assertEquals(p1.getCurrentTeam(),t1);
        p1.setCurrentTeam(t2);
        assertFalse(p1.getCurrentTeam().getName().equals("juventus"));
        assertEquals(p1.getCurrentTeam().getName(),t2.getName());

    }


}