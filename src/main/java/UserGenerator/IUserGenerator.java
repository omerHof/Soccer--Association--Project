package UserGenerator;

import Users.User;

import java.time.LocalDate;
import java.util.Date;

public interface IUserGenerator {

    /**
     *
     * @param userName
     * @param password
     * @return User object
     */
    public User generate(String userName, String password, String managementPassword, String role, String fullName, String userEmail, LocalDate birthDate, String qualification, String courtRole, String teamRole);
}
