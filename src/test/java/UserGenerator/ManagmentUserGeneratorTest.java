package UserGenerator;

import SystemLogic.DB;
import SystemLogic.MainSystem;
import Users.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagmentUserGeneratorTest {

    private DB dbTest = DB.getInstance();
    private AssociationRepresentative assoTest = new AssociationRepresentative("aa", "", "alal", "a@a");
    private MainSystem mainSystem = MainSystem.getInstance();
    private ManagementUserGenerator manG = new ManagementUserGenerator();

    @Before
    public void setUp() throws Exception {
        dbTest.addUser(assoTest);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void generate() {

        Administrator testAdministrator = (Administrator) manG.generate("p1", "94929", "yyyy", "Administrator", "Aviv Alush", "tali@aviv", null, "", "", "");
        assertNull(testAdministrator);

        AssociationRepresentative testAssociationRepresentative = (AssociationRepresentative) manG.generate("m1", "94929", "yyyy", "AssociationRepresentative", "omer hof", "o@o", null, "", "", "");
        assertNull(testAssociationRepresentative);

        String specialPass = mainSystem.getSpecialPassword();
        testAdministrator = (Administrator) manG.generate("p1", "94929", specialPass, "Administrator", "Aviv Alush", "tali@aviv", null, "", "", "");
        assertNotNull(testAdministrator);

        testAssociationRepresentative = (AssociationRepresentative) manG.generate("m1", "94929", specialPass, "AssociationRepresentative", "omer hof", "o@o", null, "", "", "");
        assertNotNull(testAssociationRepresentative);

        assertTrue((testAdministrator) instanceof Administrator);
        assertTrue((testAssociationRepresentative) instanceof AssociationRepresentative);
    }

    @Test
    public void askForApproval() {
    }
}