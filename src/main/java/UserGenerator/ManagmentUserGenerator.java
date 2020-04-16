package UserGenerator;

import SystemLogic.MainSystem;
import Users.Administrator;
import Users.AssociationRepresentative;
import Users.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class ManagmentUserGenerator implements IUserGenerator {

    private static String represantativePassword;
    private static String systemManagerPassword;

    @Override
    public User generate(String userName, String password, String managementPassword, String role, String fullName, String userEmail, LocalDate birthDate, String qualification, String courtRole, String teamRole) {

        MainSystem mainSystem = MainSystem.getInstance();
        boolean approved = managementPassword.equals(mainSystem.getSpecialPassword()); // להשוות מול הסיסמא שיצרו אצל כצי

        if (approved) {
            if (role.equals("AssociationRepresentative"))
                return new AssociationRepresentative(userName, password, fullName, userEmail);
            else if (role.equals("Administrator"))
                return new Administrator(userName, password, fullName, userEmail);
            else
                return null;
        }
        else
            return null;
    }


    private String askForSpecialPassword() {

        System.out.println("Please enter your management user's password:");

        Scanner sc = new Scanner(System.in);
        return sc.next();
    }


    public boolean askForApproval(String fullName, String password) { //checks whether the special password is valid or not.
        return true;
    }
}