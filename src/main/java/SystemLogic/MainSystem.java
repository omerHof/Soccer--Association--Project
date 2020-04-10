package SystemLogic;

import UserGenerator.IUserGenerator;
import UserGenerator.ManagmentUserGenerator;
import Users.Administrator;
import Users.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainSystem {
    private static MainSystem single_instance = null;
    private AccountSystemProxy accountSystemProxy;
    private TaxSystemProxy taxSystemProxy;
    private User currentUser = null;
    public static final Logger LOG = LogManager.getLogger();
    private MainSystem() {

    }

    public static MainSystem getInstance()
    {
        if (single_instance == null) {
            synchronized (MainSystem.class) {
                if (single_instance == null) {
                    single_instance = new MainSystem();
                }
            }
        }
        return single_instance;
    }

    public void initializeSystem(){
        connectToLog();
        connectExternalSystems();
        appointUserToSAdministrator(); //how can someone be a user before initialization?
    }


    private void connectToLog(){
        LOG.info("LOG WAS CREATED!");
    }

    private void connectExternalSystems(){
        accountSystemProxy = new AccountSystemProxy();
        taxSystemProxy = new TaxSystemProxy();
    }

    private void appointUserToSAdministrator() {
        ManagmentUserGenerator managmentUserGenerator = new ManagmentUserGenerator();
        User scapegoat = DB.getInstance().getUser("name"); //todo: 1. change to remove 2. get random or by name?
        Administrator administrator = (Administrator) managmentUserGenerator.generate(scapegoat.getUserName(),scapegoat.getPassword(),
                "", scapegoat.getUserFullName(), scapegoat.getUserEmail(),
                "","","","");
        DB.getInstance().addUser(administrator);
        LOG.info("Administrator was appointed successfully");
    }

    public boolean singUp(String userName, String password, String role, String fullName,String userEmail,
                         String birthDate, String qualification, String courtRole, String teamRole,
                         IUserGenerator iUserGenerator){
        if (false){//todo: make method that checks if DB contains userName / password
            return false;
        }

        User newUser =  iUserGenerator.generate(userName, password, role, fullName, userEmail,
                birthDate, qualification, courtRole, teamRole);
        DB.getInstance().addUser(newUser);
        LOG.info("A new user was created successfully");
        this.currentUser = newUser;
        return true;
    }

    public String logIn(User user, String userName, String password){


        return null;
    }

    public String logOut(User user){
        return "successfully";
    }






    /* Getters & Setters*/
    public AccountSystemProxy getAccountSystemProxy() {
        return accountSystemProxy;
    }

    public void setAccountSystemProxy(AccountSystemProxy accountSystemProxy) {
        this.accountSystemProxy = accountSystemProxy;
    }

    public TaxSystemProxy getTaxSystemProxy() {
        return taxSystemProxy;
    }

    public void setTaxSystemProxy(TaxSystemProxy taxSystemProxy) {
        this.taxSystemProxy = taxSystemProxy;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


}


