package Users;

import Games.Event;
import Games.Game;
import LeagueSeasonsManagment.*;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Team;
import UserGenerator.IUserGenerator;
import UserGenerator.PremiumUserGenertator;
import UserGenerator.SimpleUserGenerator;

import java.sql.Ref;
import java.util.*;

public class AssociationRepresentative extends User implements Observer {

    private static int numOfApprovals = 0 ;
    private DB db = DB.getInstance();
    private List<Game> myGames;


    public AssociationRepresentative(String userName, String password, String fullName,String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
        myGames = new LinkedList<>();
    }

    public boolean approveRegistration(String fullName, String role){ //random (symbolic) function

        if (numOfApprovals < 9) {
            numOfApprovals++;
            return true;
        }

        // after 9 approvals.
        numOfApprovals=0; //begins from the start.
        return false;
    }

    ////////////////////////////// USE CASE 9.1 ////////////////////////////// VVV
    public boolean addLeague (String leagueName, int numOfTeams){

        League newLeague = new League(leagueName, numOfTeams);
        if (db.addLeague(newLeague)){ //this league does not exist yet. OK.
            MainSystem.LOG.info("new league: " + leagueName + " were added.");
            return true;
        }
        else
            return false;
    }

    ////////////////////////////// USE CASE 9.2 ////////////////////////////// VVV
    public void addSeasonToLeague (String leagueName, int year, String scorePolicy, String gamePolicy, List<String> teams, List<String> referees, List<String> representatives){

        ArrayList<Team> allTeams = new ArrayList<>();
        ArrayList<Referee> allReferees = new ArrayList<>();
        ArrayList<AssociationRepresentative> allReps = new ArrayList<>();

        setLeagueTeams(teams, allTeams);
        setLeagueReferees(referees, allReferees);
        setLeagueRepresentatives(representatives, allReps);

        Season newSeason = new Season(year, allTeams, allReferees, allReps, scorePolicy, gamePolicy);

        if (newSeason != null) {
            db.addSeason(leagueName, newSeason); //adds the season to DB.}
            MainSystem.LOG.info("new season: " + year + " was added to league: " + leagueName + ".");
        }

        else
            System.out.println("couldn't create a new season here.");
    }

    /**
     * adds all associationRepresentative to this season - help function
     * @param representatives - to add to
     * @param representativesNames - list of AssociationRepresentative's names
     */
    private void setLeagueRepresentatives(List<String> representativesNames, ArrayList<AssociationRepresentative> representatives) {

        if (representatives != null && representativesNames != null) {

            for (String representative : representativesNames) {
                AssociationRepresentative currRepresentative = (AssociationRepresentative) db.getUserByFullName(representative);
                representatives.add(currRepresentative);
            }
            //season.setAllRepresentatives(allRepresentatives);
            //MainSystem.LOG.info("Associations Representatives were added to league: " + leagueName + ", season: " + season.getYear());
        }

        else{
            ////////////////// display error ????????? ///////////////
        }
    }

    /**
     * adds all teams to this season - help function
     * @param teams - to add to
     * @param teamsNames - list of teams' names
     */
    private void setLeagueTeams(List<String> teamsNames, ArrayList<Team> teams) {

        if (teams != null && teamsNames!= null) {

            for (String team : teamsNames) {
                Team currTeam = db.getTeam(team);
                teams.add(currTeam);
            }
            //season.setAllTeams(allTeams);
            //MainSystem.LOG.info("Teams were added to league: " + leagueName + ", season: " + season.getYear());

        }
        else {
            //////////////////// display error ??????? /////////////
        }

    }

    ////////////////////////////// USE CASE 9.3.1 ////////////////////////////// VVV
    /**
     * return user from requested type
     * @param fullName - of the fan user we want to nominate as a referee.
     * @return true if success, false if not.
     */
    public boolean addReferee (String fullName){

        if(db.getUserByFullName(fullName)!= null) { //checks whether this referee already exists in the DB.


            Fan oldFan = (Fan)db.getUserByFullName(fullName); //gets referee itself
            PremiumUserGenertator premiumUserG = new PremiumUserGenertator();

            /////////////// change שדותתתת
            Referee newReferee = (Referee) premiumUserG.generate(oldFan.userName, oldFan.password, "onlyChangeStatus", "Referee", fullName,
                    oldFan.userEmail, null, "??????????????????", "", "");

            db.removeUser(oldFan.userName); //removes the fan
            db.addUser(newReferee); //adds the referee.

            ////////// send email ???????????????/ //////// todo: email.

            MainSystem.LOG.info("the fan: " + oldFan.getUserName() + " became a referee.");
            return true;
        }

        else {
            System.out.println("this user does not exist in the system.");
            return false;
        }
    }


