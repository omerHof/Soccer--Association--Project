package Users;

import Games.Game;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import SystemLogic.Notification;
import Teams.Stadium;
import Teams.Team;
import Teams.TeamPage;
import UserGenerator.AUsersGenerator;
import UserGenerator.SimpleUserGenerator;
import javafx.scene.control.Alert;
import sun.security.util.Password;

import java.util.*;

public class Fan extends User implements Observer {



    //private HashMap<String,PersonalPage> followedPages;
    private ArrayList<PersonalPage>followedPages;
    private HashMap<String,Team> followedTeams;
    private HashMap<String,Team> notificationTeams;
    private boolean pageAlert;
    private String pageMessage;
    private boolean teamAlert;
    private String teamMessage;

    private boolean preGameAlert;
    private String preGameMessage;
    private boolean startGameAlert;
    private String startGameMessage;
    private boolean endGameAlert;
    private String endGameMessage;

    //private Search searcher;

    public Fan(String userName, String password, String fullName, String email) {

        this.password = password;
        this.userName = userName;
        this.userFullName = fullName;
        this.userEmail = email;
        //followedPages = new HashMap<>();
        followedPages = new ArrayList<>();
        followedTeams = new HashMap<>();
        pageAlert = false;
        teamAlert = false;
        preGameAlert=false;
        startGameAlert = false;
        endGameAlert = false;

    }

    public boolean isPageAlert() {
        return pageAlert;
    }

    public void setPageAlert(boolean pageAlert) {
        this.pageAlert = pageAlert;
    }

    public String getPageMessage() {
        return pageMessage;
    }

    public void setPageMessage(String pageMessage) {
        this.pageMessage = pageMessage;
    }

    @Override
    public void update(Observable o, Object arg) {
        DB db = DB.getInstance();
        MainSystem.LOG.info("The user get update");
        if (o instanceof PersonalPage) {
            Team team = (Team) arg;
            String name = (String) ((PersonalPage) o).getName();
            User sender = db.getUserByFullName(name);
            pageMessage = team.getName();
            String message = "new update! " + name + " has moved to " + pageMessage;
            Notification notification = new Notification(sender,message,this);
            notification.send();
        }
        if (o instanceof TeamPage) {
            TeamPage team = (TeamPage) o;
            Team t = db.getTeam(team.getName());
            User managerSender;
            if(t.getManagers().size()!=0) {
                managerSender = team.getManagers().entrySet().stream().findFirst().get().getValue();
            }
            else{
                managerSender = t.getTeamOwners().entrySet().stream().findFirst().get().getValue();
            }

            if (arg instanceof Player) {
                Player player = (Player) arg;
                pageMessage = player.getUserFullName();
                String teamName = player.getCurrentTeamName();
                String message;
                if(team.getName()==teamName) {
                     message = "new update! the player " + pageMessage + " has moved to " + team.getName();
                }
                else{
                     message = "new update! the player " + pageMessage + " has left the team" + team.getName();

                }
                Notification notification = new Notification(managerSender,message,this);
                notification.send();

            } else if (arg instanceof Coach) {
                Coach Coach = (Coach) arg;
                pageMessage = Coach.getUserFullName();
                String message = "new update! the coach " + pageMessage + " has moved to " + team.getName();
                Notification notification = new Notification(managerSender,message,this);
                notification.send();
            }

            else if (arg instanceof Stadium) {
                Stadium stadium = (Stadium) arg;
                pageMessage= stadium.getName();
                String message = "new update! the team " + team.getName() + " has move to the stadium "+pageMessage;
                Notification notification = new Notification(managerSender,message,this);
                notification.send();

            } else if (arg instanceof TeamOwner) {
                TeamOwner TeamOwner = (TeamOwner) arg;
                pageMessage = TeamOwner.getUserFullName();
                String message = "new update! the team owner " + pageMessage + " has moved to " + team.getName();
                Notification notification = new Notification(managerSender,message,this);
                notification.send();


            } else if (arg instanceof Manager) {
                Manager Manager = (Manager) arg;
                pageMessage = Manager.getUserFullName();
                String message = "new update! the manager " + pageMessage + " has moved to " + team.getName();
                Notification notification = new Notification(managerSender,message,this);
                notification.send();

            } else if (arg instanceof String) {

                String updateFromGame = (String)arg;
                String message = updateFromGame;
                Notification notification = new Notification(managerSender,message,this);
                notification.send();

                /*
                if(updateFromGame)
                if (game.getStatus() == Game.gameStatus.preGame) {
                    System.out.println("REMINDER! in 1 day the game between " + homeTeam + " and " + awayTeam + " will start");

                }
                if (game.getStatus() == Game.gameStatus.active) {
                    System.out.println("new update! the game between " + homeTeam + " and " + awayTeam + " has started!");
                }
                if (game.getStatus() == Game.gameStatus.finish) {
                    System.out.println("new update! the game between " + homeTeam + " and " + awayTeam + " has finish in score " + game.getScore());
                }

                 */
            }
        }

    }


/*
    public HashMap<String, PersonalPage> getFollowedPages() {
        return followedPages;
    }


 */
public ArrayList< PersonalPage> getFollowedPages() {
    return followedPages;
}
    //get notification
    /*
    public void getNotificationOnGames(){
        selectTeams();/// the fan selected the teams that he want to get notidications about them
        for(HashMap.Entry<String,Team> team:notificationTeams.entrySet()) {
            team.getValue().addObserver(this);///not sure
        }
        }
     */

