package LeagueSeasonsManagment;

import Games.Game;
import SystemLogic.DB;
import Teams.Team;

import java.util.*;

/**
     * this class get list of teams and create a scheduling for a season
     */
    public class OneRoundGamePolicy implements IGameInlayPolicy {

        private String name;
        private ArrayList<Team> ListTeam; // the initial list of teams
        private HashMap<Integer, ArrayList<Game>> tempListOfGames; // the results
        private HashMap<Integer, ArrayList<Game>> listOfGames; // the results

        /**
         * constructor
         *
         * @param teams
         */
        public OneRoundGamePolicy(ArrayList<Team> teams) {
            this.name = "OneRoundGamePolicy";
            this.ListTeam = teams;
            this.listOfGames = new HashMap<>();
            this.tempListOfGames = new HashMap<>();

        }

        @Override
        public String getName(){
            return name;
        }

        /**
         * round robin implementation
         *
         * @param
         * @return
         */
        @Override
        public HashMap<Integer, ArrayList<Game>> gameInlayPolicyAlgoImplementation() {
            Collections.shuffle(ListTeam);
            IGameInlayPolicy policy = new SimpleGamePolicy(ListTeam);
            tempListOfGames = policy.gameInlayPolicyAlgoImplementation();
            Iterator it = tempListOfGames.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if((int)pair.getKey()<ListTeam.size()){
                    listOfGames.put((int)pair.getKey(),(ArrayList<Game>)pair.getValue());
                }

            }
            return listOfGames;
        }
    }



