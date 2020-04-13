package Teams;

import Games.Game;
import Users.*;

import java.util.ArrayList;
import java.util.Observable;

public class Team extends Observable implements Comparable {

    public enum teamStatus {
        active, close , PermanentlyClosed
    }

    private teamStatus status;
    private Statistics statistics;
    private String name;
    private ArrayList<Player>players;
    private ArrayList<Coach>coaches;
    private Stadium stadium;
    private ArrayList<TeamOwner> teamOwners;
    private Manager manager;
    private ArrayList<Game> gameList;

    public Team(String name, ArrayList<TeamOwner> teamOwners) {
        this.name = name;
        this.teamOwners = teamOwners;
        this.status = teamStatus.active;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }


    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
        setChanged();
        notifyObservers(players);
    }
    public void setPlayer(Player player) {
        players.add(player);
        setChanged();
        notifyObservers(player);
    }
    public void setCoach(Coach Coach) {
        coaches.add(Coach);
        setChanged();
        notifyObservers(Coach);
    }

    public ArrayList<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(ArrayList<Coach> coaches) {
        this.coaches = coaches;
        setChanged();
        notifyObservers(coaches);

    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
        setChanged();
        notifyObservers(stadium);
    }



    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
        setChanged();
        //notifyObservers(owner);
    }

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public Team(String name) {
        this.name = name;
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

    @Override
    public int compareTo(Object obj) {
        Team other=(Team)obj;
        return this.getStatistics().compareTo(other.getStatistics());
    }
}
