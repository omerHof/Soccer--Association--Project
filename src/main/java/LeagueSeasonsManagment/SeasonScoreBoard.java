package LeagueSeasonsManagment;

import Teams.Team;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class SeasonScoreBoard {

    private ArrayList<Team> teams;
    private ArrayList<Team> table;
    private IScorePolicy policy;
    private LocalDateTime firstGameDate;
    private int numOfWeeks;

    /**
     * constructor
     * @param teams
     * @param policy
     * @param firstGameDate
     */
    public SeasonScoreBoard(ArrayList<Team> teams,IScorePolicy policy, LocalDateTime firstGameDate, int numOfWeeks) {
        this.teams = teams;
        this.policy= policy;
        this.table = initTable();
        this.firstGameDate = firstGameDate;
        this.numOfWeeks = numOfWeeks;
        updateTable();
    }

    /**
     * get table
     * @return
     */
    public ArrayList<Team> getTable() {
        return table;
    }

    /**
     * set table
     * @param table
     */
    public void setTable(ArrayList<Team>table) {
        this.table = table;
    }

    /**
     * get policy
     * @return
     */
    public IScorePolicy getPolicy() {
        return policy;
    }

    /**
     * set policy
     * @param policy
     */
    public void setPolicy(IScorePolicy policy) {
        this.policy = policy;
    }

    /**
     * init table
     * @return
     */
    private ArrayList<Team> initTable() {
        ArrayList<Team> table= new ArrayList();
        for(Team team:teams){
            //Statistics statistics= new Statistics(policy);
            team.getStatistics().setNewSeasonStatistics(policy);
            table.add(team);
        }

        return table;
    }

    /**
     * sort table by chosen score policy
     */
    public void sortByValue()
    {
        Collections.sort(table);
    }

    public Team getTeamByName(String name){
        for(Team team:table){
            if(team.getName().equals(name)){
                return team;
            }
        }
        return null;
    }

    /**
     * print table
     */
    public void showTable(){
        int i=1;
        System.out.println("Table:");
        for(Team team:table){
            System.out.println(i+". Name: "+ team.getName()+" | score: "+team.getStatistics().getScore()+
                    " | w:"+ team.getStatistics().getWins()+" | d: "+ team.getStatistics().getTie()+" | l: "+ team.getStatistics().getLoses()+
                    " | goals:"+team.getStatistics().getGoals()+
                    " | goals c:"+ team.getStatistics().getGc()+" | dif: "+ team.getStatistics().getDif());
            i++;
        }
    }

    /**
     *
     */
   public void updateTable() {
        try {
            LocalDateTime timeToUpdate;
            timeToUpdate = firstGameDate.plus(2, ChronoUnit.HOURS);
            LocalDateTime from = LocalDateTime.now();
            Duration duration = Duration.between(from, timeToUpdate);
            UpdateTable updateTable = new UpdateTable(duration.getSeconds(), this, new AtomicInteger(numOfWeeks));
            updateTable.beep();
        }catch (Exception e){

        }
   }
}
/**
 * this class represent the time to update the score board
 */

class UpdateTable {

    long delay;
    private AtomicInteger count;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    SeasonScoreBoard scoreBoard;

    public UpdateTable(Long tDelay,SeasonScoreBoard scoreBoard1, AtomicInteger numOfWeeks) {
        delay = tDelay;
        scoreBoard = scoreBoard1;
        count = numOfWeeks;
    }
    public void beep() {
        final Runnable beeper = new Runnable() {
            public void run() {
                count.getAndDecrement();
                scoreBoard.sortByValue();

                if (count.get() == 0) {
                    scheduler.shutdown();
                }
            }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(
                beeper, delay, 7, DAYS);
    }
}

