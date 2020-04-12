package LeagueSeasonsManagment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SimpleScorePolicyTest {

    IScorePolicy policy;
    @Before
    public void setUp() throws Exception {

        policy= new SimpleScorePolicy();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void scorePolicyAlgoImplementation() {
        try {
            policy.scorePolicyAlgoImplementation();
            assertEquals("same number", 3, policy.getWin());
            assertEquals("same number", 1, policy.getDraw());
            assertEquals("same number", 0, policy.getLost());

        } catch (Exception e) {
            System.out.println("error");
        }
    }


    @Test
    public void priority() {
        try {
            policy.scorePolicyAlgoImplementation();
            ArrayList<String> priority = policy.priority("points", "moreGoals","goalDiff");
            assertEquals("same priority", "points",priority.get(0));
            assertEquals("same priority", "goalDiff",priority.get(1));
            assertEquals("same priority", "moreGoals",priority.get(2));

        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getWin() {
        try {
            policy.scorePolicyAlgoImplementation();
            assertEquals("same number", 3,policy.getWin());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getDraw() {
        try {
            policy.scorePolicyAlgoImplementation();
            assertEquals("same number", 1,policy.getDraw());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getLost() {
        try {
            policy.scorePolicyAlgoImplementation();
            assertEquals("same number", 0,policy.getLost());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void getName() {
        try {
            assertEquals("same name", "SimpleScorePolicy", policy.getName());
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}