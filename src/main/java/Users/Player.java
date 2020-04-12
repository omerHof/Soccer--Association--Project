package Users;

import SystemLogic.MainSystem;
import Teams.Team;

import java.util.Date;

public class Player extends User {

    private int age;
    private String courtRole;
    private PlayerPersonalPage page;
    private int salary;



    public Player(String userName, String password, String fullName, String userEmail ,Date birthDate, String courtRole) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
        this.courtRole = courtRole;
        page =null;
        // page = new PlayerPersonalPage(fullName,birthDate,courtRole);// not sure
        salary = 0;

        //this.page
    }


    public PlayerPersonalPage createPersonalPage(int height,int weight, int shirtNum, Date birthDate,Team team) {
        MainSystem.LOG.info("The player  create personal page");

        page = new PlayerPersonalPage(this.userFullName, birthDate, courtRole, height, weight, shirtNum, team);
        return page;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }





    public boolean approveRegistration(String fullName, String role){
        return true;
    }




/*
   public String getAge() {
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
*/


    public void setCourtRole(String courtRole) {
        this.courtRole = courtRole;
        page.setPosition(courtRole);

    }
    public String getCourtRole() {
        return courtRole;
    }


    ///changes for personal page
/*
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

 */
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public PlayerPersonalPage getPage() {
        return page;
    }

    public void setPage(PlayerPersonalPage page) {
        this.page = page;
    }

}

