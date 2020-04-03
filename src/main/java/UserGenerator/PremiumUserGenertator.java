package UserGenerator;

import Users.*;

public class PremiumUserGenertator implements IUserGenerator {

    @Override
    public User generate(String userName, String password, String role, String fullName, String birthDate, String qualification, String courtRole, String teamRole) {

        //User newUser = new Users.Fan(userName, password);

        User newUser = whichUserAmI(userName, password, role, fullName, birthDate, qualification, courtRole, teamRole);

        return newUser;

    }

    public User whichUserAmI(String userName, String password, String role, String fullName, String birthDate, String qualification, String courtRole, String teamRole) {

        if (role != null) {
            switch (role.toLowerCase()) {

                case ("player"): {//Register a player
                    User newPlayer = new Player(userName, password, fullName, birthDate, courtRole);
                    return newPlayer;
                }

                case ("coach"): {//Register a coach
                    User newCoach = new Coach(userName, password, fullName, qualification, teamRole);
                    return newCoach;
                }

                case ("manager"): { //Register a manager
                    User newManager = new Manager(userName, password, fullName);
                    return newManager;
                }

                case ("teamowner"): { //Register a teamOwner
                    User newTeamOwner = new TeamOwner(userName, password, fullName);
                    return newTeamOwner;
                }

                case ("referee"): { //Register a referee
                    User newReferee = new Referee(userName, password, fullName, qualification);
                    return newReferee;
                }

                default: {  //not legal.
                    System.out.println("unsupported input");
                    return null;
                }
            }
        }
        else
            return null;

    }
}