    ////////////////////////////// USE CASE 9.3.2 ////////////////////////////// VVV
    public boolean removeReferee (String fullName){

        if( db.getUserByFullName(fullName) != null ) { //checks whether this referee already exists in the DB.

            Referee oldReferee = (Referee)db.getUserByFullName(fullName); //gets referee itself
            SimpleUserGenerator simpleUserG = new SimpleUserGenerator();

            Fan newFan = (Fan) simpleUserG.generate(oldReferee.userName, oldReferee.password, "", "", oldReferee.userFullName,
                    oldReferee.userEmail,  null, "", "", ""); //creates a new one.

            ////////// send email ???????????????/ //////// todo: email.

            db.removeUser(oldReferee.userName); //removes referee
            db.addUser(newFan); //adds as a fan.

            MainSystem.LOG.info("referee: " + newFan.userName + " was removed, and added as a simple fan.");
            return true;
        }

        else {
            System.out.println("Can not remove a referee that does not exist in the DB.");
            return false;
        }
    }

    ////////////////////////////// USE CASE 9.4 ////////////////////////////// VVV
    /**
     * adds all teams to this season - help function
     * @param referees - to add to
     * @param refereesNames - list of referees' names
     */
    public void setLeagueReferees (List<String> refereesNames, ArrayList<Referee> referees) {

        if (referees != null && refereesNames != null) {
            for (String referee : refereesNames) {
                Referee currReferee = (Referee) db.getUserByFullName(referee);
                referees.add(currReferee);
            }
            //season.setAllReferees(allReferees);
            //MainSystem.LOG.info("Referees were added to league: " + leagueName + ", season: " + season.getYear());

        }

        else{
            ////////////////// display error ????????? ///////////////
        }
    }

    ////////////////////////////// USE CASE 10.3 ////////////////////////////// VVV
    /**
     * this function adds an event to an active game's event book.
     * @return true if successes, false if not.
     */
    private boolean addGameEvent(Event.eventType type, int time, String playerName){

        Game gameToAdd = findActiveGame();

        if(gameToAdd!= null) { //there is an active game
            Event newEvent = new Event(type, time, playerName);
            gameToAdd.addEvent(newEvent);

            MainSystem.LOG.info("A new event: " + type + " was added to game: " + gameToAdd.getHomeTeam().getName() + "-" + gameToAdd.getAwayTeam().getName() + ", " + gameToAdd.getGameDate());
            return true;
        }
        else //no active game to add events to.
            return false;
    }

    /**
     * gets the only active game
     * @return Game
     */
    public Game findActiveGame(){

        for (Game game : myGames)
            if(game.getStatus().equals(Game.gameStatus.active))
                return game;

        return null; //no active game at the moment.
    }

    public void setMyGame(Game newGame) {
        myGames.add(newGame);
    }

    public boolean passMyGames (){

        int year = myGames.get(0).getTimeOfGame().getYear(); //my season's year. // just a random game.
        String myLeague = db.whatLeagueImAt(this, year);

        int i=0 ;
        boolean outOfAsso = false;
        List<User> allAssociations = db.getUserTypeList("AssociationRepresentative");
        AssociationRepresentative substituteAsso = (AssociationRepresentative)allAssociations.get(i); //first Asso'

        String substituteLeague = db.whatLeagueImAt(substituteAsso, year);

        while (!outOfAsso && myLeague.equals(substituteLeague)){ //keep getting random asso' till the leagues are not overlapping.
            i++;
            if (i == allAssociations.size()-1){ //the asso' list s over. no good asso' to pass to.
                outOfAsso = true; //last chance.
            }
            substituteAsso = (AssociationRepresentative)allAssociations.get(i); //next one in list.
            substituteLeague = db.whatLeagueImAt(substituteAsso, year); //gets his league's name.
        }

        //finally found a good substitute.
        for (Game game : myGames){
            substituteAsso.setMyGame(game); //transfer game by game.
        }
        return true;
    }



    @Override
    public void update(Observable o, Object arg) {

    }


    public void followThisGame(Game game){
        game.addObserver(this);
        myGames.add(game);
    }

}
