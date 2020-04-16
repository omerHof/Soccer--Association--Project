package Games;

import SystemLogic.DB;
import SystemLogic.MainSystem;
import Teams.Statistics;
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
    private Date gameDate;//todo remove
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
    public void SetAlarms(){//todo tali need to add this func
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
        LocalDateTime GameEndTime =  timeOfGame.plus(90,ChronoUnit.MINUTES);
        EndGame endGame= new EndGame(homeTeam,awayTeam,this,score);
        LocalDateTime from =LocalDateTime.now();
        Duration duration = Duration.between(from, GameEndTime);
        timer.schedule(endGame,duration.getSeconds());
    }

    /**
     * set alarms when the game close
     */
    private void closeGame() {
        LocalDateTime closeGameTime =  timeOfGame.plus(5,ChronoUnit.DAYS);
        CloseGame closeGame= new CloseGame(this);
        LocalDateTime from =LocalDateTime.now();
        Duration duration = Duration.between(from, closeGameTime);
        timer.schedule(closeGame,duration.getSeconds());
    }

    public LocalDateTime getTimeOfGame() {
        return timeOfGame;
    }

    public void setTimeOfGame(LocalDateTime timeOfGame) {
        this.timeOfGame = timeOfGame;
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

    public gameStatus getStatus() { return status; }

    public String getScore() {
        return score;
    }

    public List<Referee> getGameReferees() {
        return gameReferees;
    }

    public AssociationRepresentative getRepresentative() {
        return representative;
    }


    public void setAssociationRepresentative(AssociationRepresentative representative) {
        this.representative = representative;
    }

    public ArrayList<Event> getEventBook() {
        return eventBook;
    }

    public void setEventBook(ArrayList<Event> eventBook) {
        this.eventBook = eventBook;
    }

    public String getFinalReport() {
        return finalReport;
    }

    public void setFinalReport(String finalReport) {
        this.finalReport = finalReport;
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

/**
 * this class represent the day before a game
 */
class DayToGame extends TimerTask {
    private List<Referee> referees;
    private AssociationRepresentative representative;
    private Team homeTeam;
    private Team awayTeam;
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
        homeTeam.getPage().notifyObservers("DayToGame");
        awayTeam.getPage().notifyObservers("DayToGame");
    }
}

/**
 * this class represent the start of a game
 */
class StartGame extends TimerTask{
    private AssociationRepresentative representative;
    private Team homeTeam;
    private Team awayTeam;
    private Game game;

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
        homeTeam.getPage().notifyObservers("game Start!");
        awayTeam.getPage().notifyObservers("game Start!");
        game.setStatus(Game.gameStatus.active);
        MainSystem.LOG.info("The game between: "+game.getHomeTeam().getName()+" and "+game.getAwayTeam().getName()+ "started");
    }
}

/**
 * this class represent the end of a game
 */
class EndGame extends TimerTask{
    private Team homeTeam;
    private Team awayTeam;
    private Game game;
    private String score;
    private int homeTeamGoals;
    private int awayTeamGoals;

    /**
     * constructor
     * @param homeTeam
     * @param awayTeam
     */
    public EndGame(Team homeTeam, Team awayTeam, Game game, String score) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.game = game;
        this.score = score;
        this.homeTeamGoals=0;
        this.awayTeamGoals=0;
    }

    @Override
    public void run() {
        homeTeam.getPage().notifyObservers("score: "+homeTeam.getName()+" "+ score+" "+awayTeam.getName());
        awayTeam.getPage().notifyObservers("score: "+homeTeam.getName()+" "+ score+" "+awayTeam.getName());
        game.setStatus(Game.gameStatus.finish);
        double moneyFromGame=homeTeam.getStadium().getCapacity()*homeTeam.getStadium().getPrice();
        homeTeam.setBudget(homeTeam.getBudget()+moneyFromGame);
        game.setFinalReport(eventListToReport(game.getEventBook()));
        setStatistic();
        MainSystem.LOG.info("The game between: "+game.getHomeTeam().getName()+" and "+game.getAwayTeam().getName()+ "ended");

    }

    /**
     * create the report from the event list
     * @param eventBook
     * @return
     */
    private String eventListToReport(ArrayList<Event> eventBook) {
        String report="";
        for(Event event:eventBook){
            report += event.evenToString() + "\t";
        }
        return report;
    }

    /**
     * set the statistic from the game to the teams
     */
    private void setStatistic() {
        String[] result=score.split("-");
        homeTeamGoals=Integer.parseInt(result[0]);
        awayTeamGoals=Integer.parseInt(result[1]);
        Statistics homeTeamStatistics = homeTeam.getStatistics();
        Statistics awayTeamStatistics = awayTeam.getStatistics();

        homeTeamStatistics.setGoals(homeTeamGoals);
        homeTeamStatistics.setGc(awayTeamGoals);
        awayTeamStatistics.setGoals(awayTeamGoals);
        awayTeamStatistics.setGc(homeTeamGoals);

        if(homeTeamGoals>awayTeamGoals){
            homeTeamStatistics.setWins();
            awayTeamStatistics.setLoses();
        }
        else if(homeTeamGoals<awayTeamGoals){
            awayTeamStatistics.setWins();
            homeTeamStatistics.setLoses();
        }
        else{
            homeTeamStatistics.setTie();
            awayTeamStatistics.setTie();
        }
        homeTeam.setStatistics(homeTeamStatistics);
        awayTeam.setStatistics(awayTeamStatistics);
    }
}

/**
 * this class represent 5 hours after a game
 */
class CloseGame extends TimerTask{

    Game game;
    /**
     * constructor
     * @param game
     */
    public CloseGame(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        game.setStatus(Game.gameStatus.close);
        MainSystem.LOG.info("The game between: "+game.getHomeTeam().getName()+" and "+game.getAwayTeam().getName()+ "is close");

    }
}
