package Users;

public class Manager extends User {

    public Manager(String userName, String password, String fullName, String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
    }

    @Override
    public boolean approveRegistration(String fullName, String role) {
        return false;
    }
}
