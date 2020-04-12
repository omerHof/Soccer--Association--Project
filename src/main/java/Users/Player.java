package Users;

import SystemLogic.MainSystem;
import Teams.Team;

import java.util.Calendar;
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
        age = getAge(birthDate);
        page =null;
        salary = 0;

    }


    public PlayerPersonalPage createPersonalPage(int height,int weight, int shirtNum,Team team) {
        MainSystem.LOG.info("The player  create personal page");

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
        //page.setPosition(courtRole);

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

