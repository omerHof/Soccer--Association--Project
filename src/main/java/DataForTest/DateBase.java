package DataForTest;

import LeagueSeasonsManagment.*;
import SystemLogic.DB;
import Teams.Statistics;
import Teams.Team;
import Users.Administrator;
import Users.AssociationRepresentative;
import Users.MainReferee;
import Users.Referee;

import java.util.ArrayList;

import LeagueSeasonsManagment.*;
import SystemLogic.DB;
import Teams.Statistics;
import Teams.Team;
import Users.*;

import java.util.ArrayList;

public class DateBase {


    DB db;

    /**
     * Management
     **/
    private League league;
    private ArrayList<Season> seasons;
    private Season season;


    /**
     * Policies
     **/
    private IGameInlayPolicy gameInlayPolicy;
    private IScorePolicy scorePolicy;

    /**
     * Teams
     **/
    private ArrayList<Team> teams;
    private Team a = new Team("barca");
    private Team b = new Team("real");
    private Team c = new Team("man u");
    private Team d = new Team("man city");
    private Team e = new Team("liverpool");
    private Team f = new Team("chelsea");
    private Team g = new Team("roma");
    private Team h = new Team("juve");
    private Team i = new Team("milan");
    private Team j = new Team("inter");


    /**
     * Statistics
     **/
    private Statistics statisticsA;
    private Statistics statisticsB;
    private Statistics statisticsC;
    private Statistics statisticsD;
    private Statistics statisticsE;
    private Statistics statisticsF;
    private Statistics statisticsG;
    private Statistics statisticsH;
    private Statistics statisticsI;
    private Statistics statisticsJ;


    /**
     * Users
     **/
    private ArrayList<Referee> referees;
    private ArrayList<AssociationRepresentative> representatives;
    private Referee referee1;
    private Referee referee2;
    private Referee referee3;
    private Referee referee4;
    private Referee referee5;
    private Referee referee6;
    private Referee referee7;
    private Referee referee8;
    private Referee referee9;
    private Referee referee10;
    private Referee referee11;
    private Referee referee12;
    private Referee referee13;
    private Referee referee14;
    private Referee referee15;
    private MainReferee mainReferee1;
    private MainReferee mainReferee2;
    private MainReferee mainReferee3;
    private MainReferee mainReferee4;
    private MainReferee mainReferee5;

    private AssociationRepresentative representative1;
    private AssociationRepresentative representative2;
    private AssociationRepresentative representative3;
    private AssociationRepresentative representative4;
    private AssociationRepresentative representative5;

    private Administrator administrator;

