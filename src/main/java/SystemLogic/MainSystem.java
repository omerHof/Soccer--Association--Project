package SystemLogic;

import UserGenerator.ManagmentUserGenerator;
import Users.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainSystem {
    private static MainSystem single_instance = null;
    private AccountSystemProxy accountSystemProxy;
    private TaxSystemProxy taxSystemProxy;
    private User currentUser = null;
    ManagmentUserGenerator managmentUserGenerator = new ManagmentUserGenerator();
    public static final Logger LOG = LogManager.getLogger();;

    private MainSystem() {
        //here?
        initializeSystem();
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
        this.connectToLog();
        this.connectExternalSystems();
        this.appointUserToSAdministrator();
    }



    private void connectToLog(){
        LOG.info("LOG WAS CREATED!");
    }

    private void connectExternalSystems(){
        accountSystemProxy = new AccountSystemProxy();
        taxSystemProxy = new TaxSystemProxy();
    }

    private void appointUserToSAdministrator() {
        User scapegoat = DB.getUsers().remove(0);//todo: change according to Yiftah

        //managmentUserGenerator.generate(scapegoat.getUserName(),scapegoat.);// not field

    }

    public String singUp(String userName, String password){
        if( DB.getUsers().contains(userName)) {//todo: change according to Yiftah

        }
        return "successfully";
    }

    public String logIn(User user, String userName, String password){
        if( !DB.getUsers().contains(userName)) {//todo: change according to Yiftah
            return "name!";
        }
        else if( !DB.getUsers().contains(password)){//todo: change according to Yiftah
            return "password bitch!";
        }
        else{
            this.currentUser = user;
            return "successfully";
        }

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


