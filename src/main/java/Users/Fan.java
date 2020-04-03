package Users;

import java.util.Observable;
import java.util.Observer;

public class Fan extends User implements Observer {

    private String userName;
    private String password;

    public Fan(String userName, String password) {

        this.password = password;
        this.userName = userName;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
