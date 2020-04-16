package Teams;

import Games.Game;
import SystemLogic.MainSystem;
import SystemLogic.DB;
import Users.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Team implements Comparable {

    public enum teamStatus {
        active, close , PermanentlyClosed
    }

    private String name;
    private HashMap<String, Player> players;
    private HashMap<String, Coach> coaches;
    private HashMap<String, Manager> managers;
    private HashMap<String, TeamOwner> teamOwners;
    private HashSet<Assent> assents;
    private Stadium stadium;
    private ArrayList<Game> gameList;
    private teamStatus status;
    private Statistics statistics;
    private double budget;
    private TeamPage page;
    private DB db;



    public Team(String name, HashMap<String, TeamOwner> teamOwners) {
        this.name = name;
        this.teamOwners = teamOwners;
        this.status = teamStatus.active;
        page = null;
        db = DB.getInstance();
    }

    public Team(String name) {
        this.name = name;
        this.status = teamStatus.active;
        page = null;
        db = DB.getInstance();
    }

    public TeamPage createPage(String history,String nation){
        page = new TeamPage(name,players,coaches,managers,stadium,history,nation);
        return page;
    }

    public TeamPage getPage() {
        return page;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public boolean containsAssent(Assent assent){
        return assents.contains(assent);
    }

    public void addAssent(Assent assent){
        if(assent instanceof Player){
            addPlayer((Player)assent);
        }
        if(assent instanceof Coach){
            addCoach((Coach)assent);
        }
        if(assent instanceof Manager){
            addManager((Manager) assent);
        }
        if(assent instanceof TeamOwner){
            addTeamOwner((TeamOwner) assent);
        }
        if (assent instanceof Stadium){
            setStadium((Stadium) assent);
            MainSystem.LOG.info("The stadium " + stadium.getName() + "was added successfully to the team " + this.getName());
        }
        this.assents.add(assent);
    }

   public void removeAssent(Assent assent){
        if(assent instanceof Player){
            removePlayer((Player)assent);
        }
        if(assent instanceof Coach){
            removeCoach((Coach)assent);
        }
        if(assent instanceof Manager){
            removeManager((Manager) assent);
        }
        if(assent instanceof TeamOwner){
            removeTeamOwner((TeamOwner) assent);
        }
        if (assent instanceof Stadium){
            String stadium_name = stadium.getName();
            setStadium(null);
            MainSystem.LOG.info("The stadium " + stadium_name + "was removed successfully to the team " + this.getName());
        }
        this.assents.remove(assent);
    }

    public void setStatus(teamStatus status) {
        this.status = status;
    }

    public void addPlayer(Player player) {
        players.put(player.getUserFullName(), player);
        if(page!=null){
          page.addPlayer(player);
          db.setTeam(this);
        }
        MainSystem.LOG.info("the player " + player.getUserName() + " was added successfully to the team " + this.getName());
    }

    public void removePlayer(Player player) {
        players.remove(player.getUserFullName());
        if(page!=null){
            page.removePlayer(player);
            db.setTeam(this);
        }
        MainSystem.LOG.info("the player " + player.getUserName() + " was removed successfully from the team " + this.getName());
    }

    public void addCoach(Coach coach) {
        coaches.put(coach.getUserFullName(),coach);
        if(page!=null){
            page.addCoach(coach);
            db.setTeam(this);
        }
        MainSystem.LOG.info("the coach " + coach.getUserName() + " was added successfully to the team " + this.getName());
    }

    public void removeCoach(Coach coach) {
        coaches.remove(coach.getUserFullName());
        if(page!=null){
            page.removeCoach(coach);
            db.setTeam(this);
        }
        MainSystem.LOG.info("the coach " + coach.getUserName() + " was removed successfully to the team " + this.getName());
    }

    public void addManager(Manager manager) {
        managers.put(manager.getUserFullName(),manager);
        if(page!=null){
            page.addManager(manager);
            db.setTeam(this);
        }
        MainSystem.LOG.info("the manager " + manager.getUserName() + " was added successfully to the team " + this.getName());
    }

    public void removeManager(Manager manager) {
        managers.remove(manager.getUserFullName());
        if(page!=null){
            page.removeManager(manager);
            db.setTeam(this);
        }
        MainSystem.LOG.info("the manager " + manager.getUserName() + " was removed successfully to the team " + this.getName());
    }

    public void addTeamOwner(TeamOwner teamOwner) {
        teamOwners.put(teamOwner.getUserFullName(), teamOwner);
        MainSystem.LOG.info("the team owner " + teamOwner.getUserName() + " was added successfully to the team " + this.getName());
    }

    public void removeTeamOwner(TeamOwner teamOwner) {
        teamOwners.remove(teamOwner.getUserFullName());
        MainSystem.LOG.info("the team owner " + teamOwner.getUserName() + " was removed successfully to the team " + this.getName());
    }

    public HashMap<String, Coach> getCoaches()
    {
        return coaches;
    }

    public HashMap<String, Player> getPlayers()
    {
        return players;
    }

    public HashMap<String, Manager> getManagers() {
        return managers;
    }

    public HashMap<String, TeamOwner> getTeamOwners() {
        return teamOwners;
    }

    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
        if(page!=null){
            page.setPlayers(players);
            db.setTeam(this);
        }
    }

    public void setCoaches(HashMap<String, Coach> coaches) {
        this.coaches = coaches;
        if(page!=null){
            page.setCoaches(coaches);
            db.setTeam(this);
        }
    }

    public void setManagers(HashMap<String, Manager> managers) {
        this.managers = managers;
        if(page!=null){
            page.setManagers(managers);
            db.setTeam(this);
        }
    }

    public void setTeamOwners(HashMap<String, TeamOwner> teamOwners) {
        this.teamOwners = teamOwners;

    }

    public Stadium getStadium() {
        return stadium;

    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
        if(page!=null){
            page.setStadium(stadium);
            db.setTeam(this);
        }
    }


    public boolean containsPlayer(String player_name){
        //return players.
        return true;
    }

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if(page!=null){
            page.setName(name);
            db.setTeam(this);
        }
    }

    public teamStatus getStatus() {
        return status;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public int compareTo(Object obj) {
        Team other=(Team)obj;
        return this.getStatistics().compareTo(other.getStatistics());
    }
}
