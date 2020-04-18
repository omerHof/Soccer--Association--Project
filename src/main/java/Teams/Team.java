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
        players = new HashMap<>();
        coaches = new HashMap<>();
        managers = new HashMap<>();
        gameList = new ArrayList<>();
    }

    public Team(String name) {
        this.name = name;
        this.status = teamStatus.active;
        page = null;
        db = DB.getInstance();
        players = new HashMap<>();
        coaches = new HashMap<>();
        managers = new HashMap<>();
        teamOwners = new HashMap<>();
        gameList = new ArrayList<>();

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
        Team team = player.getCurrentTeam();
        if(team!=null) {
            team.removePlayer(player);
            db.setTeam(team);
        }
        players.put(player.getUserFullName(), player);
        player.setCurrentTeam(this);
        db.setTeam(this);

        if(page!=null){
            page.addPlayer(player);
        }
        MainSystem.LOG.info("the player " + player.getUserName() + " was added successfully to the team " + this.getName());
    }

    public void removePlayer(Player player) {
        players.remove(player.getUserFullName());
        player.setCurrentTeam(null);
        db.setTeam(this);
        if(page!=null){
            page.removePlayer(player);
        }
        MainSystem.LOG.info("the player " + player.getUserName() + " was removed successfully from the team " + this.getName());
    }

    public void addCoach(Coach coach) {
        Team team = coach.getCurrentTeam();
        if(team!=null) {
            team.removeCoach(coach);
            db.setTeam(team);
        }
        coaches.put(coach.getUserFullName(), coach);
        coach.setCurrentTeam(this);
        db.setTeam(this);
        if(page!=null){
            page.addCoach(coach);
        }
        MainSystem.LOG.info("the coach " + coach.getUserName() + " was added successfully to the team " + this.getName());
    }

    public void removeCoach(Coach coach) {
        coaches.remove(coach.getUserFullName());
        coach.setCurrentTeam(null);
        db.setTeam(this);
        if(page!=null){
            page.removeCoach(coach);
        }
        MainSystem.LOG.info("the coach " + coach.getUserName() + " was removed successfully to the team " + this.getName());
    }

    public void addManager(Manager manager) {
        Team team = manager.getTeam();
        if(team!=null){
            team.removeManager(manager);
            db.setTeam(team);
        }
        managers.put(manager.getUserFullName(),manager);
        manager.setTeam(this);
        if(page!=null){
            page.addManager(manager);
        }
        MainSystem.LOG.info("the manager " + manager.getUserName() + " was added successfully to the team " + this.getName());
    }

    public void removeManager(Manager manager) {
        managers.remove(manager.getUserFullName());
        manager.setTeam(null);
        db.setTeam(this);
        if(page!=null){
            page.removeManager(manager);
        }
        MainSystem.LOG.info("the manager " + manager.getUserName() + " was removed successfully to the team " + this.getName());
    }

    public void addTeamOwner(TeamOwner teamOwner) {
        Team team = teamOwner.getTeam();
        if(team!=null){
            team.removeTeamOwner(teamOwner);
            db.setTeam(team);
        }
        teamOwners.put(teamOwner.getUserFullName(), teamOwner);
        MainSystem.LOG.info("the team owner " + teamOwner.getUserName() + " was added successfully to the team " + this.getName());
    }

    public void removeTeamOwner(TeamOwner teamOwner) {
        teamOwners.remove(teamOwner.getUserFullName());
        teamOwner.setTeam(null);
        db.setTeam(this);
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

    public void addGame (Game game){
        if (game != null)
            gameList.add(game);
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
