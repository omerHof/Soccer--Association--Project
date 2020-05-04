package UILayer.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController extends Controller {

    String userFullName;
    String courtRole;
    String teamRole;
    String qualification;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //find the user type
        switch (userType) {
            case "Fan":

                break;
            case "Player":

                break;
            case "Coach":

                break;
            case "AssociationRepresentative":

                break;
            case "TeamOwner":

                break;
            case "Manager":

                break;
            case "Referee":

                break;
            case "MainReferee":

                break;
        }

        //display his correct details
        //create edit button for each detail beside password and username
        //open text field after press edit button
        //validate new details
        //submit to db


    }
}
