package Users;

import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Team;

import java.util.Date;

public class Coach extends User {


    private String teamRole;
    private int salary;
    private CoachPersonalPage page;
    private DB  DB1;


    public Coach(String userName, String password, String fullName,String userEmail, String teamRole) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
        this.teamRole = teamRole;
        salary=0;
        DB1=DB.getInstance();

    }

   public CoachPersonalPage createCoachPersonalPage(Date birthDate, Team team){
       MainSystem.LOG.info("The coach " +getUserFullName()+ " create personal page");
       page = new CoachPersonalPage(userFullName,birthDate,teamRole,team);
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

    public void setCurrentTeam(Team team){
        if(page!=null) {
            page.setCurrentTeam(team);
            DB1.setUser(this);
        }
    }
    public Team getCurrentTeam(){
        if(page!=null){
            return page.getCurrentTeam();
        }
        return null;
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


}


