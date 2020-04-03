package Users;

public class MainReferee extends Referee {

    public MainReferee(String userName, String password, String fullName, String qualification) {
        super(userName, password, fullName, qualification);
    }

    public boolean approveRegistration(String fullName, String role){
        return true;
    }
}
