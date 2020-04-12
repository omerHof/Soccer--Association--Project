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

public class OneRoundGamePolicyTest {
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
            IGameInlayPolicy policy = new OneRoundGamePolicy(teams);
            results = policy.gameInlayPolicyAlgoImplementation();
            assertEquals("test failed", teams.size()-1 ,results.size() );

            print();
        } catch (Exception e) {
            System.out.println("error");
        }

    }
    public void print(){
        IGameInlayPolicy policy = new OneRoundGamePolicy(teams);
        results = policy.gameInlayPolicyAlgoImplementation();
        Iterator it = results.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ArrayList<Game> games = (ArrayList) pair.getValue();
            for (Game g : games) {
                System.out.println(pair.getKey() + ":" + g.getHomeTeam().getName() + " vs " + g.getAwayTeam().getName());
            }

        }
    }
}
