package Users;
import SystemLogic.DB;
import Teams.Assent;
import Teams.Team;

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

    public String addAssent(Assent assent){
        if(assent == null){
            return "null";
        }

        if(this.team.containsAssent(assent)){
            return "already added";
        }

        else{
            this.team.addAssent(assent);
            buyAssent(assent.getWorth());
        }
        return "";
    }

    public void appointTeamOwner(){

    }

    public void appointManager(){

    }

    public void buyAssent(double price){
        //todo: add sanctions
        double newBudget = this.team.getBudget() - price;
        this.team.setBudget(newBudget);
    }

    public void sellAssent(double price){
        double newBudget = this.team.getBudget() + price;
        this.team.setBudget(newBudget);
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
