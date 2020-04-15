package Users;

import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Assent;
import Teams.Team;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Player extends User implements Assent {

    private int age;
    private String courtRole;
    private PlayerPersonalPage page;
    private int salary;
    private DB DB1;
    private double worth;


    public Player(String userName, String password, String fullName, String userEmail ,Date birthDate, String courtRole) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
        this.courtRole = courtRole;
        age = getAge(birthDate);
        page =null;
        salary = 0;
        DB1=DB.getInstance();

    }


    public PlayerPersonalPage createPersonalPage(int height,int weight, int shirtNum,Team team) {
        MainSystem.LOG.info("The player " +getUserFullName()+ " create personal page");

        page = new PlayerPersonalPage(this.userFullName, age, courtRole, height, weight, shirtNum, team);
        return page;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;

    }


    public void setCourtRole(String courtRole) {
        this.courtRole = courtRole;
        if(page!=null){
            page.setPosition(courtRole);
        }
        DB1.setUser(this);

        //page.setPosition(courtRole);

    }
    public String getCourtRole() {
        return courtRole;
    }


    ///changes for personal page

    public void setNumberOfShirt(int number){
        if(page!=null) {
            page.setShirtNumber(number);
            DB1.setUser(this);
        }

    }
    public void setHeight(int height){
        if(page!=null) {
            page.setHeight(height);
            DB1.setUser(this);
        }
    }

    public void setWeight(int weight){
        if(page!=null) {
            page.setWeight(weight);
            DB1.setUser(this);
        }
    }
    public void setCurrentTeam(Team team){
        if(page!=null) {
            page.setCurrentTeam(team);
            page.setOneTeamToHistory(team.getName());
            DB1.setUser(this);
        }
    }
    public int getHeight(){
        if(page!=null){
            return page.getHeight();
        }
        return 0;
    }

    public int getWeight(){
        if(page!=null){
            return page.getWeight();
        }
        return 0;
    }
    public int getNumberOfShirt(){
        if(page!=null){
            return page.getShirtNumber();
        }
        return 0;

    }
    public Team getCurrentTeam(){
        if(page!=null){
            return page.getCurrentTeam();
        }
        return null;
    }



    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
        DB1.setUser(this);
    }

    public PlayerPersonalPage getPage() {
        return page;
    }

    public void setTeamHistory(ArrayList<String> teamHistoryList){
        if(page!=null){
             page.setTeamHistory(teamHistoryList);
             DB1.setUser(this);
        }
    }

    @Override
    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public ArrayList<Team> getTeamHistory() {
        if(page!=null){
            return page.getTeamHistory();
        }
        return null;
    }




    public static int getAge(Date dateOfBirth) {

        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        int age = 0;

        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ( (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
                (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH ))){
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        }else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH )) &&
                (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH ))){
            age--;
        }

        return age;
    }

}

