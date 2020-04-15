package Users;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Assent;
import Teams.Team;

import java.util.ArrayList;
import java.util.HashMap;

public class TeamOwner extends User implements Assent {
    private Team team = null;
    private double worth;
    private boolean permission;

    public TeamOwner(String userName, String password, String fullName,String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
    }

    public void askPermission (){//todo: necessary?

    }

    public void openTeam(String team_name){//after he got permission
        if(permission) {
            HashMap<String , TeamOwner> me = new HashMap<>();
            me.put(super.getUserName(), this);
            team = new Team(team_name, me);
            DB.getInstance().addTeam(team);
        }
    }


    public void editUserDetails(){

    }

    public void showStadium(){

    }

    public void addStadium(){

    }

    public void removeStadium(){

    }

    public void editStadiumDetails(){

    }

    public void appointTeamOwner(){

    }

    public void appointManager(){

    }

    public void closeTeam(){

    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
