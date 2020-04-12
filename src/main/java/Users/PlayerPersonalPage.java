package Users;

import Teams.Team;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PlayerPersonalPage extends PersonalPage {



    private int height;
    private int weight;
    private int shirtNumber;
    private String position;



    public PlayerPersonalPage(String name, int age, String position,int height,int weight,int shirtNumber, Team team){
        this.name=name;
        this.age=age;
        this.position=position;
        this.height = height;
        this.weight = weight;
        this.shirtNumber=shirtNumber;
        this.currentTeam=team;
        this.teamHistory.add(team.getName());
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


}
