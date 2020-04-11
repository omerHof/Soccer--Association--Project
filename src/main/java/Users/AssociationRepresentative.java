package Users;

import LeagueSeasonsManagment.*;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Team;
import UserGenerator.IUserGenerator;
import UserGenerator.PremiumUserGenertator;
import UserGenerator.SimpleUserGenerator;

import java.sql.Ref;
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
    public void addLeague (String leagueName, int numOfTeams){

        League newLeague = new League(leagueName, numOfTeams);
        db.addLeague(newLeague);

        MainSystem.LOG.info("new league: " + leagueName + " were added.");
    }

    ////////////////////////////// USE CASE 9.2 //////////////////////////////
    public void addSeasonToLeague (String leagueName, int year, String scorePolicy, String gamePolicy, List<String> teams, List<String> referees, List<String> representatives){

        Season newSeason = new Season(year);

         // שיבוץ קבוצות
        for (String team : teams){
            Team currTeam = db.getTeam(team);
            db.setTeam(currTeam);
        }

        // שיבוץ שופטים
        setLeagueReferees(referees);


        // שיבוץ נציגי התאחדות אחראיים על עדכונים ממשחקים
        for (String representative : representatives){
            AssociationRepresentative currRepresentative = (AssociationRepresentative) db.getUser(representative);
            db.setUser(currRepresentative);
        }

        //בחירת מדיניות חישוב תוצאות
        newSeason.setIScorePolicy(scorePolicy);

        // בחירת מדיניות שיבוץ משחקים
        newSeason.setiGameInlayPolicy(gamePolicy);

        IGameInlayPolicy iGameInlayPolicy = newSeason.getiGameInlayPolicy();

        //הפעלת שיבוץ משחקים- מקבל את הקבוצות אז חייב להיקרא אחרי שיבוץ הקבוצות לעונה!!
        iGameInlayPolicy.gameInlayPolicyAlgoImplementation();

        db.addSeason(leagueName, newSeason);

        MainSystem.LOG.info("new season: " + year + " were added to league: " + leagueName + ".");

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
    public void setLeagueReferees (List<String> referees) {

        for (String referee : referees) {
            Referee currReferee = (Referee) db.getUserByFullName(referee);
            db.setUser(currReferee);
        }
    }
}
