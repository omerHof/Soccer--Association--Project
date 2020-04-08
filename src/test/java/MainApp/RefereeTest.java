package MainApp;

import Games.Game;
import LeagueSeasonsManagment.IGameInlayPolicy;
import LeagueSeasonsManagment.SimpleGamePolicy;
import Teams.Team;
import Users.Referee;

import java.io.IOException;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.HashMap;

public class RefereeTest {

    public static void main(String[] args) throws IOException {

        ArrayList<Team> teams=new ArrayList<>();
        HashMap<Integer, ArrayList<Game>> results= new HashMap<>();

        Referee r = new Referee("r1", "rr", "Ziv Adler", "Kavan");
        UC_10_1(r) ;




        try {
            IGameInlayPolicy policy = new SimpleGamePolicy(teams);
            results = policy.gameInlayPolicyAlgoImplementation();
        }
        catch (Exception e){
            System.out.println("error");
        }
    }

    public static void UC_10_1 (Referee r){
        r.updatePersonalDetails("Tali", "main");
        r.updatePersonalDetails("", "main");
        r.updatePersonalDetails("Tali", "");
        r.updatePersonalDetails("", "");
    }

}
