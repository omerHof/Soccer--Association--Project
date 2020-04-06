package SystemLogic;

import Users.AssociationRepresentative;
import Users.User;

import java.util.ArrayList;

public class DB {

    private static DB db;

    /**
     * implement data structures
     */
    private static ArrayList<User> users;
    private static ArrayList<User> leagues;

    /**
     * constructor
     */
    private DB() {
        users=new ArrayList<>();
        leagues=new ArrayList<>();
    }

    public static DB getResultsInstance(){
        if(db == null){
            synchronized (DB.class) {
                if(db == null){
                    db = new DB();
                }
            }
        }
        return db;
    }

    /**
     * getter for users
     * @return
     */
    public static ArrayList<User> getUsers() {
        return users;
    }

    /**
     * setter for users
     * @param users
     */
    public static void setUsers(ArrayList<User> users) {
        DB.users = users;
    }

    /**
     * getter for leagues
     * @return
     */
    public static ArrayList<User> getLeagues() {
        return leagues;
    }

    /**
     * setter for leagues
     * @param leagues
     */
    public static void setLeagues(ArrayList<User> leagues) {
        DB.leagues = leagues;
    }

    public static AssociationRepresentative getRepresentative() {

        //choose randomly association representative
        return null;
    }
}