    public void followThisPage(PersonalPage page){
        page.addObserver(this);
        //followedPages.put(page.getName(),page);
        followedPages.add(page);

    }
    public void followThisPage(String pageName){
        MainSystem.LOG.info(getUserFullName()+" follow the page of: "+pageName);

        DB DB1;
        DB1=DB.getInstance();


        User user = DB1.getUserByFullName(pageName);
        PersonalPage page;
        if(user instanceof Player){
           page= ((Player) user).getPage();

        }
        else{
            page= ((Coach) user).getPage();
        }
        followThisPage(page);
    }

    public void stopFollowThisPage(String pageName) {

        for (PersonalPage page : followedPages) {
            if (page.getName().equals(pageName)) {

                followedPages.remove(page);
                page.deleteObserver(this);
                break;
            }

        }
        MainSystem.LOG.info(getUserFullName()+" stop follow the page of: "+pageName);

    }
    public void stopFollowAllPages(){
          while (!followedPages.isEmpty()){
              int i=0;
              PersonalPage p =followedPages.get(i);
              followedPages.remove(p);
              p.deleteObserver(this);
              i++;
          }
        MainSystem.LOG.info(getUserFullName()+" stop follow all pages");

    }

    public boolean followTeam(String teamName){
        DB DB1;
        DB1=DB.getInstance();
        Team team = DB1.getTeam(teamName);
        if(team==null){
            return false;
        }
        TeamPage teamPage = team.getPage();
        teamPage.addObserver(this);
        followedTeams.put(teamName,team);
        MainSystem.LOG.info(getUserFullName()+" follow the team : "+teamName);
        return true;
    }
    public void stopFollowTeam(String teamName){ ///to fix bug
        Team team;
        if (followedTeams.containsKey(teamName)) {
            team = followedTeams.remove(teamName);
            TeamPage teamPage = team.getPage();
            teamPage.deleteObserver(this);

        }
        MainSystem.LOG.info(getUserFullName()+" stop follow the team : "+teamName);


    }
    public void stopFollowAllTeams(){
        Iterator it = followedTeams.entrySet().iterator();
        Team team;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String key =(String) pair.getKey();
            Team value = (Team)pair.getValue();
            it.remove();
            TeamPage teamPage = value.getPage();
            teamPage.deleteObserver(this);
            MainSystem.LOG.info(getUserFullName()+" stop follow the team : "+key);

        }

        MainSystem.LOG.info(getUserFullName()+" stop follow all of the teams");

    }

   public void searchPage(String str){
        ///maybe not here
   }
/*
    public ArrayList<String> showFollowPages(){
        ArrayList<String>nameOfThePages=new ArrayList<>();
        for(HashMap.Entry<String,PersonalPage> page:followedPages.entrySet()){
            String key = page.getKey();
            nameOfThePages.add(key);
        }

        return nameOfThePages;
    }


    public ArrayList<String> showFollowTeams(){
        ArrayList<String>teamNames=new ArrayList<>();
        for(HashMap.Entry<String,Team> page:followedTeams.entrySet()){
            String key = page.getKey();
            teamNames.add(key);
        }

        return teamNames;
    }


 */


    public HashMap<String, Team> getFollowedTeams() {
        return followedTeams;
    }




    public HashMap<String, Team> getNotificationTeams() {
        return notificationTeams;
    }



    public boolean isTeamAlert() {
        return teamAlert;
    }

    public void setTeamAlert(boolean teamAlert) {
        this.teamAlert = teamAlert;
    }

    public String getTeamMessage() {
        return teamMessage;
    }

    public void setTeamMessage(String teamMessage) {
        this.teamMessage = teamMessage;
    }
    /*
    public void selectTeams(){
      ArrayList<String>teams= showFollowTeams();
      boolean chooseMore = true;
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        while(chooseMore ==true){
        System.out.println("please select team to get about them Notifications:");
        for (String team :teams) {
                System.out.println(team);
            }
        String choose = sc.nextLine();
        if(teams.contains(choose)){

                notificationTeams.put(choose,getTeamByName(choose));
        }
        else{
                System.out.println("error! the team is not in the followed teams");
            }

            System.out.println("do you want to choose more?");
             choose = sc.nextLine();
             if(choose!="yes"){
                 chooseMore=false;
             }
        }

    }

     */


    /*
    //next iteration

    public void sendComplaint(){

    }
    public void fillForm(){

    }
    public void sendForm(){

    }


    public void watchHistory(){
        //need to get into the log somehow

    }
     */


}
