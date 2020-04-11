package Users;

import LeagueSeasonsManagment.*;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Team;
import UserGenerator.IUserGenerator;
import UserGenerator.PremiumUserGenertator;
import UserGenerator.SimpleUserGenerator;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class AssociationRepresentative extends User {

    private static int numOfApprovals = 0 ;
    private DB db = DB.getInstance();

    public AssociationRepresentative(String userName, String password, String fullName,String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
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

    ////////////////////////////// USE CASE 9.1 //////////////////////////////
    public boolean addLeague (String leagueName, int numOfTeams){

        League newLeague = new League(leagueName, numOfTeams);
        if (db.addLeague(newLeague)){ //this league does not exist yet. OK.
            MainSystem.LOG.info("new league: " + leagueName + " were added.");
            return true;
        }
        else
            return false;
    }

    ////////////////////////////// USE CASE 9.2 //////////////////////////////
    public void addSeasonToLeague (String leagueName, int year, String scorePolicy, String gamePolicy, List<String> teams, List<String> referees, List<String> representatives){

        Season newSeason = new Season(year);

        setLeagueTeams(newSeason, teams); // שיבוץ קבוצות מתוך הרשימה שהתקבלה
        setLeagueReferees(newSeason, referees); //  שיבוץ שופטים מתוך רשימת השמות שהתקבלה
        setLeagueRepresentatives(newSeason, representatives); // שיבוץ נציגי התאחדות אחראיים על עדכונים ממשחקים

        newSeason.setIScorePolicy(scorePolicy); //בחירת מדיניות חישוב תוצאות
        newSeason.setiGameInlayPolicy(gamePolicy); // בחירת מדיניות שיבוץ משחקים

        IGameInlayPolicy iGameInlayPolicy = newSeason.getiGameInlayPolicy();

        //הפעלת שיבוץ משחקים- מקבל את הקבוצות אז חייב להיקרא אחרי שיבוץ הקבוצות לעונה!!
        iGameInlayPolicy.gameInlayPolicyAlgoImplementation();

        db.addSeason(leagueName, newSeason); //adds the season to DB.

        MainSystem.LOG.info("new season: " + year + " was added to league: " + leagueName + ".");
    }

    private void setLeagueRepresentatives(Season season, List<String> representatives) {

        if (representatives != null) {
            ArrayList<AssociationRepresentative> allRepresentatives = new ArrayList<>();

            for (String representative : representatives) {
                AssociationRepresentative currRepresentative = (AssociationRepresentative) db.getUserByFullName(representative);
                allRepresentatives.add(currRepresentative);
            }
            season.setAllRepresentatives(allRepresentatives);
        }

        else{
            ////////////////// display error ????????? ///////////////
        }

        for (String representative : representatives){
            AssociationRepresentative currRepresentative = (AssociationRepresentative) db.getUser(representative);
            db.setUser(currRepresentative);
        }
    }

    /**
     * adds all teams to this league - help function
     * @param season - to add to
     * @param teams - list of teams' names
     */
    private void setLeagueTeams(Season season, List<String> teams) {

        ArrayList<Team> allTeams = new ArrayList<>();

        if (teams != null) {

            for (String team : teams) {
                Team currTeam = db.getTeam(team);
                allTeams.add(currTeam);
            }
            season.setAllTeams(allTeams);
        }
        else {
            //////////////////// display error ??????? /////////////
        }

    }

    ////////////////////////////// USE CASE 9.3.1 //////////////////////////////
    public void addReferee (String fullName){

        if(db.userExist(fullName)) { //checks whether this referee already exists in the DB.

            Fan oldFan = (Fan)db.getUserByFullName(fullName); //gets referee itself
            PremiumUserGenertator premiumUserG = new PremiumUserGenertator();

            /////////////// change שדותתתת
            Referee newReferee = (Referee) premiumUserG.generate(oldFan.userName, oldFan.password, "", "", fullName,
                    oldFan.userEmail, "", "??????????????????", "", "");

            db.removeUser(oldFan.userName); //removes the fan
            db.addUser(newReferee); //adds the referee.

            ////////// send email ???????????????/ //////// todo: email.

            MainSystem.LOG.info("the fan: " + fullName + " became a referee.");
        }

        else
            System.out.println("user already exists.");
    }


    ////////////////////////////// USE CASE 9.3.2 //////////////////////////////
    public void removeReferee (String username){

        if(db.userExist(username)) { //checks whether this referee already exists in the DB.

            Referee oldReferee = (Referee)db.getUser(username); //gets referee itself
            SimpleUserGenerator simpleUserG = new SimpleUserGenerator();

            Fan newFan = (Fan) simpleUserG.generate(oldReferee.userName, oldReferee.password, "", "", oldReferee.userFullName,
                    oldReferee.userEmail, "", "", "", ""); //creates a new one.

            ////////// send email ???????????????/ //////// todo: email.

            db.removeUser(oldReferee.userFullName); //removes referee
            db.addUser(newFan); //adds as a fan.

            MainSystem.LOG.info("referee: " + newFan.userName + " was removed, and added as a simple fans.");
        }

        else
            System.out.println("Can not remove a referee that does not exist in the DB.");
    }

    ////////////////////////////// USE CASE 9.4 //////////////////////////////
    public void setLeagueReferees (Season season, List<String> referees) {

        ArrayList<Referee> allReferees = new ArrayList<>();

        if (referees != null) {
            for (String referee : referees) {
                Referee currReferee = (Referee) db.getUserByFullName(referee);
                allReferees.add(currReferee);
            }
            season.setAllReferees(allReferees);
        }

        else{
            ////////////////// display error ????????? ///////////////
        }
    }
}
