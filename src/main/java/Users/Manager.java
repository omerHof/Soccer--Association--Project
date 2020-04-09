package Users;

import SystemLogic.MainSystem;

public class Manager extends User {

    private String userName;
    private String password;
    private String fullName;

    public Manager(String userName, String password, String fullName) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
    }

    @Override
    public boolean approveRegistration(String fullName, String role) {
        return false;//todo
    }

    public void pointAnotherManager(){
        MainSystem.LOG.info("something");
    }
}
