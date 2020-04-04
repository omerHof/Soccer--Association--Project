package Users;

import LeagueSeasonsManagment.League;
import LeagueSeasonsManagment.Season;

import java.util.List;

public class AssociationRepresentative extends User {

    private String userName;
    private String password;

    private String fullName;
    private static int numOfApprovals = 0 ;

    public AssociationRepresentative(String userName, String password, String fullName) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
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

        DB.addLeague(newLeague); //////////////// ?????????????????
    }

    public void addSeasonToLeague (String leagueName, int year){

        League currLeague = DB.getLeague(leagueName);
        Season newSeason = new Season(year);

        List<Season> currSeasons = currLeague.getAllSeasons(); //gets all current seasons
        ((List) currSeasons).add(newSeason); //adds the new one
        currLeague.setAllSeasons(currSeasons); //replace old list.

    }

    }
