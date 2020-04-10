package UserGenerator;

import Users.User;

public interface IUserGenerator {

    /**
     *
     * @param userName
     * @param password
     * @return User object
     */
    public User generate(String userName, String password, String managementPassword, String role, String fullName,String userEmail, String birthDate, String qualification, String courtRole, String teamRole);
}
