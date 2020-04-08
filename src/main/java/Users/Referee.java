package Users;

import javafx.scene.control.Alert;

public class Referee extends User {


    private String userName;
    private String password;

    private String fullName;
    private String qualification;

    public Referee(String userName, String password, String fullName, String qualification) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.qualification = qualification;
    }

    @Override
    public boolean approveRegistration(String fullName, String role) {
        return false;
    }

    public void updatePersonalDetails(String fullName, String qualification){

        if (fullName != "" && qualification != ""){
            this.fullName = fullName;
            this.qualification = qualification;
        }
        else if (fullName != "")
            this.fullName = fullName;
        else if(qualification != "")
            this.qualification = qualification;
        else
            //displayError("Nothing to update!");
        System.out.println("Nothing to update!");
    }


    private void displayError(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(error);
        alert.show();
    }
}
