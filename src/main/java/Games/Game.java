package Games;

import SystemLogic.DB;
import Teams.Team;
import Users.AssociationRepresentative;
import Users.Fan;
import Users.Referee;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Game extends Observable{

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
    private AssociationRepresentative representative;
    private String finalReport;
    private LocalDateTime timeOfGame;
    Timer timer;

    /**
     * constructor
     * @param homeTeam
     * @param awayTeam
     * @param timeOfGame
     */
    public Game(Team homeTeam, Team awayTeam, LocalDateTime timeOfGame) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameReferees = new LinkedList<>();
        this.timeOfGame= timeOfGame;
        this.timer = new Timer();
        this.status = gameStatus.preGame;
    }

    /**
     * set the alarms before, after and on the game to all Stakeholders
     */
    public void SetAlarms(){
        dayToGame();
        startGame();
        endGame();
        closeGame();
    }

    /**
     * set alarms day before the game
     */
    private void dayToGame() {
        LocalDateTime dayBefore =  timeOfGame.minus(1, ChronoUnit.DAYS);
        DayToGame dayToGame= new DayToGame(gameReferees,representative,homeTeam,awayTeam);
        LocalDateTime from =LocalDateTime.now();
        Duration duration = Duration.between(from, dayBefore);
        timer.schedule(dayToGame,duration.getSeconds());
    }

    /**
     * set alarms when the game start
     */
    private void startGame() {
        LocalDateTime GameTime =  timeOfGame;
        StartGame startGame= new StartGame(representative,homeTeam,awayTeam,this);
        LocalDateTime from =LocalDateTime.now();
        Duration duration = Duration.between(from, GameTime);
        timer.schedule(startGame,duration.getSeconds());
    }

    /**
     * set alarms when the game start
     */
    private void endGame() {
    }

    /**
     * set alarms when the game close
     */
    private void closeGame() {
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

    public void setStatus(gameStatus status) {
        this.status = status;
    }

    public void addEvent (Event event){
        eventBook.add(event);
    }
}

class DayToGame extends TimerTask {
    List<Referee> referees;
    AssociationRepresentative representative;
    Team homeTeam;
    Team awayTeam;
    /**
     * constructor
     * @param referees
     * @param representative
     * @param homeTeam
     * @param awayTeam
     */
    public DayToGame(List<Referee> referees, AssociationRepresentative representative, Team homeTeam,Team awayTeam) {
        this.referees = referees;
        this.representative = representative;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;

    }

    @Override
    public void run() {
        homeTeam.notifyObservers("DayToGame");
        awayTeam.notifyObservers("DayToGame");

    }
}

class StartGame extends TimerTask{
    AssociationRepresentative representative;
    Team homeTeam;
    Team awayTeam;
    Game game;

    /**
     * constructor
     * @param representative
     * @param homeTeam
     * @param awayTeam
     */
    public StartGame(AssociationRepresentative representative, Team homeTeam, Team awayTeam,Game game) {
        this.representative = representative;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.game = game;
    }

    @Override
    public void run() {
        homeTeam.notifyObservers("game Start!");
        awayTeam.notifyObservers("game Start!");
        game.setStatus(Game.gameStatus.active);
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
