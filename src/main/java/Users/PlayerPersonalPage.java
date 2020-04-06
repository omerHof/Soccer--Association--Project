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
    private ArrayList<User>followers;


    public PlayerPersonalPage(Player player){
        name=player.getFullName();
        birthDate=player.getBirthDate();
        position=player.getCourtRole();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
    }
    public void addTeamToHistoryCareer(Team team){
        teamHistory.add(team);
    }

    public ArrayList<Team> getTeamHistory() {
        return teamHistory;
    }

    public void addFollower(User user){
        followers.add(user);
    }
    public void removeFollower(User user){
        followers.remove(user);
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }
}
