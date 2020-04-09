package SystemLogic;

import Users.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.util.HashMap;

public class MainSystem {
    private static MainSystem single_instance = null;
    private AccountSystemProxy accountSystemProxy;
    private TaxSystemProxy taxSystemProxy;
    private User currentUser;
    public static final Logger LOG = LogManager.getLogger();;

    private MainSystem() {

    }

    public static MainSystem getInstance()
    {
        if (single_instance == null)
            single_instance = new MainSystem();
        return single_instance;
    }

    public void initializeSystem(){
        this.connectToLog();
        this.connectExternalSystems();
    }

    public void connectToLog(){
        LOG.info("LOG WAS CREATED!");
    }

    public void connectExternalSystems(){
        accountSystemProxy = new AccountSystemProxy();
        taxSystemProxy = new TaxSystemProxy();

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


