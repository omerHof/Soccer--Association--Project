package Users;

import java.util.Date;

public class Player extends User {

    private String userName;
    private String password;

    private String fullName;
    private String birthDate;
    private String courtRole;

    public Player(String userName, String password, String fullName, String birthDate, String courtRole) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.courtRole = courtRole;
    }
}
