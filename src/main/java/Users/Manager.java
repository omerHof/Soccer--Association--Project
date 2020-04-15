package Users;

import Teams.Assent;

public class Manager extends User implements Assent {
    private double worth;

    public Manager(String userName, String password, String fullName, String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }
}
