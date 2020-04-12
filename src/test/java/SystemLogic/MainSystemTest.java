package SystemLogic;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class MainSystemTest {
    private static MainSystem mainSystem;

    @BeforeClass
    public static void beforeClass() {
        mainSystem = MainSystem.getInstance();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getInstance() {

    }

    @Test
    public void initializeSystem() {

    }

    @Test
    public void singUp() {
        //mainSystem.singUp('Roi',"kod66666")
    }

    @Test
    public void logIn() {

    }

    @Test
    public void logOut() {

        mainSystem.logOut();
        assertNull(mainSystem.getCurrentUser());
    }
}
