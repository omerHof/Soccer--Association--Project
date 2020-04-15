package LeagueSeasonsManagment;

import Games.Game;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Team;
import Users.AssociationRepresentative;
import Users.MainReferee;
import Users.Referee;
import Users.User;

import java.sql.Ref;
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
    private DB db;

    public Season(int year, ArrayList<Team> allTeams, ArrayList<Referee> allReferees,
                  ArrayList<AssociationRepresentative> allReps, String scorePolicy, String gamePolicy) {

        db = DB.getInstance();
        this.year = year;
        this.allReferees = allReferees;
        this.allTeams = allTeams;
        this.allRepresentatives = allReps;

        setiGameInlayPolicy(gamePolicy);
        setIScorePolicy(scorePolicy);

        //IGameInlayPolicy iGameInlayPolicy = getiGameInlayPolicy();

        this.allGames = iGameInlayPolicy.gameInlayPolicyAlgoImplementation(); //adds list of games to each mahzor.

        assignUsersToGames(3); //assign 4 referees for each game, and 1 association rep.
        this.seasonScoreBoard= new SeasonScoreBoard(allTeams,iScorePolicy,allGames.get(1).get(0).getTimeOfGame(),allGames.size());


    }


    /**
     * assign referees and association representatives to all games
     * @param amount - how many standard referees to assign for each game.
     */
    private void assignUsersToGames(int amount) {

        if (allReferees != null && allRepresentatives != null) {

            for (int mahzor : allGames.keySet()) { //every week.
                ArrayList<Game> currMahzorGames = allGames.get(mahzor); //gets current mahzor's games.

                List<User> mahzorReferees = new LinkedList<>(); //to check constraints and doubles.
                Referee ref;

                //ArrayList<Referee> copyReferees = new ArrayList<>();
                //copyReferees.addAll(allReferees);

                for (Game game : currMahzorGames) {

                    for (int i = 0; i < amount; i++) {

                        ref = getRandomReferee();

                        if (!mahzorReferees.contains(ref)){ //doesn't contain this referee already
                            ref.followThisGame(game); //adds both ways.
                            mahzorReferees.add(ref);
                        }

                        else { //try finding another referee
                            i--;
                            continue;
                        }
                    }

                    AssociationRepresentative asso = getRandomAsso();
                    while (mahzorReferees.contains(asso)) { //until gets a new one.
                        asso = getRandomAsso();
                    }
                    asso.followThisGame(game); //adds both ways.
                    mahzorReferees.add(asso);

                    MainReferee mainReferee = (MainReferee) db.getUserType("MainReferee");
                    while (mahzorReferees.contains(mainReferee)) { //until gets a new one.
                        mainReferee = (MainReferee) db.getUserType("MainReferee");
                    }
                    mainReferee.followThisGame(game); //adds both ways.
                    mahzorReferees.add(mainReferee);

                }
            }
        }
        else //no option.
            System.out.println(" cannot create a new season without referees nor association.");
    }

    /**
     * this function returns a random referee.
     * @return Referee - to assign for a game.
     */
    private Referee getRandomReferee() {

        Random random = new Random();
        int rand = random.nextInt(allReferees.size());

        return allReferees.get(rand);
     }

    /**
     * this function returns a random AssociationRepresentative.
     * @return AssociationRepresentative - to assign for a game.
     */
    private AssociationRepresentative getRandomAsso() {

        Random random = new Random();
        int rand = random.nextInt(allRepresentatives.size());

        return allRepresentatives.get(rand);
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
