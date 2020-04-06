package MainApp;

import Games.Game;
import LeagueSeasonsManagment.IGameInlayPolicy;
import LeagueSeasonsManagment.SimpleGamePolicy;
import Teams.Team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class simpleGamePolicyTest {

    public static void main(String[] args) throws IOException {
        ArrayList<Team>teams=new ArrayList<>();
        HashMap<Integer, ArrayList<Game>> results= new HashMap<>();
        Team a= new Team();
        Team b= new Team();
        Team c= new Team();
        Team d= new Team();
        teams.add(a);
        teams.add(b);
        teams.add(c);
        teams.add(d);
        try {
            IGameInlayPolicy policy = new SimpleGamePolicy(teams);
            results = policy.gameInlayPolicyAlgoImplementation();
        }
        catch (Exception e){
            System.out.println("error");
        }
        }
}
