package UserGenerator;

import Users.User;

public interface IUserGenerator {

    /**
     *
     * @param userName
     * @param password
     * @return User object
     */
    public User generate(String userName, String password, String role, String fullName, String birthDate, String qualification, String courtRole, String teamRole);
}
