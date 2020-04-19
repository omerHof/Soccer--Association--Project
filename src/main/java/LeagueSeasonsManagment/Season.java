package LeagueSeasonsManagment;

import Games.Game;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Team;
import Users.AssociationRepresentative;
import Users.MainReferee;
import Users.Referee;
import Users.User;

import java.awt.*;
import java.sql.Ref;
import java.util.*;
import java.util.List;

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

        assignUsersToGames(3); //assign 3 referees for each game, 1 main referee, and 1 association rep.
        this.seasonScoreBoard = new SeasonScoreBoard(allTeams, iScorePolicy, allGames.get(1).get(0).getTimeOfGame(), allGames.size());

    }

    /**
     * assign referees and association representatives to all games
     *
     * @param amount - how many standard referees to assign for each game.
     */
    public void assignUsersToGames(int amount) {

        if (allReferees != null && allRepresentatives != null) {

            for (int mahzor : allGames.keySet()) { //every week.
                ArrayList<Game> currMahzorGames = allGames.get(mahzor); //gets current mahzor's games.

                List<User> mahzorRefereesAndAsso = new LinkedList<>(); //to check constraints and doubles.
                Referee ref;

                for (Game game : currMahzorGames) {

                    for (int i = 0; i < amount; i++) {
                        ref = getRandomReferee();

                        while (mahzorRefereesAndAsso.contains(ref) || ref instanceof MainReferee) { // contains this referee already
                            ref = getRandomReferee(); //gets another one
                        }

                        ref.followThisGame(game); //adds both ways.
                        mahzorRefereesAndAsso.add(ref);
                    }

                    AssociationRepresentative asso = getRandomAsso();
                    while (mahzorRefereesAndAsso.contains(asso)) { //until gets a new one.
                        asso = getRandomAsso();
                    }
                    asso.followThisGame(game); //adds both ways.
                    mahzorRefereesAndAsso.add(asso);

                    MainReferee mainReferee = getRandomMainReferee();

                    while (mainReferee == null || mahzorRefereesAndAsso.contains(mainReferee)) { //until gets a new one.
                        mainReferee = getRandomMainReferee();
                    }
                    mainReferee.followThisGame(game); //adds both ways.
                    mahzorRefereesAndAsso.add(mainReferee);

                    //////////////////////////////////// set teams' games: /////////////////////////////////
                    Team away = game.getAwayTeam();
                    Team home = game.getHomeTeam();

                    away.addGame(game);
                    home.addGame(game);
                }
            }
        } else //no option.
            System.out.println(" cannot create a new season without referees nor association.");
    }

    /**
     * this function returns a random referee.
     *
     * @return Referee - to assign for a game.
     */
    private Referee getRandomReferee() {

        Random random = new Random();
        int rand = random.nextInt(allReferees.size());

        return allReferees.get(rand);
    }

    /**
     * this function returns a random AssociationRepresentative.
     *
     * @return AssociationRepresentative - to assign for a game.
     */
    private AssociationRepresentative getRandomAsso() {

        Random random = new Random();
        int rand = random.nextInt(allRepresentatives.size());

        return allRepresentatives.get(rand);
    }

    private MainReferee getRandomMainReferee() {

        Random random = new Random();
        int rand = random.nextInt(allReferees.size());

        Referee toReturn = allReferees.get(rand);
        if (toReturn instanceof MainReferee)
            return (MainReferee) toReturn;

        return null;
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
            case "OneRoundGamePolicy":
                this.iGameInlayPolicy = new OneRoundGamePolicy(this.allTeams, getYear());
                break;
            case "RandomTwoRoundsGamePolicy":
                this.iGameInlayPolicy = new RandomTwoRoundsGamePolicy(this.allTeams, getYear());
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

    public ArrayList<AssociationRepresentative> getAllRepresentatives() {
        return allRepresentatives;
    }

    public void setIScorePolicy(String iScorePolicy) {

        switch(iScorePolicy) {
            case "RegularScorePolicy":
                this.iScorePolicy = new RegularScorePolicy();
                break;
            case "GoalScorePolicy":
                this.iScorePolicy = new GoalScorePolicy();
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
