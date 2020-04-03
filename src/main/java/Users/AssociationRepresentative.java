package Users;

public class AssociationRepresentative extends User {

    private String userName;
    private String password;

    private String fullName;
    private static int numOfApprovals = 0 ;

    public AssociationRepresentative(String userName, String password, String fullName) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
    }

    public boolean approveRegistration(String fullName, String role){ //random (symbolic) function

        if (numOfApprovals < 9) {
            numOfApprovals++;
            return true;
        }

        // after 9 approvals.
        numOfApprovals=0; //begins from the start.
        return false;
    }
}
