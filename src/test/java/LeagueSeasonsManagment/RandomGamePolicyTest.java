package LeagueSeasonsManagment;

import Games.Game;
import SystemLogic.DB;
import Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

public class RandomGamePolicyTest {

    ArrayList<Team> teams = new ArrayList<>();
    HashMap<Integer, ArrayList<Game>> results = new HashMap<>();
    Team a = new Team("barca");
    Team b = new Team("real");
    Team c = new Team("man u");
    Team d = new Team("man city");
    Team e = new Team("liverpool");
    Team f = new Team("chelsea");
    Team g = new Team("roma");
    Team h = new Team("juve");
    Team i = new Team("milan");
    Team j = new Team("inter");

    @Before
    public void setUp() throws Exception {
        teams.add(a);
        teams.add(b);
        teams.add(c);
        teams.add(d);
        teams.add(e);
        teams.add(f);
        teams.add(g);
        teams.add(h);
        teams.add(i);
        teams.add(j);
        DB.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void gameInlayPolicyAlgoImplementation() {
        try {
            IGameInlayPolicy policy = new SimpleGamePolicy(teams);
            results = policy.gameInlayPolicyAlgoImplementation();
            assertEquals("test failed",results.size(),teams.size()*2-2);

        } catch (Exception e) {
            System.out.println("error");
        }

    }
}