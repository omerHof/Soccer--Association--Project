package Users;

import SystemLogic.Notification;

import java.util.ArrayList;

public abstract class User {

    protected String userName;
    protected String password;

    protected String userEmail;
    protected String userFullName;

    protected ArrayList<Notification> receivedNotifications = new ArrayList<>();
    protected ArrayList<Notification> readNotifications = new ArrayList<>();
    private boolean nonReadNotifications = false;

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

    public ArrayList<Notification> getReadNotifications() {
        return readNotifications;
    }

    public void setReadNotifications(ArrayList<Notification> readNotifications) {
        this.readNotifications = readNotifications;
    }

    public boolean isNonReadNotifications() {
        return nonReadNotifications;
    }

    public void setNonReadNotifications(boolean nonReadNotifications) {
        this.nonReadNotifications = nonReadNotifications;
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
