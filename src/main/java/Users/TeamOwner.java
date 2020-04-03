package Users;

public class TeamOwner extends User {


    private String userName;
    private String password;

    private String fullName;

    public TeamOwner(String userName, String password, String fullName) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
    }

    public boolean approveRegistration(String fullName, String role){
        return true;
    }
}
