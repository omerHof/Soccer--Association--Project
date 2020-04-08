package Users;

import Teams.Team;

public class Coach extends User {

    private String userName;
    private String password;

    private String fullName;
    private String qualification;
    private String teamRole;
    private int salary;
    private CoachPersonalPage page;

    public Coach(String userName, String password, String fullName, String qualification, String teamRole) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.qualification = qualification;
        this.teamRole = teamRole;
        salary=0;
        page = new CoachPersonalPage(fullName,qualification,teamRole);


    }

    public boolean approveRegistration(String fullName, String role){
        return true;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
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
    ///changes for personal page


    public void setCurrentTeam(Team team){
        page.setCurrentTeam(team);
    }
    public void setTeamToHistoryOfTeams(Team team){
        page.addTeamToHistoryCareer(team);
    }
}


