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
import java.util.Date;
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

        Season newSeason = new Season(year);
        MainSystem.LOG.info("new season: " + year + " was added to league: " + leagueName + ".");

        setLeagueTeams(leagueName, newSeason, teams); // שיבוץ קבוצות מתוך הרשימה שהתקבלה
        setLeagueReferees(leagueName, newSeason, referees); //  שיבוץ שופטים מתוך רשימת השמות שהתקבלה
        setLeagueRepresentatives(leagueName, newSeason, representatives); // שיבוץ נציגי התאחדות אחראיים על עדכונים ממשחקים

        newSeason.setIScorePolicy(scorePolicy); //בחירת מדיניות חישוב תוצאות
        newSeason.setiGameInlayPolicy(gamePolicy); // בחירת מדיניות שיבוץ משחקים

        IGameInlayPolicy iGameInlayPolicy = newSeason.getiGameInlayPolicy();

        //הפעלת שיבוץ משחקים- מקבל את הקבוצות אז חייב להיקרא אחרי שיבוץ הקבוצות לעונה!!
        iGameInlayPolicy.gameInlayPolicyAlgoImplementation();

        db.addSeason(leagueName, newSeason); //adds the season to DB.

    }


    private void setLeagueRepresentatives(String leagueName, Season season, List<String> representatives) {

        if (representatives != null) {
            ArrayList<AssociationRepresentative> allRepresentatives = new ArrayList<>();

            for (String representative : representatives) {
                AssociationRepresentative currRepresentative = (AssociationRepresentative) db.getUserByFullName(representative);
                allRepresentatives.add(currRepresentative);
            }
            season.setAllRepresentatives(allRepresentatives);
            MainSystem.LOG.info("Associations Representatives were added to league: " + leagueName + ", season: " + season.getYear());
        }

        else{
            ////////////////// display error ????????? ///////////////
        }
    }

    /**
     * adds all teams to this league - help function
     * @param season - to add to
     * @param teams - list of teams' names
     */
    private void setLeagueTeams(String leagueName, Season season, List<String> teams) {

        ArrayList<Team> allTeams = new ArrayList<>();

        if (teams != null) {

            for (String team : teams) {
                Team currTeam = db.getTeam(team);
                allTeams.add(currTeam);
            }
            season.setAllTeams(allTeams);
            MainSystem.LOG.info("Teams were added to league: " + leagueName + ", season: " + season.getYear());

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
    public void setLeagueReferees (String leagueName, Season season, List<String> referees) {

        ArrayList<Referee> allReferees = new ArrayList<>();

        if (referees != null) {
            for (String referee : referees) {
                Referee currReferee = (Referee) db.getUserByFullName(referee);
                allReferees.add(currReferee);
            }
            season.setAllReferees(allReferees);
            MainSystem.LOG.info("Referees were added to league: " + leagueName + ", season: " + season.getYear());

        }

        else{
            ////////////////// display error ????????? ///////////////
        }
    }
}
