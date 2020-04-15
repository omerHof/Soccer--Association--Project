package LeagueSeasonsManagment;

import Games.Game;
import Teams.Team;
import Users.AssociationRepresentative;
import Users.Referee;
import Users.User;

import java.util.*;

public class Season {

    private int year;
    private HashMap<Integer, ArrayList<Game>> allGames;
    private ArrayList<Team> allTeams;
    private ArrayList<Referee> allReferees;
    private ArrayList<AssociationRepresentative> allRepresentatives;
    private IGameInlayPolicy iGameInlayPolicy;
    private IScorePolicy iScorePolicy;
    private SeasonScoreBoard seasonScoreBoard;

    public Season(int year, ArrayList<Team> allTeams, ArrayList<Referee> allReferees,
                  ArrayList<AssociationRepresentative> allReps, String scorePolicy, String gamePolicy) {

        this.year = year;
        this.allReferees = allReferees;
        this.allTeams = allTeams;
        this.allRepresentatives = allReps;

        setiGameInlayPolicy(gamePolicy);
        setIScorePolicy(scorePolicy);
        this.seasonScoreBoard= new SeasonScoreBoard(allTeams,iScorePolicy);

        IGameInlayPolicy iGameInlayPolicy = getiGameInlayPolicy();

        this.allGames = iGameInlayPolicy.gameInlayPolicyAlgoImplementation(); //adds list of games to each mahzor.

        assignUsersToGames(allReferees, null, 2); //assign 2 referees for each game
        assignUsersToGames(null, allReps, 1); //assign 1 representative for each game.

    }


    /**
     * assign referees or association representatives to all games
     * @param amount - how many referees to assign for each game.
     */
    private void assignUsersToGames(ArrayList<Referee> allReferees, ArrayList<AssociationRepresentative> allReps, int amount) {

        ArrayList<Game> currMahzorGames = new ArrayList<>();

        for (int mahzor : allGames.keySet()){ //every weak.
            currMahzorGames = allGames.get(mahzor); //gets current mahzor's games.

            List<AssociationRepresentative> copyAsso = new LinkedList<>(allReps); // WORK ????????? copy.!! doesn't change originalll
            Referee ref;

            List<Referee> copyReferees = new ArrayList<>(allReferees); // WORK ????????? copy.!! doesn't change originalll
            List<Referee> gameReferees = new LinkedList<>(); //to add current random referees to.

            //ArrayList<Referee> copyReferees = new ArrayList<>();
            //copyReferees.addAll(allReferees);

            for (Game game : currMahzorGames){
                if (allReferees != null && allReps != null) {
                    for (int i=0; i < amount; i++){

                        ref = (Referee)getRandomReferee(copyReferees);
                        gameReferees.add(ref); //maybe not wor. doresss.
                        /////// gameReferees.add(new Referee(ref.getUserName(), ref.getPassword(), ref.getUserFullName(), ref.getUserEmail(), ref.getQualification())); //assign the random referee ((copy)) to the game.
                        ref.addGame(game); // and the opposite...
                    }
                    AssociationRepresentative asso = (AssociationRepresentative)getRandomAsso(allReps);
                    game.setAssociationRepresentative(asso); //assign asso to the game
                    asso.setMyGame(game); //adds the game to asso' list og games.

                    game.setGameReferees(gameReferees); //adds the referees' list to the current game.
                }
/*
                else if (allReferees != null){ //assign only referees.  //// even possible ???????
                    for (int i=0; i < amount; i++){

                        ref = (Referee)getRandomReferee(copyReferees);
                        gameReferees.add(ref); //maybe not wor. doresss.
                        /////// gameReferees.add(new Referee(ref.getUserName(), ref.getPassword(), ref.getUserFullName(), ref.getUserEmail(), ref.getQualification())); //assign the random referee ((copy)) to the game.
                        ref.addGame(game); // and the opposite...
                    }
                    game.setGameReferees(gameReferees); //adds the referees' list to the current game.
                }
                else if (allReps != null) { //assign only reps. //// even possible ???????

                    AssociationRepresentative asso = (AssociationRepresentative)getRandomAsso(allReps);
                    game.setAssociationRepresentative(asso); //assign asso to the game
                    asso.setMyGame(game); //adds the game to asso' list og games.
                }*/

                else //no option.
                    System.out.println(" cannot create a new season without referees nor association.");

            }

        }


    }

    /**
     * this function returns a random referee.
     * @param copyList - a copy of the Referees' list of the season.
     * @return Referee - to assign for a game.
     */
    private User getRandomReferee(List<Referee> copyList) {

        Random random = new Random();
        int rand = random.nextInt(copyList.size()+1);

        return copyList.remove(rand);
     }

    /**
     * this function returns a random AssociationRepresentative.
     * @param copyList - a copy of the AssociationRepresentative' list of the season.
     * @return AssociationRepresentative - to assign for a game.
     */
    private User getRandomAsso(List<AssociationRepresentative> copyList) {

        Random random = new Random();
        int rand = random.nextInt(copyList.size()+1);

        return copyList.get(rand);
    }

    //getters
    public int getYear() {
        return year;
    }


    public IGameInlayPolicy getiGameInlayPolicy() {
        return iGameInlayPolicy;
    }

    public IScorePolicy getiScorePolicy() {
        return iScorePolicy;
    }

    //setters
    public void setYear(int newYear){
        this.year=newYear;
    }


    public void setAllTeams(ArrayList<Team> allTeams) {
        this.allTeams = allTeams;
    }

    public void setAllRepresentatives(ArrayList<AssociationRepresentative> allRepresentatives) {
        this.allRepresentatives = allRepresentatives;
    }

    public HashMap<Integer, ArrayList<Game>> getAllGames() {
        return allGames;
    }

    public void setAllGames(HashMap<Integer, ArrayList<Game>> allGames) {
        this.allGames = allGames;
    }

    /*
    public void GameInlayPolicyAlgoImplementation(){
        //////to complete
    }

     */

    public void setiGameInlayPolicy(String iGameInlayPolicy) {
        switch(iGameInlayPolicy) {
            case "TwoRoundsGamePolicy":
                this.iGameInlayPolicy = new TwoRoundsGamePolicy(this.allTeams,getYear());
                break;
            case "OtherGamePolicy":
                // code block
                break;
            default:
                this.iGameInlayPolicy = new TwoRoundsGamePolicy(this.allTeams,getYear());
        }
    }

    public void setAllReferees(ArrayList<Referee> allReferees) {
        this.allReferees = allReferees;
    }

    public ArrayList<Team> getAllTeams() {
        return allTeams;
    }

    public List<Referee> getAllReferees() {
        return allReferees;
    }

    public List<AssociationRepresentative> getAllRepresentatives() {
        return allRepresentatives;
    }

    public void setIScorePolicy(String iScorePolicy) {

        switch(iScorePolicy) {
            case "RegularScorePolicy":
                this.iScorePolicy = new RegularScorePolicy();
                break;
            case "OtherScorePolicy":
                // code block
                break;
            default:
                this.iScorePolicy = new RegularScorePolicy();
        }
    }

    public SeasonScoreBoard getSeasonScoreBoard() {
        return seasonScoreBoard;
    }

    public void setSeasonScoreBoard(SeasonScoreBoard seasonScoreBoard) {
        this.seasonScoreBoard = seasonScoreBoard;
    }
}
