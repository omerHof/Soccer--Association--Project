package Users;

import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Assent;
import Teams.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Coach extends User implements Assent {

    private String teamRole;
    private int salary;
    private CoachPersonalPage page;
    private DB  DB1;
    private double worth;
    private Team currentTeam;

    public Coach(String userName, String password, String fullName,String userEmail, String teamRole) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
        this.teamRole = teamRole;
        salary=0;
        DB1=DB.getInstance();
        currentTeam=null;

    }

   public CoachPersonalPage createCoachPersonalPage(LocalDate birthDate, String team){
       MainSystem.LOG.info("The coach " +getUserFullName()+ " create personal page");
       Team currTeam = DB1.getTeam(team);
       if(currTeam==null){
           page = new CoachPersonalPage(userFullName,birthDate,teamRole,null);
       }
       else{
           page = new CoachPersonalPage(userFullName,birthDate,teamRole,team);
           setCurrentTeam(currTeam.getName());
       }
        return page;
   }

    public String getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(String teamRole) {

        this.teamRole = teamRole;
        if(page!=null){
            page.setTeamRole(teamRole);
        }
        DB1.setUser(this);

    }

    public boolean setCurrentTeam(String team){
        Team t =  DB1.getTeam(team);

        if(t==null){
           return false;
        }
        currentTeam=t;
        if(page!=null) {
            page.setCurrentTeam(t);
            page.setOneTeamToHistory(team);
        }
        DB1.setUser(this);
        return true;
    }
    public String getCurrentTeamName(){
       return currentTeam.getName();
    }
    public Team getCurrentTeam(){
        return currentTeam;
    }


    //changes for personal page


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
        DB1.setUser(this);
    }

    public CoachPersonalPage getPage() {
        return page;
    }

    public void setTeamHistory(ArrayList<String> teamHistoryList){
        if(page!=null){
            page.setTeamHistory(teamHistoryList);
        }
    }

    public ArrayList<String> getTeamHistory() {
        if(page!=null){
            return page.getTeamHistory();
        }
        return null;
    }

    @Override
    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }
}


