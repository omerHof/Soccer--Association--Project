import LeagueSeasonsManagment.TwoRoundsGamePolicyTest;
import MainApp.AppTest;
import SystemLogic.DBTest;
import SystemLogic.MainSystemTest;
import Users.AssociationRepresentativeTest;
import Users.CoachTest;
import Users.RefereeTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {

        /* initialise Results*/
        Result simpleGamePolicy_result = JUnitCore.runClasses(TwoRoundsGamePolicyTest.class);
        Result App_result = JUnitCore.runClasses(AppTest.class);
        Result DB_result = JUnitCore.runClasses(DBTest.class);
        Result MainSystem_result = JUnitCore.runClasses(MainSystemTest.class);
        Result AssociationRepresentative_result = JUnitCore.runClasses(AssociationRepresentativeTest.class);
        Result Coach_result = JUnitCore.runClasses(CoachTest.class);
        Result Referee_result = JUnitCore.runClasses(RefereeTest.class);


        /* LeagueSeasonsManagement */
//        test(simpleGamePolicy_result);

        /* MainApp */
//        test(App_result);

        /* SystemLogic */
//        test(DB_result);
        test(MainSystem_result);

        /* Users */
//        test(AssociationRepresentative_result);
//        test(Coach_result);
//        test(Referee_result);


    }

    private static void test (Result result){
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}