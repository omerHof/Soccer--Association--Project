package ServiceLayer;

import SystemLogic.MainSystem;

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
}


