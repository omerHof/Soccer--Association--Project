package Users;

import SystemLogic.DB;
import SystemLogic.MainSystem;
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
        MainSystem.LOG.info("The user  get update");
        if(o instanceof PersonalPage){
            Team team = (Team)arg;
            String name = (String)((PersonalPage) o).getName();
            pageAlert=true;
            pageMessage= team.getName();
            System.out.println("new update! "+name+" has moved to "+pageMessage);
        }
        if(o instanceof TeamPage){
            teamAlert=true;
            TeamPage team = (TeamPage)o;
            if(arg instanceof Player){
                Player player=( Player)arg;
                pageMessage= player.getUserFullName();
                System.out.println("new update! the player "+pageMessage+" has moved to "+team.getName());

            }

            else if(arg instanceof Coach){
                Coach Coach=( Coach)arg;
                pageMessage= Coach.getUserFullName();
                System.out.println("new update! the Coach "+pageMessage+" has moved to "+team.getName());
            }
            else if(arg instanceof Stadium){
                Stadium stadium=( Stadium)arg;
               // pageMessage= stadium.getName();
                System.out.println("new update! the team "+team.getName()+" has a new stadium");

            }
            else if(arg instanceof TeamOwner){
                TeamOwner TeamOwner=( TeamOwner)arg;
                pageMessage= TeamOwner.getUserFullName();
                System.out.println("new update! the TeamOwner "+pageMessage+" has moved to "+team.getName());

            }
            else if(arg instanceof Manager){
                Manager Manager=( Manager)arg;
                pageMessage= Manager.getUserFullName();
                System.out.println("new update! the Manager "+pageMessage+" has moved to "+team.getName());

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
        MainSystem.LOG.info(getUserFullName()+" stop follow the page of: "+pageName);
        // PersonalPage page;
           /*
            if (followedPages.containsKey(pageName)) {
                page = followedPages.remove(pageName);
                page.deleteObserver(this);
            }

            */


        for (PersonalPage page : followedPages) {
            if (page.getName().equals(pageName)) {

                followedPages.remove(page);
                page.deleteObserver(this);
                break;
            }

        }
    }
    public void stopFollowAllPages(){
        MainSystem.LOG.info(getUserFullName()+" stop follow all pages");

/*
        for(HashMap.Entry<String,PersonalPage> page:followedPages.entrySet()){
            String key = page.getKey();
            stopFollowThisPage(key);
        }

 */

          while (!followedPages.isEmpty()){
              int i=0;
              PersonalPage p =followedPages.get(i);
              followedPages.remove(p);
              p.deleteObserver(this);
              i++;
          }
    }

    public void followTeam(String teamName){
        MainSystem.LOG.info(getUserFullName()+" follow the team : "+teamName);
        DB DB1;
        DB1=DB.getInstance();
        Team team = DB1.getTeam(teamName);
        TeamPage teamPage = team.getPage();

        teamPage.addObserver(this);
        followedTeams.put(teamName,team);

    }
    public void stopFollowTeam(String teamName){
        MainSystem.LOG.info(getUserFullName()+" stop follow the team : "+teamName);
        Team team;
        if (followedTeams.containsKey(teamName)) {
            team = followedTeams.remove(teamName);
            TeamPage teamPage = team.getPage();
            teamPage.deleteObserver(this);
        }
    }
    public void stopFollowAllTeams(){
        MainSystem.LOG.info(getUserFullName()+" stop follow all of the teams");

        Iterator it = followedTeams.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String key =(String) pair.getKey();
            stopFollowTeam(key);
            it.remove();
        }
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

    public String[] watchDetails(){
        MainSystem.LOG.info(getUserFullName()+" watch his details");
        String[] details = new String[4];
        details[0]=userFullName;
        details[1]=userName;
        details[2]= password;
        details[3]=userEmail;
        return details;




    }















}
