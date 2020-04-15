package Teams;

import Games.Game;
import Users.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;

public class Team extends Observable implements Comparable {

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

    public Team(String name, HashMap<String, TeamOwner> teamOwners) {
        this.name = name;
        this.teamOwners = teamOwners;
        this.status = teamStatus.active;
    }

    public Team(String name) {
        this.name = name;
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
            setStadium(null);
        }
        this.assents.remove(assent);
    }

    public void addPlayer(Player player) {
        players.put(player.getUserName(), player);
        setChanged();
        notifyObservers(player);
    }

    public void removePlayer(Player player) {
        players.remove(player.getUserName());
        setChanged();
        notifyObservers(player);
    }

    public void addCoach(Coach coach) {
        coaches.put(coach.getUserName(), coach);
        setChanged();
        notifyObservers(coach);
    }

    public void removeCoach(Coach coach) {
        coaches.remove(coach.getUserName());
        setChanged();
        notifyObservers(coach);
    }

    public void addManager(Manager manager) {
        managers.put(manager.getUserName(), manager);
        setChanged();
        notifyObservers(manager);
    }

    public void removeManager(Manager manager) {
        managers.remove(manager.getUserName());
        setChanged();
        notifyObservers(manager);
    }

    public void addTeamOwner(TeamOwner teamOwner) {
        teamOwners.put(teamOwner.getUserName(), teamOwner);
        setChanged();
        notifyObservers(teamOwner);
    }

    public void removeTeamOwner(TeamOwner teamOwner) {
        teamOwners.remove(teamOwner.getUserName());
        setChanged();
        notifyObservers(teamOwner);
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
        setChanged();
        notifyObservers(players);
    }

    public void setCoaches(HashMap<String, Coach> coaches) {
        this.coaches = coaches;
        setChanged();
        notifyObservers(coaches);
    }

    public void setManagers(HashMap<String, Manager> managers) {
        this.managers = managers;
        setChanged();
        notifyObservers(managers);
    }

    public void setTeamOwners(HashMap<String, TeamOwner> teamOwners) {
        this.teamOwners = teamOwners;
        setChanged();
        notifyObservers(teamOwners);
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
        setChanged();
        notifyObservers(stadium);
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
