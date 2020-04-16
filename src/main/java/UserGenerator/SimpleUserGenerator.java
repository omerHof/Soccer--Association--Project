package UserGenerator;

import Users.User;

import java.time.LocalDate;
import java.util.Date;

public class SimpleUserGenerator implements IUserGenerator {


    @Override
    public User generate(String userName, String password, String managementPassword, String role, String fullName, String userEmail, LocalDate birthDate, String qualification, String courtRole, String teamRole) { // all the rest are not relevant for this simple user.

        User newFan = new Users.Fan(userName, password, fullName, userEmail);
        return newFan;
    }
}