    /**
     * constructor
     */
    public DateBase() {

        db = DB.getInstance();

        /**Policies-score**/
        scorePolicy = new RegularScorePolicy();

        /**Teams**/
        teams = new ArrayList<>();
        Team a = new Team("barca");
        Team b = new Team("real");
        Team c = new Team("man u");
        Team d = new Team("man city");
        Team e = new Team("liverpool");
        Team f = new Team("chelsea");
        Team g = new Team("roma");
        Team h = new Team("juve");
        Team i = new Team("milan");
        Team j = new Team("inter");

        /**Statistics**/
        statisticsA = new Statistics(scorePolicy);
        a.setStatistics(statisticsA);
        statisticsB = new Statistics(scorePolicy);
        b.setStatistics(statisticsB);
        statisticsC = new Statistics(scorePolicy);
        c.setStatistics(statisticsC);
        statisticsD = new Statistics(scorePolicy);
        d.setStatistics(statisticsD);
        statisticsE = new Statistics(scorePolicy);
        e.setStatistics(statisticsE);
        statisticsF = new Statistics(scorePolicy);
        f.setStatistics(statisticsF);
        statisticsG = new Statistics(scorePolicy);
        g.setStatistics(statisticsG);
        statisticsH = new Statistics(scorePolicy);
        h.setStatistics(statisticsH);
        statisticsI = new Statistics(scorePolicy);
        i.setStatistics(statisticsI);
        statisticsJ = new Statistics(scorePolicy);
        j.setStatistics(statisticsJ);

        teams.add(a);
        teams.add(b);
        teams.add(c);
        teams.add(d);
        teams.add(e);
        teams.add(f);
        teams.add(g);
        teams.add(h);
        teams.add(i);
        teams.add(j);


        /**Policies-game**/
        gameInlayPolicy = new OneRoundGamePolicy(teams, 2020);


        /**Users**/
        referees = new ArrayList<>();
        referee1 = new Referee("referee1", "a", "a", "a", "a");
        referees.add(referee1);
        referee2 = new Referee("referee2", "a", "a", "a", "a");
        referees.add(referee2);
        referee3 = new Referee("referee3", "a", "a", "a", "a");
        referees.add(referee3);
        referee4 = new Referee("referee4", "a", "a", "a", "a");
        referees.add(referee4);
        referee5 = new Referee("referee5", "a", "a", "a", "a");
        referees.add(referee5);
        referee6 = new Referee("referee6", "a", "a", "a", "a");
        referees.add(referee6);
        referee7 = new Referee("referee7", "a", "a", "a", "a");
        referees.add(referee7);
        referee8 = new Referee("referee8", "a", "a", "a", "a");
        referees.add(referee8);
        referee9 = new Referee("referee9", "a", "a", "a", "a");
        referees.add(referee9);
        referee10 = new Referee("referee10", "a", "a", "a", "a");
        referees.add(referee10);
        referee11 = new Referee("referee11", "a", "a", "a", "a");
        referees.add(referee11);
        referee12 = new Referee("referee12", "a", "a", "a", "a");
        referees.add(referee12);
        referee13 = new Referee("referee13", "a", "a", "a", "a");
        referees.add(referee13);
        referee14 = new Referee("referee14", "a", "a", "a", "a");
        referees.add(referee14);
        referee15 = new Referee("referee15", "a", "a", "a", "a");
        referees.add(referee15);
        mainReferee1 = new MainReferee("mainReferee1", "a", "a", "a", "a");
        mainReferee2 = new MainReferee("mainReferee2", "a", "a", "a", "a");
        mainReferee3 = new MainReferee("mainReferee3", "a", "a", "a", "a");
        mainReferee4 = new MainReferee("mainReferee4", "a", "a", "a", "a");
        mainReferee5 = new MainReferee("mainReferee5", "a", "a", "a", "a");

        representatives = new ArrayList<>();
        representative1 = new AssociationRepresentative("representative1", "a", "a", "a");
        representative2 = new AssociationRepresentative("representative2", "a", "a", "a");
        representative3 = new AssociationRepresentative("representative3", "a", "a", "a");
        representative4 = new AssociationRepresentative("representative4", "a", "a", "a");
        representative5 = new AssociationRepresentative("representative5", "a", "a", "a");

        representatives.add(representative1);
        representatives.add(representative2);
        representatives.add(representative3);
        representatives.add(representative4);
        representatives.add(representative5);

        administrator = new Administrator("The King", "1234", "Oren Hason", "OrenHason@gmail.com");


        /**Management**/
        league = new League("Champions league", 10);
        season = new Season(2020, teams, referees, representatives, scorePolicy.getName(), gameInlayPolicy.getName());
        seasons = new ArrayList<>();
        seasons.add(season);
        league.setAllSeasons(seasons);

        /**DB**/

        db.setLeague(league);

        db.setUser(mainReferee1);
        db.setUser(mainReferee2);
        db.setUser(mainReferee3);
        db.setUser(mainReferee4);
        db.setUser(mainReferee5);

        db.setUser(referee1);
        db.setUser(referee2);
        db.setUser(referee3);
        db.setUser(referee4);
        db.setUser(referee5);
        db.setUser(referee6);
        db.setUser(referee7);
        db.setUser(referee8);
        db.setUser(referee9);
        db.setUser(referee10);
        db.setUser(referee11);
        db.setUser(referee12);
        db.setUser(referee13);
        db.setUser(referee14);
        db.setUser(referee15);

        db.setUser(representative1);
        db.setUser(representative2);
        db.setUser(representative3);
        db.setUser(representative4);
        db.setUser(representative5);

        db.setUser(administrator);

        db.setTeam(a);
        db.setTeam(b);
        db.setTeam(c);
        db.setTeam(d);
        db.setTeam(e);
        db.setTeam(f);
        db.setTeam(g);
        db.setTeam(h);
        db.setTeam(i);
        db.setTeam(j);
    }

}

