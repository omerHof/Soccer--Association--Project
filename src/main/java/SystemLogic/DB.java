package SystemLogic;

//import Users.AssociationRepresentative;
import LeagueSeasonsManagment.League;
import LeagueSeasonsManagment.Season;
import Teams.Team;
import Users.*;

import java.util.*;

public class DB {

    private static DB db;

    /**
     * implement data structures
     */
    private HashMap<String,User> users;
    private HashMap<String, League> leagues;
    private HashMap<String, Team> teams;

    /**
     * constructor
     */
    private DB() {
        users= new HashMap<>();
        leagues= new HashMap<>();
        teams= new HashMap<>();
    }

    public static DB getInstance(){
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
     * get user by user name
     * @return user
     */
    public User getUser(String name) {
        if(users.containsKey(name)) {
            return users.get(name);
        }
        return null;
    }

    /**
     * get user by full name
     * @return user
     */
    public User getUserByFullName(String name) {
        Iterator it = users.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            User user = (User) pair.getValue();
            if (user.getUserFullName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    /**
     * check if user exist
     * @param name
     * @return
     */
    public boolean userExist (String name){
        if(users.containsKey(name)) {
            return true;
        }
        return false;
    }

    /**
     * set user
     * @param user
     */
    public void setUser(User user) {
        if(user!=null && user.getUserName()!=null && !userExist(user.getUserName())) {
            users.put(user.getUserName(), user);
        }
    }

    /**
     * add user if user name not contain already
     * @param user
     * @return
     */
    public boolean addUser(User user){
        if(user!=null && !users.containsKey(user.getUserName())){
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
    public boolean removeUser(String name){
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
    public League getLeague(String name) {
        if(leagues.containsKey(name)) {
            return leagues.get(name);
        }
        return null;
    }
    /**
     * check if league exist
     * @param name
     * @return
     */
    public boolean leagueExist (String name){
        if(leagues.containsKey(name)) {
            return true;
        }
        return false;
    }
    /**
     * set league
     * @param league
     */
    public void setLeague(League league) {
        if (league != null && league.getName() != null && !leagueExist(league.getName())) {
            leagues.put(league.getName(), league);
        }
    }

    /**
     * add league if user name not contain already
     * @param league
     * @return
     */
    public boolean addLeague(League league){
        if(league!=null && !leagues.containsKey(league.getName())){
            leagues.put(league.getName(),league);
            return true;
        }
        return false;
    }

    /**
     * add season to league
     * @param name
     * @param season
     * @return
     */
    public boolean addSeason(String name, Season season){
        if(leagues.containsKey(name)){
            if(season!=null && !leagues.get(name).getAllSeasons().contains(season)){
                List<Season> newList=leagues.get(name).getAllSeasons();
                newList.add(season);
                leagues.get(name).setAllSeasons(newList);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * remove league if exist
     * @param name
     * @return
     */
    public boolean removeLeague(String name){
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
    public Team getTeam(String name) {
        if(teams.containsKey(name)) {
            return teams.get(name);
        }
        return null;
    }

    /**
     * check if team exist
     * @param name
     * @return
     */
    public boolean teamExist (String name){
        if(teams.containsKey(name)) {
            return true;
        }
        return false;
    }
    /**
     * set team
     * @param team
     */
    public void setTeam(Team team) {
        if (team != null && team.getName() != null && !teamExist(team.getName())) {
            teams.put(team.getName(), team);

        }
    }

    /**
     * add user if user name not contain already
     * @param team
     * @return
     */
    public boolean addTeam(Team team){
        if(team!=null && !teams.containsKey(team.getName())){
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
    public boolean removeTeam(String name){
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

    public User getUserType(String type){

        Iterator it = users.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (type.equals("AssociationRepresentative") && pair.getValue() instanceof AssociationRepresentative) {
                return (User) pair.getValue();
            }
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
            if (type.equals("MainReferee") && pair.getValue() instanceof MainReferee) {
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
