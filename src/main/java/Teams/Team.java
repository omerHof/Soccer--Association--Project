package Teams;

import Games.Game;
import Users.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Team extends Observable {


    private String name;
    private ArrayList<Player>players;
    private ArrayList<Coach>coaches;
    private Stadium stadium;
    private TeamOwner owner;
    private Manager manager;
    private ArrayList<Game> gameList;

    public ArrayList<Player> getPlayers() {
        return players;
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

    public TeamOwner getOwner() {
        return owner;
    }

    public void setOwner(TeamOwner owner) {
        this.owner = owner;

    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
        setChanged();
        notifyObservers(owner);
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

}
