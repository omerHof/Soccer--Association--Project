package Users;

import UserGenerator.AUsersGenerator;
import UserGenerator.SimpleUserGenerator;

import java.util.Observable;
import java.util.Observer;

public class Fan extends User implements Observer {

    private String userName;
    private String password;
    private String name;
    private String email;

    public Fan(String userName, String password) {

        this.password = password;
        this.userName = userName;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public boolean approveRegistration(String fullName, String role){
        return true;
    }

    //getters


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //edit user details
    public void writeNewUserName(String username) {
        setUserName(username);
    }
    public void writeNewUPassword(String password){
        setPassword(password);
    }
    public void writeNewEmail(String email){
        setEmail(email);
    }
    public void writeNewName(String name){
        setName(name);
    }


}
