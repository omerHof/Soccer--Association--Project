package Users;

import Games.Game;
import SystemLogic.DB;
import Teams.Stadium;
import Teams.Team;
import Teams.TeamPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class FanTest {
    Fan f1;
    Team t1;
    Player p1;
    Coach c1;
    DB DBTest;


    PlayerPersonalPage messiPage;
    CoachPersonalPage klingerPage;
    //LocalDate localDate;



     HashMap<String,PersonalPage> followedPagesTest = new HashMap<>();
     HashMap<String,Team> followedTeamsTest = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        LocalDate localDate2 = LocalDate.of(1999,1,1);
        f1 = new Fan("ido747","12345","ido kestenbaum","ido747@gmail.com");
        t1 = new Team("hapoel tel aviv");
        p1 = new Player("messi","12345","leo messi","leo123@gmail.com",localDate2,"striker");
        c1 = new Coach("klinger","123","nir klinger","nir123@gmail.com","head coach");
        DBTest = DB.getInstance();
        DBTest.addUser(f1);
        DBTest.addUser(p1);
        DBTest.addUser(c1);
        DBTest.addTeam(t1);


/*
        p1.createPersonalPage(167,70,10,new Team("barcelona"));
         messiPage = p1.getPage();
        f1.followThisPage(messiPage);

        c1.createCoachPersonalPage(localDate2,t1);
         klingerPage =c1.getPage();
        f1.followThisPage(klingerPage);


 */







    }

    @After
    public void tearDown() throws Exception {
       DBTest.removeUser(f1.getUserName());
        DBTest.removeUser(p1.getUserName());
        DBTest.removeUser(c1.getUserName());
        DBTest.removeTeam(t1.getName());
        f1 =null;
        t1=null;
        p1=null;
        c1=null;
    }

    @Test
    public void isPageAlert() {
    }

    @Test
    public void setPageAlert() {
    }

    @Test
    public void getPageMessage() {
    }

    @Test
    public void setPageMessage() {
    }

    @Test
    public void update() {
        Team barca = new Team("barcelona");
        DBTest.addTeam(barca);
        Team real = new Team("real madrid");
        DBTest.addTeam(real);
        TeamPage barcaPage = barca.createPage("team in spain","spain");
        TeamPage realPage = real.createPage("team in spain","spain");
       TeamPage hapoelPage = t1.createPage("team in israel","israel");

       TeamOwner hapoelOwner = new TeamOwner("aa","aa","nisanov","dsdsadas");
        TeamOwner barcaOwner = new TeamOwner("arrrra","aa","bartulemeo","dsdsadffas");
        TeamOwner realOwner = new TeamOwner("222222","aa","peres","dsdsadffas");
        DBTest.addUser(hapoelOwner);
        DBTest.addUser(barcaOwner);
        DBTest.addUser(realOwner);
        t1.addTeamOwner(hapoelOwner.getUserFullName());
        barca.addTeamOwner(barcaOwner.getUserFullName());
        real.addTeamOwner(realOwner.getUserFullName());

        PlayerPersonalPage messiPage = p1.createPersonalPage(167,65,10,barca.getName());
        LocalDate date = LocalDate.of(1965,12,12);
        CoachPersonalPage klingerPage =c1.createCoachPersonalPage(date,null);

/*
        ////followed player move team
        f1.followThisPage(p1.getUserFullName());

        t1.addPlayer(p1.getUserFullName());
        //p1.setCurrentTeam(t1.getName());
        assertEquals(f1.getReceivedNotifications().get(0).getContext(),"new update! leo messi has moved to hapoel tel aviv");
        assertEquals(f1.getReceivedNotifications().size(),1);
        barca.addPlayer(p1.getUserFullName());
        assertEquals(f1.getReceivedNotifications().get(1).read(),"new update! leo messi has moved to barcelona");
        assertEquals(f1.getReceivedNotifications().get(1).getSender(),p1);

        ////followed coach move team

        f1.followThisPage(c1.getUserFullName());
        barca.addCoach(c1);
        assertEquals(f1.getReceivedNotifications().get(2).read(),"new update! nir klinger has moved to barcelona");
        assertEquals(f1.getReceivedNotifications().get(2).getSender(),c1);
        assertEquals(barca.getPlayers().size(),1);
        assertEquals(barca.getCoaches().size(),1);
        assertEquals(t1.getCoaches().size(),0);
        assertEquals(t1.getPlayers().size(),0);

        f1.stopFollowAllPages();
        assertEquals(f1.getFollowedPages().size(),0);


 */

        ///follow team
        f1.followTeam(t1.getName());
        t1.addPlayer(p1);
        assertEquals(f1.getReceivedNotifications().get(0).getContext(),"new update! the player leo messi has moved to hapoel tel aviv");
        t1.addCoach(c1.getUserFullName());
        assertEquals(f1.getReceivedNotifications().get(1).getContext(),"new update! the coach nir klinger has moved to hapoel tel aviv");
        Stadium stadium = new Stadium(12222,12222,12222);
        stadium.setName("bloomfield");
        t1.setStadium(stadium);
        assertEquals(f1.getReceivedNotifications().get(2).getContext(),"new update! the team hapoel tel aviv has move to the stadium "+stadium.getName());

        Manager manager = new Manager("ssss","dsds","managerr","fsdfsdf");
        DBTest.addUser(manager);
        t1.addManager(manager);
        assertEquals(f1.getReceivedNotifications().get(3).getContext(),"new update! the manager managerr has moved to hapoel tel aviv");
        f1.followTeam(barca.getName());
        barca.addPlayer(p1);
        assertEquals(f1.getReceivedNotifications().get(4).getContext(),"new update! the player leo messi has left the team hapoel tel aviv");
        assertEquals(f1.getReceivedNotifications().get(5).getContext(),"new update! the player leo messi has moved to barcelona");

        barca.addCoach(c1);
        assertEquals(f1.getReceivedNotifications().get(6).getContext(),"new update! the coach nir klinger has left the team hapoel tel aviv");
        assertEquals(f1.getReceivedNotifications().get(7).getContext(),"new update! the coach nir klinger has moved to barcelona");

        Stadium stadium2 = new Stadium(12222,12222,12222);
        stadium2.setName("bloomfield2");
        t1.setStadium(stadium2);
        assertEquals(f1.getReceivedNotifications().get(8).getContext(),"new update! the team hapoel tel aviv has move to the stadium "+stadium2.getName());


        barca.addManager(manager);
        assertEquals(f1.getReceivedNotifications().get(9).getContext(),"new update! the manager managerr has left  hapoel tel aviv");
        assertEquals(f1.getReceivedNotifications().get(10).getContext(),"new update! the manager managerr has moved to barcelona");
        barca.removeManager(manager.getUserFullName());

        assertEquals(f1.getReceivedNotifications().get(11).getContext(),"new update! the manager managerr has left  barcelona");
        assertEquals(f1.getReceivedNotifications().get(11).getSender().getUserFullName(),"bartulemeo");













        /*

        ////// game notifications

        f1.followTeam(barca.getName());
        LocalDateTime gameDate = LocalDateTime.of(2020,4,19,22,32);
        Game g = new Game(barca,real,gameDate);
        barca.addGame(g);
        real.addGame(g);
        g.setStatus(Game.gameStatus.active);
       System.out.println(f1.getReceivedNotifications().get(4).getContext());


         */

        // assertEquals(f1.getReceivedNotifications().get(4).getContext(),"DayToGame between: "+barca.getName()+" and "+real.getName());




        //t1.removePlayer(p1);
       // System.out.println(f1.getReceivedNotifications().get(1).getContext());










    }

    @Test
    public void getFollowedPages() {
        p1.createPersonalPage(167,65,10,"barcelona");
        assertEquals(0,f1.getFollowedPages().size());
        PlayerPersonalPage page = p1.getPage();
        f1.followThisPage(page);
        assertEquals(1,f1.getFollowedPages().size());
        LocalDate date1 = LocalDate.of(1970,5,5);
        c1.createCoachPersonalPage(date1,t1.getName());
        f1.followThisPage("nir klinger");
        assertEquals(2,f1.getFollowedPages().size());

        /*
        //before changes


        followedPagesTest.put("leo messi",messiPage);
        followedPagesTest.put("nir klinger",klingerPage);
        assertEquals(f1.getFollowedPages(),followedPagesTest);
        assertTrue(f1.getFollowedPages().size()==followedPagesTest.size());


        ///after changes

        f1.stopFollowThisPage("leo messi");
        assertTrue(f1.getFollowedPages().size()!=followedPagesTest.size());


         */

    }

    @Test
    public void followThisPage() {
        int numberOfPages = f1.getFollowedPages().size();
        assertEquals(2,numberOfPages);
        System.out.println("1 good");
        LocalDate localDate2 = LocalDate.of(1999,1,1);


        ///create new pages
        Coach klopp = new Coach("jorgenklopp","123","jorgen klopp","jo@gmail.com","head coach");
        Team liverpool = new Team("liverpool");
        CoachPersonalPage kloppPage = klopp.createCoachPersonalPage(localDate2,liverpool.getName());
        DBTest.addTeam(liverpool);
        DBTest.addUser(klopp);

        Player mane = new Player("mane123","2222","sadio mane","sadio12@gmail.com",null,"inside forward");
        DBTest.addUser(mane);
        mane.createPersonalPage(173,70,10,liverpool.getName());


        //f1.followThisPage("sadio mane");
        f1.followThisPage(kloppPage);
        assertEquals(3,f1.getFollowedPages().size());

        f1.followThisPage("sadio mane");
        assertEquals(4,f1.getFollowedPages().size());




    }

    @Test
    public void followThisPage1() {

    }

    @Test
    public void stopFollowThisPage() {


        ///before change
        assertEquals(2, f1.getFollowedPages().size());


        ///after change
       f1.stopFollowThisPage(messiPage.getName());
        assertEquals(1,f1.getFollowedPages().size());

        klingerPage.setName("klinger");
        f1.stopFollowThisPage("klinger");
        assertEquals(0,f1.getFollowedPages().size());


    }

    @Test
    public void stopFollowAllPages() {
        ///before change
        assertEquals(2, f1.getFollowedPages().size());
        ///after change
        f1.stopFollowAllPages();
        assertEquals(0,f1.getFollowedPages().size());



    }

    @Test
    public void followTeam() {
        ///before change
        assertEquals(0, f1.getFollowedTeams().size());

        ///after change
        f1.followTeam("hapoel tel aviv");
        assertEquals(1, f1.getFollowedTeams().size());

        Team team = new Team("barcelona");
        DBTest.addTeam(team);
        f1.followTeam("barcelona");
        assertEquals(2, f1.getFollowedTeams().size());

    }

    @Test
    public void stopFollowTeam() {

        f1.followTeam("hapoel tel aviv");
        assertEquals(1, f1.getFollowedTeams().size());

        f1.stopFollowTeam("hapoel tel aviv");
        assertEquals(0,f1.getFollowedTeams().size());

    }

    @Test
    public void stopFollowAllTeams() {
        f1.followTeam("hapoel tel aviv");
        Team team = new Team("maccabi haifa");
        DBTest.addTeam(team);
        f1.followTeam("maccabi haifa");
        assertEquals(2,f1.getFollowedTeams().size());
        System.out.println(" test 1 work");

        Team team2 = new Team("maccabi tel aviv");
        DBTest.addTeam(team2);
        f1.followTeam(team2.getName());
        f1.stopFollowAllTeams();
        assertEquals(0,f1.getFollowedTeams().size());


    }

    @Test
    public void searchPage() {
    }

    @Test
    public void getFollowedTeams() {
        f1.followTeam("hapoel tel aviv");
        followedTeamsTest.put(t1.getName(),t1);

        assertEquals(f1.getFollowedTeams(),followedTeamsTest);
    }


    @Test
    public void isTeamAlert() {
    }

    @Test
    public void setTeamAlert() {
    }

    @Test
    public void getTeamMessage() {
    }

    @Test
    public void setTeamMessage() {
    }

    @Test
    public void getTeamByName() {
    }

    @Test
    public void watchDetails() {
        String[] testDetails = {"ido kesttenbaum","ido747","12345","ido747@gmail.com"};
        //before changes
        assertEquals(testDetails[0],f1.watchDetails()[0]);
        assertEquals(testDetails[1],f1.watchDetails()[1]);
        assertEquals(testDetails[2],f1.watchDetails()[2]);
        assertEquals(testDetails[3],f1.watchDetails()[3]);

        f1.setUserName("idok");
        assertFalse(testDetails[1].equals(f1.watchDetails()[1]));
        assertTrue(testDetails[2].equals(f1.watchDetails()[2]));




    }
}