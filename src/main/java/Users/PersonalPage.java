package Users;

import Teams.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class PersonalPage extends Observable {
    private List<Fan> followers ;
    String name;
    Team currentTeam;

    public PersonalPage(){
        followers = new ArrayList<>();

    }

    public void addFollower(Fan fan){
        followers.add(fan);

    }
    public void removeFollower(Fan fan){
        followers.remove(fan);
    }

    public List<Fan> getFollowers() {

       return followers;
    }
    public String getName() {
        return name;
    }
    public Team getCurrentTeam() {
        return currentTeam;
    }





}
