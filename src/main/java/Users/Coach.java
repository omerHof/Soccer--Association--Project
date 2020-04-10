package Users;

import SystemLogic.MainSystem;
import Teams.Team;

import java.util.Date;

public class Coach extends User {

    private int age;
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
        page=null;
        //page = new CoachPersonalPage(fullName,qualification,teamRole);


    }
    ///do it later
    public CoachPersonalPage createPersonalPage(Date bithDate, Team team){
        MainSystem.LOG.info("The coach  create personal page");

        page= new CoachPersonalPage(userFullName,bithDate,teamRole,team);
        return page;
    }

    public boolean approveRegistration(String fullName, String role){
        return true;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPage(CoachPersonalPage page) {
        this.page = page;
    }
    /*
    ///changes for personal page

    public void setCurrentTeam(Team team){
        page.setCurrentTeam(team);
    }
    public void setTeamToHistoryOfTeams(Team team){
        page.addTeamToHistoryCareer(team);
    }

     */
}


