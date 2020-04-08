package Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class PersonalPage extends Observable {
    private List<Observer> followers ;

    public PersonalPage(){
        followers = new ArrayList<>();

    }

    public void addFollower(Fan fan){
        followers.add(fan);

    }
    public void removeFollower(Fan fan){
        followers.remove(fan);
    }

    public List<Observer> getFollowers() {

       return followers;
    }




}
