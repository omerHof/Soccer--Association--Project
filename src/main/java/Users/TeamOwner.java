package Users;

public class TeamOwner extends User {


    public TeamOwner(String userName, String password, String fullName,String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
    }
}
