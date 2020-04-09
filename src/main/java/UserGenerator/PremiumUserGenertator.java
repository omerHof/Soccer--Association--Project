package UserGenerator;

import SystemLogic.DB;
import Users.*;

public class PremiumUserGenertator implements IUserGenerator {

    @Override
    public User generate(String userName, String password, String role, String fullName,String userEmail, String birthDate, String qualification, String courtRole, String teamRole) {

        boolean approved = askForApproval(fullName, role);

        if(approved) {
            User newUser = whichUserAmI(userName, password, role, fullName, birthDate,userEmail, qualification, courtRole, teamRole);
            return newUser;
        }
        else
            throw new Error(); //////// a message ????

    }

    /**
     * this method checks what kind of Premium User it is, and creates the right one.
     *
     * @param userName, .......
     * @return User object
     */
    public User whichUserAmI(String userName, String password, String role, String fullName, String userEmail, String birthDate, String qualification, String courtRole, String teamRole) {

        if (role != null) {
            switch (role.toLowerCase()) {

                case ("player"): {//Register a player
                    User newPlayer = new Player(userName, password, fullName,userEmail, birthDate, courtRole);
                    return newPlayer;
                }

                case ("coach"): {//Register a coach
                    User newCoach = new Coach(userName, password, fullName,userEmail, qualification, teamRole);
                    return newCoach;
                }

                case ("manager"): { //Register a manager
                    User newManager = new Manager(userName, password, fullName,userEmail);
                    return newManager;
                }

                case ("teamOwner"): { //Register a teamOwner
                    User newTeamOwner = new TeamOwner(userName, password, fullName,userEmail);
                    return newTeamOwner;
                }

                case ("referee"): { //Register a referee
                    User newReferee = new Referee(userName, password, fullName, qualification,userEmail);
                    return newReferee;
                }

                default: {  //not legal.
                    System.out.println("unsupported role input"); //// error alert ?/...
                    return null;
                }
            }
        }
        else
            return null;

    }

    public boolean askForApproval (String fullName, String role) {

     /*   AssociationRepresentative ar = DB.getRepresentative(); // a random one.
        boolean isApproved = ar.approveRegistration(fullName, role);

        return isApproved;
    }*/

        return false;
    }
}
