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

public class DataBase {


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
    private TeamOwner teamOwner1;
    private TeamOwner teamOwner2;
    private TeamOwner teamOwner3;
    private TeamOwner teamOwner4;
    private TeamOwner teamOwner5;
    private TeamOwner teamOwner6;
    private TeamOwner teamOwner7;
    private Manager manager1;
    private Manager manager2;
    private Manager manager3;
    private Manager manager4;
    private Player almostOwner1;
    private Player almostOwner2;
    private Player almostManager1;
    private Coach almostOwner3;
    private Coach almostOwner4;
    private Coach almostManager2;

    private AssociationRepresentative representative;

    private AssociationRepresentative representative1;
    private AssociationRepresentative representative2;
    private AssociationRepresentative representative3;
    private AssociationRepresentative representative4;
    private AssociationRepresentative representative5;

    private Administrator administrator;

    /**
     * constructor
     */
    public DataBase() {

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
        gameInlayPolicy = new OneRoundGamePolicy(teams, 2021);


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

        /** Owners **/
        teamOwner1 = new TeamOwner("teamOwner1", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner2 = new TeamOwner("teamOwner2", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner3 = new TeamOwner("teamOwner3", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner4 = new TeamOwner("teamOwner4", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner5 = new TeamOwner("teamOwner5", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner6 = new TeamOwner("teamOwner6", "1234", "teamOwnerName", "teamOwner.com");
        teamOwner7 = new TeamOwner("teamOwner7", "1234", "teamOwnerName", "teamOwner.com");

        /** Users that yet to be owners/managers **/
        almostOwner1 = new Player("almostOwner1","1234", "playerName", "player.com", null, "player_that_plays");
        almostOwner2 = new Player("almostOwner2","1234", "playerName", "player.com", null, "player_that_plays");
        almostOwner3 = new Coach("almostOwner3","1234", "coachName", "coach.com", "coach_that_coaches");
        almostOwner4 = new Coach("almostOwner4","1234", "coachName", "coach.com", "coach_that_coaches");
        almostManager1 = new Player("almostManager1","1234", "playerName", "player.com", null, "player_that_plays");
        almostManager2 = new Coach("almostManager2","1234", "coachName", "coach.com",  "coach_that_coaches");

        /** Managers **/
        manager1 = new Manager("manager1", "1234", "mmanagerName", "manager.com");
        manager2 = new Manager("manager2", "1234", "mmanagerName", "manager.com");
        manager3 = new Manager("manager3", "1234", "mmanagerName", "manager.com");
        manager4 = new Manager("manager4", "1234", "mmanagerName", "manager.com");

        /**Teams in String**/
        /*
        ArrayList<String> stringTeams = new ArrayList<>();
        String barca = "barca";
        String real = "real";
        String man_u = "man u";
        String man_city = "man city";
        String liverpool = "liverpool";
        String chelsea = "chelsea";
        String roma = "roma";
        String juve = "juve";
        String milan = "milan";
        String inter = "inter";
        stringTeams.add(barca);
        stringTeams.add(real);
        stringTeams.add(man_u);
        stringTeams.add(man_city);
        stringTeams.add(liverpool);
        stringTeams.add(chelsea);
        stringTeams.add(roma);
        stringTeams.add(juve);
        stringTeams.add(milan);
        stringTeams.add(inter);
        */
        /**Referee in String**/
        /*
        ArrayList<String> stringReferees = new ArrayList<>();
        String referee1 = "referee1";
        String referee2 = "referee2";
        String referee3 = "referee3";
        String referee4 = "referee4";
        String referee5 = "referee5";
        String referee6 = "referee6";
        String referee7 = "referee7";
        String referee8 = "referee8";
        String referee9 = "referee9";
        String referee10 = "referee10";
        String referee11 = "referee11";
        String referee12 = "referee12";
        String referee13 = "referee13";
        String referee14 = "referee14";
        String referee15 = "referee15";
        stringReferees.add(referee1);
        stringReferees.add(referee2);
        stringReferees.add(referee3);
        stringReferees.add(referee4);
        stringReferees.add(referee5);
        stringReferees.add(referee6);
        stringReferees.add(referee7);
        stringReferees.add(referee8);
        stringReferees.add(referee9);
        stringReferees.add(referee10);
        stringReferees.add(referee11);
        stringReferees.add(referee12);
        stringReferees.add(referee13);
        stringReferees.add(referee14);
        stringReferees.add(referee15);
        */


        /**AssociationRepresentative in String**/
        /*
        ArrayList<String> stringRepresentatives = new ArrayList<>();
        String representative1 = "representative1";
        String representative2 = "representative2";
        String representative3 = "representative3";
        String representative4 = "representative4";
        String representative5 = "representative5";
        stringRepresentatives.add(representative1);
        stringRepresentatives.add(representative2);
        stringRepresentatives.add(representative3);
        stringRepresentatives.add(representative4);
        stringRepresentatives.add(representative5);
        */


        /**Management**/
        league = new League("Champions league", 10);
        season = new Season(2021, teams, referees, representatives, scorePolicy.getName(), gameInlayPolicy.getName());
        seasons = new ArrayList<>();
        seasons.add(season);
        league.setAllSeasons(seasons);
        //representative = new AssociationRepresentative("representative", "a", "a", "a");
        //representative.addSeasonToLeague("Champions league",2020,scorePolicy.getName(), gameInlayPolicy.getName(),stringTeams,stringReferees,stringRepresentatives);


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

        db.setUser(teamOwner1);
        db.setUser(teamOwner2);
        db.setUser(teamOwner3);
        db.setUser(teamOwner4);
        db.setUser(teamOwner5);
        db.setUser(teamOwner6);
        db.setUser(teamOwner7);
        db.setUser(almostOwner1);
        db.setUser(almostOwner2);
        db.setUser(almostOwner3);
        db.setUser(almostOwner4);
        db.setUser(almostManager1);
        db.setUser(almostManager2);

        db.setUser(manager1);
        db.setUser(manager2);
        db.setUser(manager3);
        db.setUser(manager4);


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

