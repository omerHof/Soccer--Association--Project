package ServiceLayer;

import SystemLogic.MainSystem;
import SystemLogic.Notification;
import UserGenerator.IUserGenerator;
import UserGenerator.ManagmentUserGenerator;
import UserGenerator.PremiumUserGenertator;
import UserGenerator.SimpleUserGenerator;
import Users.Fan;
import Users.User;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class represents a bridge for handling the user creation and functionality between the domain layer and the GUI layer
 */

public class UserManagement {

    User currentUser;

    ////////////////USER MANAGEMENT FUNCTIONALITY//////////////////

    public String createNewUser(String userName, String password, String mangerPassword, String role, String fullName, String userEmail,
                                LocalDate birthDate, String qualification, String courtRole, String teamRole,
                                String type){
        IUserGenerator iUserGenerator;
        switch (type){
            case "SimpleUserGenerator":
                iUserGenerator = new SimpleUserGenerator();
                break;
            case "PremiumUserGenertator":
                iUserGenerator = new PremiumUserGenertator();
                break;
            case "ManagmentUserGenerator":
                iUserGenerator = new ManagmentUserGenerator();
                break;
            default:
                iUserGenerator = new SimpleUserGenerator();
        }
        String res = MainSystem.getInstance().singUp(userName,password,mangerPassword,role,fullName,userEmail,birthDate,qualification,courtRole,teamRole,iUserGenerator);
        currentUser = MainSystem.getInstance().getCurrentUser();
        return res;
    }


    public String logIn(String userName, String password){
        String res = MainSystem.getInstance().logIn(userName,password);
        currentUser = MainSystem.getInstance().getCurrentUser();
        return res;
    }

    public boolean logOut(){
        currentUser = null;
        return MainSystem.getInstance().logOut();
    }

    public String getUserName(){
        return currentUser.getUserName();
    }

    public void setUserName(String userName){
        currentUser.setUserName(userName);
    }

    public String getUserFullName() {
        return currentUser.getUserFullName();
    }
    public ArrayList<Notification> getReceivedNotifications() {
        return currentUser.getReceivedNotifications();
    }

    public String getUserEmail(){
        return currentUser.getUserEmail();
    }

    public void setUserEmail(String userEmail){
        currentUser.setUserEmail(userEmail);
    }

    public String[] watchDetails(){
        return currentUser.watchDetails();
    }


    ////////////////FAN MANAGEMENT FUNCTIONALITY//////////////////

    public void followPage(String pageName){
        ((Fan) currentUser).followThisPage(pageName);
    }

    public void stopFollowThisPage(String pageName){
        ((Fan) currentUser).stopFollowThisPage(pageName);
    }

    public void stopFollowAllPages(){
        ((Fan) currentUser).stopFollowAllPages();
    }

    public void followTeam(String teamName){
        ((Fan) currentUser).followTeam(teamName);
    }

    public void stopFollowTeam(String teamName){
        ((Fan) currentUser).stopFollowTeam(teamName);
    }

    public void stopFollowAllTeams(){
        ((Fan) currentUser).stopFollowAllTeams();
    }







}
