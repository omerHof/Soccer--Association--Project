package LeagueSeasonsManagment;

import Games.Game;
import SystemLogic.DB;
import Teams.Team;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class get list of teams and create a scheduling for a season
 */
public class SimpleGamePolicy implements IGameInlayPolicy {

    private ArrayList<Team> ListTeam; // the initial list of teams
    private HashMap<Integer, ArrayList<Game>> listOfGames; // the results

    /**
     * constructor
     * @param teams
     */
    public SimpleGamePolicy(ArrayList<Team> teams) {
        this.ListTeam=teams;
        this.listOfGames= new HashMap<>();
    }

    /**
     * this function returns the games
     * @param
     */
    /*
    @Override
    public HashMap<Integer, ArrayList<Game>> gameInlayPolicyAlgoImplementation() {
        int rounds=(teams.size()-1)*2;
        Random rand = new Random();
        int counter=0;
        ArrayList<String> allMatches= new ArrayList<>();

        for(int i=1; i<=rounds; i++){
            ArrayList<Game> gamesInRound= new ArrayList<>();
            listOfGames.put(i,gamesInRound);
            ArrayList<Team> roundTeams=new ArrayList<>();
            for(Team t : teams) {
                roundTeams.add(t);
            }
            while(roundTeams.size()>1){
                int randomIndex = rand.nextInt(roundTeams.size());
                Team homeTeam = roundTeams.get(rand.nextInt(roundTeams.size()));
                Team awayTeam = roundTeams.get(rand.nextInt(roundTeams.size()));
                String match=homeTeam.getName()+awayTeam.getName();
                if(!homeTeam.equals(awayTeam) && !allMatches.contains(match)){
                    allMatches.add(match);
                    Game game=new Game(homeTeam,awayTeam);
                    listOfGames.get(i).add(game);
                    roundTeams.remove(homeTeam);
                    roundTeams.remove(awayTeam);
                    counter=0;
                }
                else{
                    counter++;
                }
                if(counter>50){
                    i=0;
                    listOfGames.clear();
                    counter=0;
                    break;
                }
            }
        }
        return listOfGames;
    }
*/

    /**
     * round robin implementation
     * @param
     * @return
     */
    @Override
    public HashMap<Integer, ArrayList<Game>> gameInlayPolicyAlgoImplementation() {

        if (ListTeam.size() % 2 != 0) //check odd number of teams
        {
            System.out.println("odd number of teams");
            return null;
        }

        int rounds = (ListTeam.size() - 1); // Days needed to complete tournament

        ArrayList<Team> teams = new ArrayList<>();

        for (Team t : ListTeam) { //copy the list of teams
            teams.add(t);
        }
        teams.remove(0);

        int teamsSize = teams.size();
        int start = 0;
        addGames(start, rounds, teams, false);
        addGames(teamsSize, teamsSize * 2, teams, true);

        return listOfGames;

    }

    /**
     * this function add rounds and games of half season
     * @param start- the initial round
     * @param rounds- the number of games each team playing in half season
     * @param teams- list of teams
     * @param secondSeason- if it's first/second season
     */
    private void addGames(int start, int rounds,ArrayList<Team> teams, boolean secondSeason)
    {
        int teamsSize=teams.size();
        for (int day = start; day < rounds; day++)
        {
            ArrayList<Game>games= new ArrayList<>();
            Team homeTeam;
            Team awayTeam;
            int teamIdx = day % teamsSize;
            if(!secondSeason) {
                homeTeam = teams.get(teamIdx);
                awayTeam = ListTeam.get(0);
            }
            else{
                homeTeam=ListTeam.get(0);
                awayTeam=teams.get(teamIdx);
            }
            Game game= new Game(homeTeam,awayTeam);
            games.add(game);

            for (int idx = 1; idx < ListTeam.size()/2; idx++)
            {
                int firstTeam = (day + idx) % teamsSize;
                int secondTeam = (day  + teamsSize - idx) % teamsSize;
                if(!secondSeason) {
                    homeTeam = teams.get(firstTeam);
                    awayTeam = teams.get(secondTeam);
                }
                else{
                    homeTeam = teams.get(secondTeam);
                    awayTeam = teams.get(firstTeam);
                }
                game= new Game(homeTeam,awayTeam);
                games.add(game);
            }
            listOfGames.put(day+1,games);
        }
    }
}
