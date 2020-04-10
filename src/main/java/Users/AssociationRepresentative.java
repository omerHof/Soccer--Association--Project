package Users;

import LeagueSeasonsManagment.IScorePolicy;
import LeagueSeasonsManagment.League;
import LeagueSeasonsManagment.Season;
import SystemLogic.DB;
import SystemLogic.MainSystem;

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

    public void addLeague (String leagueName, int numOfTeams){

        League newLeague = new League(leagueName, numOfTeams);
        db.addLeague(newLeague);

        MainSystem.LOG.info("new league: " + leagueName + " were added.");
    }


   /* public void addSeasonToLeague (String leagueName, int year, IScorePolicy policy,){

        Season newSeason = new Season(year);
        newSeason.setiGameInlayPolicy(null); ////////////// policy.
         // שיבוץ קבוצות
        // שיבוץ שופטים
        // שיבוץ נציגי התאחדות אחראיים על עדכונים ממשחקים
        // בחירת מדיניות שיבוץ משחקים
        // כפתור הפעלת שיבוץ משחקים?


        db.addSeason(leagueName, newSeason);

        MainSystem.LOG.info("new season: " + leagueName + " were added to league: " + leagueName + ".");

    }*/

}
