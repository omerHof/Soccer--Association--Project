package ServiceLayer;

import SystemLogic.DB;
import SystemLogic.MainSystem;

import java.util.ArrayList;

public class SystemManagement {

    private LeagueSeasonManagement leagueSeasonManagement;
    private TeamManagement teamManagement;
    private UserManagement userManagement;

    public SystemManagement(LeagueSeasonManagement leagueSeasonManagement) {
       this.leagueSeasonManagement = new LeagueSeasonManagement();
       this.teamManagement = new TeamManagement();
       this.userManagement = new UserManagement();
       systemInitialize();
    }

    private void systemInitialize() {
        MainSystem.getInstance().initializeSystem();

    }

    ArrayList<String> getLeagueNames(){
        return DB.getInstance().getLeagueNames();
    }
    ArrayList<String> getTeamsNames(){
        return DB.getInstance().getTeamsNames();
    }

    ArrayList<String> getAllUsersByType( String type){
        return DB.getInstance().getAllUserByType(type);
    }
}


