package SystemLogic;

import Users.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.util.HashMap;

public class System {
    AccountSystemProxy accountSystemProxy;
   TaxSystemProxy taxSystemProxy;
    User currentUser;
    HashMap<String, User> users;
    private static final Logger LOG = LogManager.getLogger();;

    public System() {

    }

    public void initializeSystem(){
        this.connectToLog();
        this.connectExternalSystems();
    }

    public void connectToLog(){
        LOG.info("SOME MESSAGE!");
        LOG.debug("Debug Message!");
        Configurator.setLevel("com.example.Foo", Level.DEBUG);
        Configurator.setRootLevel(Level.INFO);
        LOG.info(String.format("Logging level: %s", LOG.getLevel()));
    }

    public void connectExternalSystems(){

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

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }
}


