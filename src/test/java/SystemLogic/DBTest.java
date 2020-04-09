package SystemLogic;

import Users.Administrator;
import Users.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBTest {

    @Before
    public void setUp() throws Exception {
        User user= new Administrator("a","b"," a c");
        DB.getResultsInstance();
        DB.setUser(user);
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
            DB.getUser("a");
            java.lang.System.out.println("success");
        }catch (Exception e){
            java.lang.System.out.println("wrong");
        }
    }

    @Test
    public void setUser() {
    }

    @Test
    public void addUser() {
    }

    @Test
    public void removeUser() {
    }

    @Test
    public void getLeague() {
    }

    @Test
    public void setLeague() {
    }

    @Test
    public void addLeague() {
    }

    @Test
    public void removeLeague() {
    }

    @Test
    public void getTeam() {
    }

    @Test
    public void setTeam() {
    }

    @Test
    public void addTeam() {
    }

    @Test
    public void removeTeam() {
    }

    @Test
    public void getUserType() {
        try {
            User result=DB.getUserType("Administrator");
            java.lang.System.out.println("success");
        }
        catch (Exception e){
            java.lang.System.out.println("wrong");
        }
    }
}