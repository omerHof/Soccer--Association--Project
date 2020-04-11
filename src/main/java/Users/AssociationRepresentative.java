package Users;

import LeagueSeasonsManagment.IGameInlayPolicy;
import LeagueSeasonsManagment.IScorePolicy;
import LeagueSeasonsManagment.League;
import LeagueSeasonsManagment.Season;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Team;

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


    public void addSeasonToLeague (String leagueName, int year, String scorePolicy, String gamePolicy, List<String> teams, List<String> referees, List<String> representatives){

        Season newSeason = new Season(year);

         // שיבוץ קבוצות
        for (String team : teams){
            Team currTeam = db.getTeam(team);
            db.setTeam(currTeam);
        }

        // שיבוץ שופטים
        for (String referee : referees){
            Referee currReferee = (Referee)db.getUser(referee);
            db.setUser(currReferee);
        }

        // שיבוץ נציגי התאחדות אחראיים על עדכונים ממשחקים
        for (String representative : representatives){
            AssociationRepresentative currRepresentative = (AssociationRepresentative) db.getUser(representative);
            db.setUser(currRepresentative);
        }

        //בחירת מדיניות חישוב תוצאות
        newSeason.setIScorePolicy(scorePolicy);

        newSeason.setiGameInlayPolicy(gamePolicy);

        // בחירת מדיניות שיבוץ משחקים
        IGameInlayPolicy iGameInlayPolicy = newSeason.getiGameInlayPolicy();

        //הפעלת שיבוץ משחקים- מקבל את הקבוצות אז חייב להיקרא אחרי שיבוץ הקבוצות לעונה!!
        iGameInlayPolicy.gameInlayPolicyAlgoImplementation();

        db.addSeason(leagueName, newSeason);

        MainSystem.LOG.info("new season: " + year + " were added to league: " + leagueName + ".");

    }

}
