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
        Team a= new Team("aa");
        Team b= new Team("bb");
        Team c= new Team("cc");
        Team d= new Team("dd");
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
