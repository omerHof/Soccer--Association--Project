package Users;

public class Coach extends User {

    private String userName;
    private String password;

    private String fullName;
    private String qualification;
    private String teamRole;

    public Coach(String userName, String password, String fullName, String qualification, String teamRole) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.qualification = qualification;
        this.teamRole = teamRole;
    }
}
