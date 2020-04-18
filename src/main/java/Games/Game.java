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

/**
 * this class represent a game. game have status and it change by the tine of the game.
 */
public class Game extends Observable {
    /**
     * *****the status that game can be:*******
     * preGame: before the game start
     * active: when the game playing
     * finish: when the game finished
     * close: 5 hours after the game finished
     */
    public enum gameStatus {
        preGame, active, finish, close
    }

    private gameStatus status;
    private Team homeTeam;
    private Team awayTeam;
    private String score;
    private ArrayList<Event> eventBook;
    private List<Referee> gameReferees;
    private AssociationRepresentative representative;
    private String finalReport;
    private LocalDateTime timeOfGame;
    private Timer timer;

    /**
     * constructor
     *
     * @param homeTeam
     * @param awayTeam
     * @param timeOfGame
     */
    public Game(Team homeTeam, Team awayTeam, LocalDateTime timeOfGame) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameReferees = new LinkedList<>();
        this.timeOfGame = timeOfGame;
        this.timer = new Timer();
        this.status = gameStatus.preGame;
        setAlarms();//set alarm to Stakeholders about the game
        this.eventBook = new ArrayList<>();
        this.score = "0-0";
    }

    /**
     * set the alarms before, after and on the game to all Stakeholders
     */
    public void setAlarms() {
        dayToGame();//todo is problem to test because its negative time
        startGame();
        endGame();
        closeGame();
    }

    /**
     * set alarms day before the game
     */
    private void dayToGame() {
        LocalDateTime dayBefore = timeOfGame.minus(1, ChronoUnit.SECONDS);//todo change seconds to days
        DayToGame dayToGame = new DayToGame(gameReferees, representative, homeTeam, awayTeam);
        LocalDateTime from = LocalDateTime.now();
        Duration duration = Duration.between(from, dayBefore);
        timer.schedule(dayToGame, duration.getSeconds() * 1000);
    }

    /**
     * set alarms when the game start
     */
    private void startGame() {
        LocalDateTime GameTime = timeOfGame;
        StartGame startGame = new StartGame(representative, homeTeam, awayTeam, this);
        LocalDateTime from = LocalDateTime.now();
        Duration duration = Duration.between(from, GameTime);
        timer.schedule(startGame, duration.getSeconds() * 1000);
    }

    /**
     * set alarms when the game start
     */
    private void endGame() {
        LocalDateTime GameEndTime = timeOfGame.plus(3, ChronoUnit.SECONDS);//todo change to 90 minutes
        EndGame endGame = new EndGame(homeTeam, awayTeam, this, score);
        LocalDateTime from = LocalDateTime.now();
        Duration duration = Duration.between(from, GameEndTime);
        timer.schedule(endGame, duration.getSeconds() * 1000);
    }

    /**
     * set alarms when the game close
     */
    private void closeGame() {
        LocalDateTime closeGameTime = timeOfGame.plus(6, ChronoUnit.SECONDS);//todo change to 6.5 hours
        CloseGame closeGame = new CloseGame(homeTeam, awayTeam, this, score);
        LocalDateTime from = LocalDateTime.now();
        Duration duration = Duration.between(from, closeGameTime);
        timer.schedule(closeGame, duration.getSeconds() * 1000);
    }

    /**********getters and setters**********/

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

    public LocalDateTime getGameDate() {
        return timeOfGame;
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

    public void setGameDate(LocalDateTime gameDate) {
        this.timeOfGame = gameDate;
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

    public void addEvent(Event event) {
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
    public DayToGame(List<Referee> referees, AssociationRepresentative representative, Team homeTeam, Team awayTeam) {
        this.referees = referees;
        this.representative = representative;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    @Override
    public void run() {
        //homeTeam.getPage().notifyObservers("DayToGame");//todo add
        //awayTeam.getPage().notifyObservers("DayToGame");//todo add
    }
}

/**
 * this class represent the start of a game
 */
class StartGame extends TimerTask {
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
    public StartGame(AssociationRepresentative representative, Team homeTeam, Team awayTeam, Game game) {
        this.representative = representative;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.game = game;
    }

    @Override
    public void run() {
        //homeTeam.getPage().notifyObservers("game Start!");//todo
        //awayTeam.getPage().notifyObservers("game Start!");//todo
        game.setStatus(Game.gameStatus.active);
        MainSystem.LOG.info("The game between: " + game.getHomeTeam().getName() + " and " + game.getAwayTeam().getName() + " started");
    }
}

/**
 * this class represent the end of a game
 */
class EndGame extends TimerTask {
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
     * @param game
     * @param score
     */
    public EndGame(Team homeTeam, Team awayTeam, Game game, String score) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.game = game;
        this.score = score;
        this.homeTeamGoals = 0;
        this.awayTeamGoals = 0;
    }

    @Override
    public void run() {
        //homeTeam.getPage().notifyObservers("score: "+homeTeam.getName()+" "+ score+" "+awayTeam.getName());//todo
        //awayTeam.getPage().notifyObservers("score: "+homeTeam.getName()+" "+ score+" "+awayTeam.getName());//todo
        game.setStatus(Game.gameStatus.finish);
        //double moneyFromGame=homeTeam.getStadium().getCapacity()*homeTeam.getStadium().getPrice();//todo now there is'nt stadium
        //homeTeam.setBudget(homeTeam.getBudget()+moneyFromGame);
        game.setScore("3-2");//todo remove
        setStatistic();
        game.setFinalReport(eventListToReport(game.getEventBook()));
        MainSystem.LOG.info("The game between: " + game.getHomeTeam().getName() + " and " + game.getAwayTeam().getName() + " ended. the score: " + game.getScore());
    }

    /**
     * set the statistic from the game to the teams
     */
    private void setStatistic() {
        String[] result = game.getScore().split("-");
        homeTeamGoals = Integer.parseInt(result[0]);
        awayTeamGoals = Integer.parseInt(result[1]);
        Statistics homeTeamStatistics = homeTeam.getStatistics();
        Statistics awayTeamStatistics = awayTeam.getStatistics();

        homeTeamStatistics.setGoals(homeTeamGoals);
        homeTeamStatistics.setGc(awayTeamGoals);
        awayTeamStatistics.setGoals(awayTeamGoals);
        awayTeamStatistics.setGc(homeTeamGoals);

        if (homeTeamGoals > awayTeamGoals) {
            homeTeamStatistics.setWins();
            awayTeamStatistics.setLoses();
        } else if (homeTeamGoals < awayTeamGoals) {
            awayTeamStatistics.setWins();
            homeTeamStatistics.setLoses();
        } else {
            homeTeamStatistics.setTie();
            awayTeamStatistics.setTie();
        }
        homeTeam.setStatistics(homeTeamStatistics);
        awayTeam.setStatistics(awayTeamStatistics);
    }

    /**
     * create the report from the event list
     * @param eventBook
     * @return report
     */
    private String eventListToReport(ArrayList<Event> eventBook) {
        String report = "";
        for (Event event : eventBook) {
            report += event.evenToString() + "\t";
        }
        return report;
    }
}

/**
 * this class represent 5 hours after a game
 */
class CloseGame extends TimerTask {
    private Team homeTeam;
    private Team awayTeam;
    private Game game;
    private String score;

    /**
     * constructor
     * @param homeTeam
     * @param awayTeam
     * @param game
     */
    public CloseGame(Team homeTeam, Team awayTeam, Game game, String score) {
        this.game = game;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
    }

    @Override
    public void run() {
        game.setStatus(Game.gameStatus.close);
        MainSystem.LOG.info("The game between: " + game.getHomeTeam().getName() + " and " + game.getAwayTeam().getName() + " is close");
    }
}