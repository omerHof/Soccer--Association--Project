package Users;

import Teams.Team;

import java.util.Date;

public class Player extends User {

    private String userName;
    private String password;

    private String fullName;
    private String birthDate;
    private String courtRole;
    private PlayerPersonalPage page;
    private int salary;



    public Player(String userName, String password, String fullName, String birthDate, String courtRole) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.courtRole = courtRole;
        page = new PlayerPersonalPage(fullName,birthDate,courtRole);// not sure
        salary = 0;

        //this.page
    }

    public boolean approveRegistration(String fullName, String role){
        return true;
    }



    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getCourtRole() {
        return courtRole;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
        page.setName(fullName);
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        page.setBirthDate(birthDate);
    }

    public void setCourtRole(String courtRole) {
        this.courtRole = courtRole;
        page.setPosition(courtRole);

    }

    ///changes for personal page

    public void setNumberOfShirt(int number){
        page.setShirtNumber(number);
    }
    public void setHeight(int height){
        page.setHeight(height);
    }
    public void setWeight(int weight){
        page.setWeight(weight);
    }
    public void setCurrentTeam(Team team){
        page.setCurrentTeam(team);
    }
    public void setTeamToHistoryOfTeams(Team team){
        page.addTeamToHistoryCareer(team);
    }
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public PlayerPersonalPage getPage() {
        return page;
    }
}

