package Users;

import Teams.Team;

import java.util.ArrayList;

public class PlayerPersonalPage extends PersonalPage {

    private String name;
    //private int age;
    private String birthDate;
    private int height;
    private int weight;
    private int shirtNumber;
    private String position;
    private Team currentTeam;
    private ArrayList<Team> teamHistory;



    public PlayerPersonalPage(String name, String birthdate, String position){
        this.name=name;
        this.birthDate=birthdate;
        this.position=position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
       notifyObservers();

    }
/*
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

 */

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        notifyObservers();

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        notifyObservers();

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
        notifyObservers();

    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
        notifyObservers();

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
        notifyObservers();

    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
        notifyObservers();

    }
    public void addTeamToHistoryCareer(Team team){
        teamHistory.add(team);
    }

    public ArrayList<Team> getTeamHistory() {
        return teamHistory;
    }


}
