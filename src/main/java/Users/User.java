package Users;

import SystemLogic.MainSystem;
import SystemLogic.Notification;

import java.util.ArrayList;

public abstract class User {

    protected String userName;
    protected String password;

    protected String userEmail;
    protected String userFullName;

    protected ArrayList<Notification> receivedNotifications = new ArrayList<>();
    protected ArrayList<Notification> sentNotifications = new ArrayList<>();

    public void sendTo (User receiver, Notification notification){
        this.sentNotifications.add(notification);
        receiver.receivedNotifications.add(notification);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public ArrayList<Notification> getReceivedNotifications() {
        return receivedNotifications;
    }

    public void setReceivedNotifications(ArrayList<Notification> receivedNotifications) {
        this.receivedNotifications = receivedNotifications;
    }

    public ArrayList<Notification> getSentNotifications() {
        return sentNotifications;
    }

    public void setSentNotifications(ArrayList<Notification> sentNotifications) {
        this.sentNotifications = sentNotifications;
    }

    public String[] watchDetails(){
        //MainSystem.LOG.info(getUserFullName()+" watch his details");
        String[] details = new String[4];
        details[0]=userFullName;
        details[1]=userName;
        details[2]= password;
        details[3]=userEmail;
        return details;
    }
}
