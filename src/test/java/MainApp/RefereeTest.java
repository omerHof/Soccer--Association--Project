package MainApp;

import Games.Game;
import LeagueSeasonsManagment.IGameInlayPolicy;
import LeagueSeasonsManagment.SimpleGamePolicy;
import Teams.Team;
import Users.Referee;

import java.io.IOException;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class RefereeTest {

    public static void main(String[] args) throws IOException {

        ArrayList<Team> teams=new ArrayList<>();
        HashMap<Integer, ArrayList<Game>> results= new HashMap<>();

        Referee r = new Referee("r1", "rr", "Ziv Adler", "Kavan");
        //UC_10_1(r) ;

        /////////////////////////////////////////////////////////////////////////////////

        Team a= new Team("Maccabi Tel Aviv");
        Team b= new Team("Maccabi Haifa");
        Team c= new Team("Beitar");
        Team d= new Team("Hapoel Beer Sheva");
        teams.add(a);
        teams.add(b);
        teams.add(c);
        teams.add(d);

        Date d1 = new Date(120, 4, 9, 21, 10);
        Date d2 = new Date(120, 4, 11, 21, 30);
        Date d3 = new Date(120, 5, 18, 14, 20);

        Date d4 = new Date();
        d4.setYear(120);
        d4.setMonth(4);
        d4.setDate(9);
        d4.setHours(21);

        //Date d4 = new Date(2020, 5, 24);
       // Date d5 = new Date(2020, 7, 29);

        Game g1 = new Game(a, b);
        Game g2 = new Game(a, c);
        Game g3 = new Game(b, d);

        g1.setGameDate(d1);
        g1.setGameHour(d1);
        g2.setGameDate(d2);
        g2.setGameHour(d2);
        g3.setGameDate(d3);
        g3.setGameHour(d3);

        r.addGame(g1);
        r.addGame(g3);
        r.addGame(g2);

        UC_10_2(r) ;

        /////////////////////////////////////////////////////////////////////////////////


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

    public static void UC_10_2 (Referee r){
        r.watchGamesList();
    }

}
