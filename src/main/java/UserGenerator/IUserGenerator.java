package UserGenerator;

import Users.User;

public interface IUserGenerator {

    User generate(String userName, String password);
}
