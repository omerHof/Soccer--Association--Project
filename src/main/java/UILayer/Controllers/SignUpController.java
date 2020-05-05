package UILayer.Controllers;

import ServiceLayer.UserManagement;
import UILayer.Main;
import ServiceLayer.UserManagement;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
//import Views.Sign


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController extends Controller {

    private UserManagement userManagement;

    private String username;
    private String fullname;
    private String password;
    private String email;

    private String qualification;
    private String courtRole;
    private String teamRole;
    private String managementPassword;
    private LocalDate birthDate;

    private boolean detailsFilled = false;

    private String userType;
    private String generatorType;

    private ObservableList<String> coachRoleOptions = FXCollections.observableArrayList();
    private ObservableList<String> refereeRoleOptions = FXCollections.observableArrayList();
    private ObservableList<String> playerCourtRoleOptions = FXCollections.observableArrayList();


    //all type buttons:
    @FXML
    JFXButton refereeBTN;
    @FXML
    JFXButton coachBTN;
    @FXML
    JFXButton fanBTN;
    @FXML
    JFXButton teamOwnerBTN;
    @FXML
    JFXButton managerBTN;
    @FXML
    JFXButton administratorBTN;
    @FXML
    JFXButton associationRepresentativeBTN;
    @FXML
    JFXButton playerBTN;


    //mutual fields for all users.
    @FXML
    TextField emailTF;
    @FXML
    TextField usernameTF;
    @FXML
    TextField fullnameTF;
    @FXML
    PasswordField passwordTF;

    //specific fields for each user.
    @FXML
    DatePicker birthdateDP;
    @FXML
    Label birthdateLable;
    @FXML
    Label teamRoleLable;
    @FXML
    ChoiceBox coachTeamRoleCB;
    @FXML
    Label courtRoleLable;
    @FXML
    ChoiceBox playerCourtRoleCB;

    @FXML
    Label qualificationLable;
    @FXML
    ChoiceBox refereeQualificationCB;

    @FXML
    TextField managePassTF;
    @FXML
    Label managePassLable;

    @FXML
    JFXButton submit;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });

        userManagement = new UserManagement();


        coachRoleOptions.add("Head Coach");
        coachRoleOptions.add("Assistant Coach");
        coachRoleOptions.add("Fitness Coach");
        coachTeamRoleCB.setItems(coachRoleOptions);

        refereeRoleOptions.add("Var referee");
        refereeRoleOptions.add("Linesman referee");
        refereeQualificationCB.setItems(refereeRoleOptions);

        playerCourtRoleOptions.add("GoalKeeper");
        playerCourtRoleOptions.add("Defender");
        playerCourtRoleOptions.add("MidFielder");
        playerCourtRoleOptions.add("Striker");
        playerCourtRoleCB.setItems(playerCourtRoleOptions);

        DatePicker birthdateDP = new DatePicker();

        StackPane root = new StackPane();
        root.getChildren().add(birthdateDP);
        s.setScene(new Scene(root, 500, 650));
        s.show();

        try {
            allExtraFieldsOff();
            submit.setDisable(true); //until the basic details are full by the user.

            //checkDetailsFilled();

            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void typePlayer() throws IOException {

        allExtraFieldsOff();

        birthdateDP.setVisible(true);
        birthdateLable.setVisible(true);
        playerCourtRoleCB.setVisible(true);
        courtRoleLable.setVisible(true);

        userType = "Player";

        submit.setDisable(false); //now the user can try pressing the submit button.

    }

    @FXML
    public void typeCoach() throws IOException {

        allExtraFieldsOff();

        coachTeamRoleCB.setVisible(true);
        teamRoleLable.setVisible(true);

        userType = "Coach";

        submit.setDisable(false); //now the user can try pressing the submit button.


    }

    @FXML
    public void typeReferee() throws IOException {

        allExtraFieldsOff();

        refereeQualificationCB.setVisible(true);
        qualificationLable.setVisible(true);

        userType = "Referee";

        submit.setDisable(false); //now the user can try pressing the submit button.

    }

    @FXML
    public void typeManagement() throws IOException {

        allExtraFieldsOff();

        managePassTF.setVisible(true);
        managePassLable.setVisible(true);

        if (administratorBTN.isPressed())
            userType = "Administrator";
        else
            userType = "AssociationRepresentative";

        submit.setDisable(false); //now the user can try pressing the submit button.

        //checkDetailsFilled();
    }

    @FXML
    public void allExtraFieldsOff() throws IOException {

        birthdateDP.setVisible(false);
        birthdateLable.setVisible(false);
        coachTeamRoleCB.setVisible(false);
        teamRoleLable.setVisible(false);
        playerCourtRoleCB.setVisible(false);
        courtRoleLable.setVisible(false);
        refereeQualificationCB.setVisible(false);
        playerCourtRoleCB.setVisible(false);
        coachTeamRoleCB.setVisible(false);
        qualificationLable.setVisible(false);
        managePassTF.setVisible(false);
        managePassLable.setVisible(false);

        submit.setDisable(false); //now the user can try to push on the submit button.
    }


    public void checkDetailsFilled() throws IOException {

//        usernameTF.addKeyListener(new KeyAdapter() {
//
//            public void keyReleased(KeyEvent e) {
//                JTextField textField = (JTextField) e.getSource();
//                String text = textField.getText();
//
//                if (text != "")
//                    submit.setDisable(true);
//            }
//
//            public void keyTyped(KeyEvent e) {
//            }
//
//            public void keyPressed(KeyEvent e) {
//            }
//
//        });

        if(fullnameTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter your name.");
            return;
        }


        if(emailTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter your email address.");
            return;
        }

        if(usernameTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a username.");
            return;
        }

        if(passwordTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a password.");
            return;
        }

        //showAlert(Alert.AlertType.CONFIRMATION, "Registration Successful!", "Welcome " + usernameTF.getText());


        checkDetailsCorrect();
    }

      /*  usernameTF.getOnAction() {
            public void changedUpdate (DocumentEvent e){
                changed();
            }
            public void removeUpdate (DocumentEvent e){
                changed();
            }
            public void insertUpdate (DocumentEvent e){
                changed();
            }
        }
            public void changed() {
                if (usernameTF.getText().equals("")){
                    submit.setDisable(false);
                }
                else {
                    submit.setDisable(true);
                }
            }
        });
*/
     /*
            username = usernameTF.getText();
        fullname = fullnameTF.getText();
        password = passwordTF.getText();
        email = emailTF.getText();*/

       /* while (!detailsFilled) {
            //if (username != "" && fullname != "" && password != "" && email != "") {
            if ((!usernameTF.getText().trim().equals("")) && (!fullnameTF.getText().trim().equals("")) && (!passwordTF.getText().trim().equals("")) && (!emailTF.getText().trim().equals(""))) {
                submit.setDisable(false); //now the user can push this button.
                detailsFilled = true;
            }
        }
    }*/

    @FXML
    public void checkDetailsCorrect() throws IOException {

        username = usernameTF.getText();
        fullname = fullnameTF.getText();
        password = passwordTF.getText();
        email = emailTF.getText();


        //checks validation of the basic details entered:

        if (!fullname.chars().allMatch(Character::isLetter)) { //check full name validation (only letters)
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Your name must contains only characters. Please try again.");
            return;
        }

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) { //invalid email address.
            showAlert(Alert.AlertType.ERROR, "Form Error!", "The email address you entered is not valid. Please try again.");
            return;
        }

        if (!username.matches("^[a-zA-Z0-9]*$")) { //check username validation (letters and nums)
            showAlert(Alert.AlertType.ERROR, "Form Error!", "The username you chose must contains only characters and digits. Please try again.");
            return;
        }

        if (!password.matches("^[a-zA-Z0-9]*$")) { //check password validation  (letters and nums)
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Your password must contains only characters and digits. Please try again.");
            return;
        }

        if (password.length() < 6) { //check password validation  (letters and nums)
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Your password must be at least 6 characters. Please try again.");
            return;
        }



        //creates the correct user generator, to send through the service layer.

        if (userType.equals("Player") || userType.equals("Coach") || userType.equals("Manager") || userType.equals("Teamowner") || userType.equals("Referee"))
            generatorType = "PremiumUserGenerator";

        else if (userType.equals("AssociationRepresentative") || userType.equals("Administrator"))
            generatorType = "ManagementUserGenerator";

        else // a fan
            generatorType = "SimpleUserGenerator";



        // gets details by user type:
        String userCreatedMessage;
        boolean isSelected;

        if (userType.equals("Referee")){

            //checks whether all the fields are filled or not:
            isSelected = (refereeQualificationCB.getValue() != null );

            if(!isSelected) { //the user didn't choose any of the options.
                showAlert(Alert.AlertType.ERROR, "Form Error!", "You must choose a qualification in order to submit the form.");
                return;
            }
            qualification = refereeQualificationCB.getValue().toString();

            // checks whether the user exists in the system already.
            userCreatedMessage = userManagement.createNewUser(userName, password, "", userType, fullname, email, null, qualification, "", "", generatorType); //true if success //only necessary parameters.
        }

        else if (userType.equals("Player")){
            //checks whether all the fields are filled or not:
            isSelected = (playerCourtRoleCB.getValue() != null );

            if(!isSelected) { //the user didn't choose any of the options.
                showAlert(Alert.AlertType.ERROR, "Form Error!", "You must choose a court role in order to submit the form.");
                return;
            }
            courtRole = playerCourtRoleCB.getValue().toString();

            birthdateDP.setOnAction(event -> { // gets the value of the date picker
                birthDate = birthdateDP.getValue();
            });

            if(birthDate==null){
                showAlert(Alert.AlertType.ERROR, "Form Error!", "You must choose your birth date in order to submit the form.");
                return;
            }

            userCreatedMessage = userManagement.createNewUser(userName, password, "", userType, fullname, email, birthDate, "", courtRole, "", generatorType); //true if success
        }

        else if (userType.equals("Coach")){

            isSelected = (coachTeamRoleCB.getValue() != null );

            if(!isSelected) { //the user didn't choose any of the options.
                showAlert(Alert.AlertType.ERROR, "Form Error!", "You must choose your team role in order to submit the form.");
                return;
            }
            teamRole = coachTeamRoleCB.getValue().toString();

            userCreatedMessage = userManagement.createNewUser(userName, password, "", userType, fullname, email, null, "", "", teamRole, generatorType); //true if success
        }

        else if ((userType.equals("AssociationRepresentative")) || (userType.equals("Administrator"))){

            managementPassword = managePassTF.getText();

            if(managementPassword.equals("")){
                showAlert(Alert.AlertType.ERROR, "Form Error!", "You must enter your management password in order to submit the form.");
                return;
            }

            userCreatedMessage = userManagement.createNewUser(userName, password, managementPassword, userType, fullname, email, null, "", "", "", generatorType); //true if success
        }

        else { // fan / manager / teamowner
            userCreatedMessage = userManagement.createNewUser(userName, password, "", userType, fullname, email, null, "", "", "", generatorType); // only the basic information.
        }



        if(userCreatedMessage.equals("exist")){
            showAlert(Alert.AlertType.ERROR, "Form Error!", "The username you choose is already exist. Please choose a new one.");
            return;
        }

        else if(userCreatedMessage.equals("null")){
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Something went wrong, please check the details you entered.");
            return;
        }

        else { //everything's good
            super.userType = this.userType; //updates attributes of the main controller
            super.userName = this.username;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Welcome abroad !");
            alert.setContentText(" Your user was added successfully to the system.");

            alert.showAndWait();

//            showAlert(Alert.AlertType.CONFIRMATION, "Form Information", "Welcome abroad ! Your user was added successfully to the system.");
            goToLanding();
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }


    public void typeFan(ActionEvent actionEvent) {
        userType = "Fan";

        try {
            allExtraFieldsOff();
        }
        catch (IOException i){

        }
    }

    public void typeTeamOwner(ActionEvent actionEvent) {
        userType = "TeamOwner";

        try {
            allExtraFieldsOff();
        }
        catch (IOException i){

        }
    }

    public void typeManager(ActionEvent actionEvent) {
        userType = "Manager";

        try {
            allExtraFieldsOff();
        }
        catch (IOException i){

        }
    }

    public void typeAdministrator(ActionEvent actionEvent) {

        try {
            allExtraFieldsOff();
        }
        catch (IOException i){

        }

        managePassTF.setVisible(true);
        managePassLable.setVisible(true);

        userType = "Administrator";

        submit.setDisable(false); //now the user can try pressing the submit button.
    }

    public void typeAssociationRepresentative(ActionEvent actionEvent) {

        managePassTF.setVisible(true);
        managePassLable.setVisible(true);

        userType = "AssociationRepresentative";

        try {
            allExtraFieldsOff();
        }
        catch (IOException i){

        }

        submit.setDisable(false); //now the user can try pressing the submit button.
    }
}
