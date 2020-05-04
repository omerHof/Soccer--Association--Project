package ServiceLayer;

import Games.Event;
import SystemLogic.MainSystem;
import Users.AssociationRepresentative;
import Users.Manager;
import Users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a bridge for handling the league/season/game creation and functionality between the domain layer and the GUI layer
 */

public class LeagueSeasonManagement {

    private User currentUser;



    ////////////////AssociationRepresentative MANAGEMENT FUNCTIONALITY//////////////////

    private void findTheUser() {
        if(this.currentUser == null){
            this.currentUser= MainSystem.getInstance().getCurrentUser();
        }
    }

    public boolean addLeague(String leagueName, int numOfTeams){
        findTheUser();
        return ((AssociationRepresentative) currentUser).addLeague(leagueName,numOfTeams);
    }

    public String addSeasonToLeague(String leagueName, int year, String scorePolicy, String gamePolicy, List<String> teams, List<String> referees, List<String> representatives){
        findTheUser();
        return ((AssociationRepresentative) currentUser).addSeasonToLeague(leagueName,year,scorePolicy,gamePolicy,teams,referees,representatives);
    }

    public boolean addReferee (String fullName){
        findTheUser();
        return ((AssociationRepresentative) currentUser).addReferee(fullName);
    }

    public boolean removeReferee (String fullName){
        findTheUser();
        return ((AssociationRepresentative) currentUser).removeReferee(fullName);
    }

    public boolean addGameEvent(String type, int time, String playerName, String whichTeam){
        findTheUser();
        Event.eventType event =Event.eventType.valueOf(type);
        return ((AssociationRepresentative) currentUser).addGameEvent(event,time,playerName, whichTeam);
    }

    ArrayList<String> teams=new ArrayList<>();
    ArrayList<String> referees=new ArrayList<>();
    ArrayList<String> representatives=new ArrayList<>();
    ArrayList<String> leagues=new ArrayList<>();
    String leagueTest="leagueTest";

    String teamTest1="teamTest1";
    String teamTest2="teamTest2";

    String regereeTest1="regereeTest1";
    String regereeTest2="regereeTest2";

    String repTest1="repTest1";
    String repTest2="repTest2";

    public List<String> getTeams(){
        teams.add(teamTest1);
        teams.add(teamTest2);
        return teams;
    }
    public List<String> getReferees(){
        referees.add(regereeTest1);
        referees.add(regereeTest2);
        return referees;
    }
    public List<String> getRepresentatives(){
        representatives.add(repTest1);
        representatives.add(repTest2);
        return representatives;
    }
    public List<String> getLeagues(){
        leagues.add(leagueTest);
        return leagues;
    }



}
