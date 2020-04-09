package SystemLogic;

//import Users.AssociationRepresentative;
import LeagueSeasonsManagment.League;
import Teams.Team;
import Users.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DB {

    private static DB db;

    /**
     * implement data structures
     */
    private static HashMap<String,User> users;
    private static HashMap<String, League> leagues;
    private static HashMap<String, Team> teams;

    /**
     * constructor
     */
    private DB() {
        users= new HashMap<>();
        leagues= new HashMap<>();
        teams= new HashMap<>();
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

    /***************USER***************/

    /**
     * get user
     * @return user
     */
    public static User getUser(String name) {
        return users.get(name);
    }

    /**
     * set user
     * @param user
     */
    public static void setUser(User user) {
        users.put(user.getUserName(),user);
    }

    /**
     * add user if user name not contain already
     * @param user
     * @return
     */
    public static boolean addUser(User user){
        if(!users.containsKey(user.getUserName())){
            users.put(user.getUserName(),user);
            return true;
        }
        return false;
    }

    /**
     * remove user if exist
     * @param name
     * @return
     */
    public static boolean removeUser(String name){
        if(users.containsKey(name)){
            users.remove(name);
            return true;
        }
        return false;
    }

    /***************LEAGUE***************/

    /**
     * get league
     * @return league
     */
    public static League getLeague(String name) {
        return leagues.get(name);
    }
    /**
     * set league
     * @param league
     */
    public static void setLeague(League league) {
        leagues.put(league.getName(),league);
    }

    /**
     * add league if user name not contain already
     * @param league
     * @return
     */
    public static boolean addLeague(League league){
        if(!leagues.containsKey(league.getName())){
            leagues.put(league.getName(),league);
            return true;
        }
        return false;
    }

    /**
     * remove league if exist
     * @param name
     * @return
     */
    public static boolean removeLeague(String name){
        if(leagues.containsKey(name)){
            leagues.remove(name);
            return true;
        }
        return false;
    }

    /***************TEAM***************/

    /**
     * get team
     * @return team
     */
    public static Team getTeam(String name) {
        return teams.get(name);
    }

    /**
     * set team
     * @param team
     */
    public static void setTeam(Team team) {
        teams.put(team.getName(),team);
    }

    /**
     * add user if user name not contain already
     * @param team
     * @return
     */
    public static boolean addTeam(Team team){
        if(!teams.containsKey(team.getName())){
            teams.put(team.getName(),team);
            return true;
        }
        return false;
    }

    /**
     * remove user if exist
     * @param name
     * @return
     */
    public static boolean removeTeam(String name){
        if(teams.containsKey(name)){
            teams.remove(name);
            return true;
        }
        return false;
    }

    /***************others***************/
    /**
     * return user from requested type
     * @param type
     * @return
     */
    public static User getUserType(String type){

        Iterator it = users.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (type.equals("Fan") && pair.getValue() instanceof Fan) {
                return (User) pair.getValue();
            }
            if (type.equals("Coach") && pair.getValue() instanceof Coach) {
                return (User) pair.getValue();
            }
            if (type.equals("Manager") && pair.getValue() instanceof Manager) {
                return (User) pair.getValue();
            }
            if (type.equals("Player") && pair.getValue() instanceof Player) {
                return (User) pair.getValue();
            }
            if (type.equals("Referee") && pair.getValue() instanceof Referee) {
                return (User) pair.getValue();
            }
            if (type.equals("TeamOwner") && pair.getValue() instanceof TeamOwner) {
                return (User) pair.getValue();
            }
            if (type.equals("Administrator") && pair.getValue() instanceof Administrator) {
                return (User) pair.getValue();
            }
        }
        return null;
    }


/*
    public static AssociationRepresentative getRepresentative() {

        //choose randomly association representative
        return null;
    }
    */




}
