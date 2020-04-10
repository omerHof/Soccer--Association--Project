package LeagueSeasonsManagment;

import Games.Game;
import SystemLogic.DB;
import Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class SimpleGamePolicyTest {
    ArrayList<Team> teams=new ArrayList<>();
    HashMap<Integer, ArrayList<Game>> results= new HashMap<>();
    Team a= new Team("aa");
    Team b= new Team("bb");
    Team c= new Team("cc");
    Team d= new Team("dd");

    @Before
    public void setUp() throws Exception {
        teams.add(a);
        teams.add(b);
        teams.add(c);
        teams.add(d);
        DB.getInstance();
        //DB.setTeam(user);
    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void gameInlayPolicyAlgoImplementation() {
        try {
            IGameInlayPolicy policy = new SimpleGamePolicy(teams);
            results = policy.gameInlayPolicyAlgoImplementation();
        }
        catch (Exception e){
            System.out.println("error");
        }
    }

}