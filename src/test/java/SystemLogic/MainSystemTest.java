package SystemLogic;

import UserGenerator.SimpleUserGenerator;
import org.junit.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


import static org.junit.Assert.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainSystemTest {
    private static MainSystem mainSystem;
    private static DB data_base;

    @BeforeClass
    public static void beforeClass() {
        data_base = DB.getInstance();
        mainSystem = MainSystem.getInstance();
    }

    @AfterClass
    public static void afterClass() {
        data_base = null;
        mainSystem = null;
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void AGetInstance() {

    }

    @Test
    public void BInitializeSystem() {

    }

    @Test
    public void CSingUp() {
        SimpleUserGenerator  simpleUserGenerator = new SimpleUserGenerator();
        //Date birth_date = new Date(1992, 4,3);
        boolean ans = mainSystem.singUp("Kroi","kod66666", "", "",
                "Roi Katz","roik16@gmail.com", null, "", "", "",
                simpleUserGenerator);
        assertTrue(ans);
        assertTrue(data_base.userExist("Kroi"));
        assertEquals(mainSystem.getCurrentUser().getUserName(),"Kroi" );
    }

    @Test
    public void DLogIn() {

    }

    @Test
    public void ELogOut() {
        mainSystem.logOut();
        assertNull(mainSystem.getCurrentUser());
    }
}
