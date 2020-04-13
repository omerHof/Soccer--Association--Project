package UserGenerator;

import SystemLogic.DB;
import SystemLogic.MainSystem;
import Users.*;

import java.util.Date;

public class PremiumUserGenertator implements IUserGenerator {

    @Override
    public User generate(String userName, String password, String managementPassword, String role, String fullName, String userEmail, Date birthDate, String qualification, String courtRole, String teamRole) {

        boolean approved = askForApproval(fullName, role);

        if(approved) {
            User newUser = whichUserAmI(userName, password, managementPassword, role, fullName, userEmail, birthDate, qualification, courtRole, teamRole);
            return newUser;
        }
        else
            return null;

    }

    /**
     * this method checks what kind of Premium User it is, and creates the right one.
     *
     * @param userName, .......
     * @return User object
     */
    public User whichUserAmI(String userName, String password, String managementPassword, String role, String fullName, String userEmail, Date birthDate, String qualification, String courtRole, String teamRole) {

        if (role != null) {
            switch (role.toLowerCase()) {

                case ("player"): {//Register a player
                    User newPlayer = new Player(userName, password, fullName, userEmail, birthDate, courtRole);
                    MainSystem.LOG.info("A new player: " + userName + " was created successfully !");
                    return newPlayer;
                }

                case ("coach"): {//Register a coach
                    User newCoach = new Coach(userName, password, fullName, userEmail, teamRole);
                    MainSystem.LOG.info("A new coach: " + userName + " was created successfully !");
                    return newCoach;
                }

                case ("manager"): { //Register a manager
                    User newManager = new Manager(userName, password, fullName,userEmail);
                    MainSystem.LOG.info("A new team manager: " + userName + " was created successfully !");
                    return newManager;
                }

                case ("teamowner"): { //Register a teamOwner
                    User newTeamOwner = new TeamOwner(userName, password, fullName,userEmail);
                    MainSystem.LOG.info("A new team owner: " + userName + " was created successfully !");
                    return newTeamOwner;
                }

                case ("referee"): { //Register a referee
                    User newReferee = new Referee(userName, password, fullName, qualification,userEmail);
                    if(!managementPassword.equals("onlyChangeStatus")) //association representative changed a fan's status. no need to write this in LOG.
                        MainSystem.LOG.info("A new referee: " + userName + " was created successfully !");
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

        DB db1 = DB.getInstance();
        AssociationRepresentative ar = (AssociationRepresentative) db1.getUserType("AssociationRepresentative"); // a random one.
        boolean isApproved = ar.approveRegistration(fullName, role);

        return isApproved;
    }

}
