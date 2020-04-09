package MainApp;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import SystemLogic.MainSystem;
import static org.junit.Assert.*;

public class MainSystemTest {
    private MainSystem mainSystem1 = MainSystem.getInstance();

    @Before // setup()
    public void before() throws Exception {
        System.out.println("Setting it up!");

    }

    @Test
    public void testDummyAccount() {
        System.out.println("Running: testMainTest");

    }

    @After // tearDown()
    public void after() throws Exception {
        System.out.println("Running: tearDown");
        mainSystem1 = null;
        assertNull(mainSystem1);
    }
}
