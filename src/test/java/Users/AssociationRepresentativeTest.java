package Users;

import SystemLogic.DB;
import SystemLogic.MainSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssociationRepresentativeTest {

    private DB dbTest;
    private AssociationRepresentative assoTest;
    private MainSystem mainSystem;

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
/*
        Referee r = new Referee("r1", "rr", "Ziv Adler","ziv@adler" , "Kavan");
        assertTrue("does nothing good. !", r.userFullName.equals("Ziv Adler"));

        r.setUserFullName("Tali");
        assertFalse(" UC 9.1 is good ! - name has been updated.", r.userFullName.equals("Ziv Adler"));
        assertTrue("UC 9.1 is good !",r.userFullName.equals("Tali"));

        assertTrue(" nothing happened.", r.getQualification().equals("Kavan"));
        r.setQualification("Nothing");
        assertFalse(" UC 9.1 is good ! - qualification has been updated.", r.getQualification().equals("Kavan"));
        assertTrue("UC 9.1 is good !",r.getQualification().equals("Nothing"));*/
    }

    @Test
    public void addSeasonToLeague() {
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