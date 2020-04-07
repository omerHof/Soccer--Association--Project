package SystemLogic;

import Users.User;

import java.util.HashMap;

public class System {
    AccountSystemProxy accountSystemProxy;
    TaxSystemProxy taxSystemProxy;
    User currentUser;
    HashMap<String, User> users;

    public System() {

    }

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


