package Users;

public class Referee extends User {


    private String userName;
    private String password;

    private String fullName;
    private String qualification;

    public Referee(String userName, String password, String fullName, String qualification) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.qualification = qualification;
    }
}
