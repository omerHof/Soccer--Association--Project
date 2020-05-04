package ServiceLayer;

import DataForTest.DataBase;
import SystemLogic.DB;
import SystemLogic.MainSystem;

import java.util.ArrayList;

public class SystemManagement {

    private LeagueSeasonManagement leagueSeasonManagement;
    private TeamManagement teamManagement;
    private UserManagement userManagement;
    private DataBase dataBase;//todo only for test
    public SystemManagement() {
       this.leagueSeasonManagement = new LeagueSeasonManagement();
       this.teamManagement = new TeamManagement();
       this.userManagement = new UserManagement();
       systemInitialize();
       dataBase= new DataBase();//todo remove- only for test

    }

    private void systemInitialize() {
        MainSystem.getInstance().initializeSystem();

    }

    public ArrayList<String> getLeagueNames(){
        return DB.getInstance().getLeagueNames();
    }
    public ArrayList<String> getFullTeamsNames(){
        return DB.getInstance().getFullTeamsNames();
    }

    public ArrayList<String> getAllUsersByType( String type){
        return DB.getInstance().getAllUserByType(type);
    }
}


