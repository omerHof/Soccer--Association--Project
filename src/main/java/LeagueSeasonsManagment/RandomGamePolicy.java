package LeagueSeasonsManagment;

import Games.Game;
import Teams.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/**
 * this class get list of teams and create a scheduling for a season
 */
public class RandomGamePolicy implements IGameInlayPolicy {

    private ArrayList<Team> ListTeam; // the initial list of teams
    private HashMap<Integer, ArrayList<Game>> listOfGames; // the results

    /**
     * constructor
     *
     * @param teams
     */
    public RandomGamePolicy(ArrayList<Team> teams) {
        this.ListTeam = teams;
        this.listOfGames = new HashMap<>();
    }


    /**
     * random round robin implementation
     * @param
     * @return
     */
    @Override
    public HashMap<Integer, ArrayList<Game>> gameInlayPolicyAlgoImplementation()
    {
        Collections.shuffle(ListTeam);
        IGameInlayPolicy policy= new SimpleGamePolicy(ListTeam);
        return policy.gameInlayPolicyAlgoImplementation();
    }
}
