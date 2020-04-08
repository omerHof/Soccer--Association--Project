package MainApp;
import SystemLogic.System;
import java.io.IOException;

public class SystemTest {

    public static void main(String[] args) throws IOException {
        System system = new System();
        system.connectToLog();
    }
}
