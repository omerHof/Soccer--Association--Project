package Users;

public abstract class User {

    private String UserName;
    private String password;

    public abstract boolean approveRegistration(String fullName, String role);

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
