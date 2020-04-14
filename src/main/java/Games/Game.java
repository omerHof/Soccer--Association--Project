package Games;

import SystemLogic.DB;
import Teams.Team;
import Users.Referee;

import java.util.*;

public class Game {

    public enum gameStatus {
        preGame,active, finish, close
    }

    private gameStatus status;
    private Team homeTeam;
    private Team awayTeam;
    private Date gameDate;
    private String gameHour;
    private String score;
    private ArrayList<Event> eventBook;
    private List<Referee> gameReferees;
    private String finalReport;

    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        gameReferees = new LinkedList<>();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public String getGameHour() {
        return gameHour;
    }

    public gameStatus getStatus() { return status; }

    public String getScore() {
        return score;
    }

    public List<Referee> getGameReferees() {
        return gameReferees;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public void setGameHour(Date d) {
        this.gameHour = Integer.toString(d.getHours()) + ":" + Integer.toString(d.getMinutes());
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setGameReferees(List<Referee> gameReferees) {
        this.gameReferees = gameReferees;
    }

    public void addEvent (Event event){
        eventBook.add(event);
    }
}

class DayToGame extends TimerTask {

    @Override
    public void run() {

    }
}

class StartGame extends TimerTask{

    @Override
    public void run() {

    }
}

class EndGame extends TimerTask{

    @Override
    public void run() {

    }
}

class CloseGame extends TimerTask{

    @Override
    public void run() {

    }
}
