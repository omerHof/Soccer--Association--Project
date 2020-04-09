package Users;

import SystemLogic.DB;
import Teams.Team;
import UserGenerator.AUsersGenerator;
import UserGenerator.SimpleUserGenerator;

import java.util.*;

public class Fan extends User implements Observer {



    private HashMap<String,PersonalPage> followedPages;
    private HashMap<String,Team> followedTeams;
    private HashMap<String,Team> notificationTeams;

    public Fan(String userName, String password, String fullName, String email) {

        this.password = password;
        this.userName = userName;
        this.userFullName = fullName;
        this.userEmail = email;
        followedPages = new HashMap<>();
        followedTeams = new HashMap<>();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("changes in"+ arg);
    }

    public boolean approveRegistration(String fullName, String role){
        return true;
    }



    //getters

/*
    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //edit user details
    public void writeNewUserName(String username) {
        setUserName(username);
    }
    public void writeNewUPassword(String password){
        setPassword(password);
    }
    public void writeNewEmail(String email){
        setEmail(email);
    }
    public void writeNewName(String name){
        setName(name);
    }

 */

    public HashMap<String, PersonalPage> getFollowedPages() {
        return followedPages;
    }
    //get notification
    public void getNotificationOnGames(){
        selectTeams();/// the fan selected the teams that he want to get notidications about them
        for(HashMap.Entry<String,Team> team:notificationTeams.entrySet()) {
            team.getValue().addObserver(this);///not sure
        }
        }

    public void followThisPage(PersonalPage page){
        page.addFollower(this);
        page.addObserver(this);///not sure
        followedPages.put(page.getName(),page);
        followedTeams.put(page.getCurrentTeam().getName(),page.getCurrentTeam());

    }

   public void searchPage(String str){
        ///maybe not here
   }

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
                notificationTeams.put(choose, DB.getTeam(choose));
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

    public void sendComplaint(){

    }
    public void fillForm(){

    }
    public void sendForm(){

    }
    public void watchHistory(){
        //need to get into the log somehow

    }
    public void watchDetails(){
        System.out.println("your details:");
        System.out.println("full name: " +userFullName );
        System.out.println("user name: " +userName );
        System.out.println("password: " +password );
        System.out.println("email: " +userEmail );



    }















}
