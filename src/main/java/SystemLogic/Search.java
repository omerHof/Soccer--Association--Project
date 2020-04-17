package SystemLogic;

import LeagueSeasonsManagment.League;
import Teams.Team;
import Users.Player;
import Users.Referee;
import Users.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Search {

    public enum Category {
        teams, players, referees, leagues ;
    }

    private String toSearch;
    private Category category;
    private DB db = DB.getInstance();

    public Search(String toSearch, Category category) {
        this.toSearch = toSearch;
        this.category = category;
    }

    public Search(String toSearch) {
        this.toSearch = toSearch;
    }

    /**
     * this function calls to the specific search we want to do - all or by category
     * @param toSearch - the String we want to find
     * @param category - the Category enum we want to look for
     * @return list of Objects found.
     */
    public List<Object> search (String toSearch, Category category){

        List<Object> toReturn = new LinkedList<>();

        if (category!= null)
            searchByCategory(toSearch, category);
        else
            searchAll(toSearch);

    return toReturn;
    }


    /**
     * this function searches for a specific kind of Objects in the DB
     * @param toSearch - the String we want to find
     * @param category - the Category enum we want to look for
     * @return list of Objects found.
     */
    public List<Object> searchByCategory (String toSearch, Category category){

        List<Object> allObjects = new LinkedList<>();
        //Object objectToAdd = new Object();

        if(category.equals(Category.leagues)) { //category search by League:

            League objectToAdd = db.getLeague(toSearch); //league name
            if (objectToAdd != null)
                allObjects.add(objectToAdd);
        }

        else if(category.equals(Category.players)) { //category search by player:

            Player objectToAdd = (Player)db.getUserByFullName(toSearch);
            if (objectToAdd != null)
                allObjects.add(objectToAdd);

            objectToAdd = (Player)db.getUserType("Player");
            if(objectToAdd!=null)
                allObjects.add(objectToAdd);

            objectToAdd = (Player)db.getUser(toSearch); //username
            if (objectToAdd != null)
                allObjects.add(objectToAdd);

            ArrayList<User> obj = db.getUserTypeList("Player");
            if(obj!=null){
                for(User user : obj) //adds all the users list
                    allObjects.add(user);
            }
        }

        else if(category.equals(Category.referees)) { //category search by Referee:

            Referee objectToAdd = (Referee)db.getUserType("Referee");
            if(objectToAdd!=null)
                allObjects.add(objectToAdd);

            objectToAdd = (Referee)db.getUserByFullName(toSearch);
            if (objectToAdd != null)
                allObjects.add(objectToAdd);

            ArrayList<User> obj = db.getUserTypeList("Referee");
            if(obj!=null){
                for(User user : obj) //adds all the users list
                    allObjects.add(user);
            }

            objectToAdd = (Referee)db.getUser(toSearch); //username
            if (objectToAdd != null)
                allObjects.add(objectToAdd);
        }

        else if(category.equals(Category.teams)) { //category search by Team:

            Team objectToAdd = db.getTeam(toSearch);
            if(objectToAdd!=null)
                allObjects.add(objectToAdd);
        }
        return allObjects;
    }



    /**
     * this function searches for all kind of Objects in the DB
     * @param toSearch - the String we want to find
     * @return list of Objects found.
     */
    public List<Object> searchAll (String toSearch){

        List<Object> allObjects = new LinkedList<>();
        Object objectToAdd = new Object();

        //using any kind of search from DB in 3.. 2.... 1......

        objectToAdd = db.getUser(toSearch);
        if(objectToAdd!=null)
            allObjects.add(objectToAdd);

        objectToAdd = db.getUserByFullName(toSearch);
        if(objectToAdd!=null)
            allObjects.add(objectToAdd);

        objectToAdd = db.getTeam(toSearch);
        if(objectToAdd!=null)
            allObjects.add(objectToAdd);

        objectToAdd = db.getUserType(toSearch);
        if(objectToAdd!=null)
            allObjects.add(objectToAdd);

        objectToAdd = db.getLeague(toSearch);
        if(objectToAdd!=null)
            allObjects.add(objectToAdd);

        ArrayList<User> obj = db.getUserTypeList(toSearch);
        if(obj!=null){
            for(User user : obj) //adds all the users list
                allObjects.add(user);
        }

        return allObjects;
    }
}
