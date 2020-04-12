package Users;

import SystemLogic.MainSystem;
import Teams.Team;

import java.util.Date;

public class Coach extends User {


    private String teamRole;
    private int salary;
    private CoachPersonalPage page;

    public Coach(String userName, String password, String fullName,String userEmail, String teamRole) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
        this.teamRole = teamRole;
        salary=0;

    }

   public CoachPersonalPage createCoachPersonalPage(Date birthDate, Team team){
       MainSystem.LOG.info("The coach create personal page");
       page = new CoachPersonalPage(userFullName,birthDate,teamRole,team);
        return page;
   }

    public String getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(String teamRole) {
        this.teamRole = teamRole;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public CoachPersonalPage getPage() {
        return page;
    }

    public void setPage(CoachPersonalPage page) {
        this.page = page;
    }
}


