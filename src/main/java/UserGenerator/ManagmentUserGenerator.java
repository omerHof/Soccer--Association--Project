package UserGenerator;

import Users.Administrator;
import Users.AssociationRepresentative;
import Users.User;

import java.util.Scanner;

public class ManagmentUserGenerator implements IUserGenerator {

    private static String represantativePassword;
    private static String systemManagerPassword;

    @Override
    public User generate(String userName, String password, String managementPassword, String role, String fullName,String userEmail,String birthDate, String qualification, String courtRole, String teamRole) {

        boolean approved = askForApproval(fullName, managementPassword); // להשוות מול הסיסמא שיצרו אצל כצי ???

        if(approved){
            if (managementPassword == represantativePassword){
                User newRepresantative = new AssociationRepresentative(userName, password, fullName,userEmail );
                return newRepresantative;
            }

            else if (managementPassword == systemManagerPassword){
                User newAdministrator = new Administrator(userName, password, fullName,userEmail);
                return newAdministrator;
            }

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