package LeagueSeasonsManagment;

import Games.Game;
import SystemLogic.DB;
import Teams.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SimpleGamePolicy implements IGameInlayPolicy {

    private ArrayList<Team> teams;
    private HashMap<Integer, ArrayList<Game>> listOfGames;

    /**
     * constructor
     * @param teams
     */
    public SimpleGamePolicy(ArrayList<Team> teams) {
        this.teams=teams;
        this.listOfGames= new HashMap<>();
    }

    /**
     * this function returns the games
     * @param
     */
    @Override
    public HashMap<Integer, ArrayList<Game>> gameInlayPolicyAlgoImplementation() {
        int rounds=(teams.size()-1)*2;
        Random rand = new Random();
        ArrayList<String> allMatches= new ArrayList<>();

        for(int i=0; i<rounds; i++){
            ArrayList<Team> roundTeams=teams;

            while(roundTeams.size()>1){
                int randomIndex = rand.nextInt(roundTeams.size());
                Team homeTeam = roundTeams.get(randomIndex);
                Team awayTeam = roundTeams.get(randomIndex);
                String match=homeTeam.toString()+awayTeam.toString();
                if(!homeTeam.equals(awayTeam) && !allMatches.contains(match)){
                    allMatches.add(match);
                    Game game=new Game(homeTeam,awayTeam);
                    listOfGames.get(i).add(game);
                    roundTeams.remove(homeTeam);
                    roundTeams.remove(awayTeam);
                }
            }
        }
        return listOfGames;


    }
}
