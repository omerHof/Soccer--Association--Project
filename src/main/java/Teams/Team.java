package Teams;

import Users.Fan;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Team extends Observable {
    private List<Fan> followers ;


    private String name;

    public Team(String name) {
        this.name = name;
        followers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Fan> getFollowers() {
        return followers;
    }
}
