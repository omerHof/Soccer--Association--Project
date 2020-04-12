package SystemLogic;

import UserGenerator.IUserGenerator;
import UserGenerator.ManagmentUserGenerator;
import Users.Administrator;
import Users.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainSystem {
    private static MainSystem single_instance = null;
    private AccountSystemProxy accountSystemProxy;
    private TaxSystemProxy taxSystemProxy;
    private User currentUser = null;
    public static Logger LOG;
    private DB db = DB.getInstance();
    private TimerPasswordBuilder timerPasswordBuilder;


    /**
     * An empty constructor
     */
    private MainSystem() {
        timerPasswordBuilder = new TimerPasswordBuilder();
    }

    /**
     * A Singleton method
     */
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

    /**
     * Use Case 1.1: This method initializes the System:
     * Connect to the logger, connect external systems, appoint administrator, start the password builder
     */
    public void initializeSystem(){
        Timer t = new Timer();
        t.scheduleAtFixedRate(timerPasswordBuilder, 0, TimeUnit.DAYS.toMillis(1));
        t.scheduleAtFixedRate(timerPasswordBuilder, 0, 1000);
        connectToLog();
        connectExternalSystems();
        appointUserToSAdministrator(); //how can someone be a user before initialization?
    }

    /**
     * This method initialize the logger
     */
    private void connectToLog(){
        LOG  = LogManager.getLogger();
        LOG.info("LOG WAS CREATED!");
    }

    /**
     * This method initialize the external system and connect this class to them.
     */
    private void connectExternalSystems(){
        accountSystemProxy = new AccountSystemProxy();
        if(accountSystemProxy.connectToSystem()){
            LOG.info("Account System is Connected");
        }
        else{
            LOG.info("Account System Connection failed");
        }
        taxSystemProxy = new TaxSystemProxy();
        if(taxSystemProxy.connectToSystem()){
            LOG.info("Tax System is Connected");
        }
        else{
            LOG.info("Tax System Connection failed");
        }
    }

    /**
     * This method gets user from the data base and appoints him to administrator.
     */
    private void appointUserToSAdministrator() {
        ManagmentUserGenerator managmentUserGenerator = new ManagmentUserGenerator();
        User scapegoat = db.getUser("name"); //todo: 1. change to remove 2. get random or by name?
        String special_password = timerPasswordBuilder.getPassword();
        Administrator administrator = (Administrator) managmentUserGenerator.generate(scapegoat.getUserName(),scapegoat.getPassword(),special_password
                ,"", scapegoat.getUserFullName(), scapegoat.getUserEmail(),
                "","","","");
        db.addUser(administrator);
        LOG.info("Administrator was appointed successfully");
    }


    /**
     * This method is for user's signing up the system
     * @param userName
     * @param password
     * @param mangerPassword
     * @param role
     * @param fullName
     * @param userEmail
     * @param birthDate
     * @param qualification
     * @param courtRole
     * @param teamRole
     * @param iUserGenerator
     * @return boolean answer - did the signing up work or not
     */
    public boolean singUp(String userName, String password, String mangerPassword, String role, String fullName,String userEmail,
                          String birthDate, String qualification, String courtRole, String teamRole,
                          IUserGenerator iUserGenerator){
        if (db.userExist(userName)){
            return false;
        }

        User newUser =  iUserGenerator.generate(userName, password, mangerPassword, role, fullName, userEmail,
                birthDate, qualification, courtRole, teamRole);
        db.addUser(newUser);
        LOG.info("A new user " + userName + " was signed up successfully");
        this.currentUser = newUser;
        LOG.info(userName + " was logged in successfully");

        return true;
    }

    /**
     * This method is for user's logging in the system.
     * Checks his user name and his password in the data base
     * @param userName
     * @param password
     * @return String - did the logging in work or not and why
     */
    public String logIn(String userName, String password){
        if(!db.userExist(userName)){
            return "name";
        }
        else if(!db.getUser(userName).getPassword().equals(password)){
            return "password";
        }
        this.currentUser = db.getUser(userName);
        LOG.info(userName + " was logged in successfully");
        return "ok";
    }

    /**
     * This method is for user's logging out the system.
     * @return boolean answer - did the logging out work or not
     */
    public boolean logOut(){
        this.currentUser = null;
        LOG.info(currentUser.getUserName() + " was logged in successfully");
        return true;
    }

    /**
     *
     * @return String special password
     */
    public String getSpecialPassword(){
        return timerPasswordBuilder.getPassword();
    }

    /** GETTERS & SETTERS **/

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

class TimerPasswordBuilder extends TimerTask {
    private String password;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * This method creates a random password every day
     */
    @Override
    public void run() {
        StringBuilder builder = new StringBuilder();
        int x =8;
        while (x-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        password =  builder.toString();
        MainSystem.LOG.info("Special password changed at ");
    }

    public String getPassword(){
        return password;
    };
}
