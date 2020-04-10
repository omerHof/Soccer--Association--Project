package Users;

public class Administrator extends User {

    public Administrator(String userName, String password, String fullName, String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userEmail = userEmail;
        this.userFullName = fullName;
    }

    public boolean approveRegistration(String fullName, String role){
        return true;
    }
}
