package Users;

import Games.Game;
import SystemLogic.DB;
import SystemLogic.Notification;
import Teams.Team;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdministratorTest {
    Administrator ad;
    Team team1;
    Team team2;
    Player player;
    Coach coach;
    TeamOwner owner;
    Referee referee;
    AssociationRepresentative assocRep;
    Manager manager;
    Fan fan;
    DB db;


    @Before
    public void setUp() throws Exception {
        ad = new Administrator("addd","bestAD","aaa","dsdasdasd");
        team1 = new Team("hapoel pardesia");
        team2 = new Team("hapoel beer sheva");

        LocalDate date1 = LocalDate.of(1985,3,3);
        player = new Player("leo","leo","leo messi","dsdsa",date1,"srtiker");
        coach = new Coach("pep","12121","pep guardiola","dsdas","head coach");
        owner = new TeamOwner("bigboss","1212","bibi","dddd");
        referee = new Referee("ref","reff","eli hakmon","eee","A");
        assocRep = new AssociationRepresentative("aa","aa","oren hason","aaaaaaa");
        manager = new Manager("bestManager","nnnn","someone","idiididi");
        fan = new Fan("ido747","1222","ido kestenbaum","ido747@gmsil.com");
        db = DB.getInstance();
        db.addTeam(team1);
        db.addTeam(team2);

        db.addUser(ad);
        db.addUser(player);
        db.addUser(coach);
        db.addUser(owner);
        db.addUser(referee);
        db.addUser(assocRep);
        db.addUser(manager);
        db.addUser(fan);



    }

    @After
    public void tearDown() throws Exception {
        db.removeUser(ad.getUserFullName());
        db.removeUser(player.getUserFullName());
        db.removeUser(coach.getUserFullName());
        db.removeUser(owner.getUserFullName());
        db.removeUser(referee.getUserFullName());
        db.removeUser(assocRep.getUserFullName());
        db.removeUser(manager.getUserFullName());
        db.removeUser(fan.getUserFullName());
        db.removeTeam(team1.getName());
        db.removeTeam(team2.getName());


    }

    @Test
    public void closeTeamForPermanent() {
        LocalDateTime date = LocalDateTime.of(2020,4,22,17,00);
        Game g = new Game(team1,team2,date);
        team1.addGame(g);
        team2.addGame(g);

        ad.closeTeamForPermanent(team1.getName());
        assertEquals(team1.getStatus(), Team.teamStatus.active);

       // ad.closeTeamForPermanent(team3.getName());
       // assertEquals(team3.getStatus(), Team.teamStatus.PermanentlyClosed);

        Team team3 = new Team("maccabi netanya");
        db.addTeam(team3);
        team3.addManager(manager);
        team3.addTeamOwner(owner);
        ad.closeTeamForPermanent(team3.getName());

        /* the norifications
         */
        ArrayList<Notification> notifications = manager.getReceivedNotifications();
        assertEquals(notifications.get(0).getContext(), "the team maccabi netanya is permanently closed" );



    }

    @Test
    public void deleteUserFromSystem() {
        /*
        AssociationRepresentative
         */
        AssociationRepresentative assocrep2 = new AssociationRepresentative("pp","pp","ppp","dsdasdas");
        db.addUser(assocrep2);
        ad.deleteUserFromSystem(assocRep.getUserFullName());
        assertEquals(null,db.getUserByFullName("oren hason"));

          /*
        administrator
         */
          ad.deleteUserFromSystem(ad.getUserFullName());
          Administrator admin = new Administrator("d","d","dddd","ddd");
          db.addUser(admin);
          ad.deleteUserFromSystem(admin.getUserFullName());
          assertEquals(1,db.checkQuantityOfUsersByType("Administrator"));

    }

    @Test
    public void watchLog() {
    }
}