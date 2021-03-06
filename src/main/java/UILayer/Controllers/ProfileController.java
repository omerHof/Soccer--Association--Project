package UILayer.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileController extends Controller {

    String userFullName;
    String userEmail;
    String coachRole;
    String playerPosition;
    String refereeQualification;

    boolean showFullName;
    boolean showEmail;
    boolean showRole;
    boolean showPosition;
    boolean showQualification;

    ObservableList<String> coachRoleOptions = FXCollections.observableArrayList();
    ObservableList<String> playerPositionsOptions = FXCollections.observableArrayList();
    ObservableList<String> refereeQualificationsOptions = FXCollections.observableArrayList();

    @FXML
    JFXTextField coachLabel;

    @FXML
    JFXTextField playerLabel;

    @FXML
    JFXTextField refereeLabel;

    @FXML
    JFXButton coachEditLabel;

    @FXML
    JFXButton playerEdit;

    @FXML
    JFXButton refereeEdit;

    @FXML
    JFXTextField userNameDetail;

    @FXML
    JFXTextField password;

    @FXML
    JFXTextField fullName;

    @FXML
    JFXTextField email;

    @FXML
    JFXTextArea fullNameEditor;

    @FXML
    JFXTextArea emailEditor;

    @FXML
    JFXButton submitFullName;

    @FXML
    JFXButton submitEmail;

    @FXML
    ChoiceBox positionChoiseBox;

    @FXML
    ChoiceBox roleChoiseBox;

    @FXML
    ChoiceBox qualificationChoiseBox;

    @FXML
    JFXTextField roleDetails;

    @FXML
    JFXTextField playerDetails;
    @FXML
    JFXTextField refereeDetails;


    @FXML
    JFXButton submitCoachRole;

    @FXML
    JFXButton submitPlayerPosition;

    @FXML
    JFXButton submitRefereeQualification;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //find the user type
        userType = "Player";
        switch (userType) {
            case "Player":
                playerDetails.setVisible(true);
                playerLabel.setVisible(true);
                playerEdit.setVisible(true);
                break;
            case "Coach":
                coachLabel.setVisible(true);
                roleDetails.setVisible(true);
                coachEditLabel.setVisible(true);
                break;
            case "Referee":
                refereeLabel.setVisible(true);
                refereeDetails.setVisible(true);
                refereeEdit.setVisible(true);
                break;
            default:
                break;
        }

        //display his correct details
        userNameDetail.appendText("omer123");
        password.appendText("wooo123");
        fullName.appendText("omer hofman");
        email.appendText("omer@gmail.com");




        //create edit button for each detail beside password and username
        showFullName = false;
        showEmail = false;
        showRole = false;
        showPosition = false;

        coachRoleOptions.add("Head Coach");
        coachRoleOptions.add("Assistant Coach");
        coachRoleOptions.add("Fitness Coach");


        playerPositionsOptions.add("GoalKeeper");
        playerPositionsOptions.add("Defender");
        playerPositionsOptions.add("MidFielder");
        playerPositionsOptions.add("Striker");

        refereeQualificationsOptions.add("Var referee");
        refereeQualificationsOptions.add("Linesman referee");


        roleChoiseBox.setItems(coachRoleOptions);
        positionChoiseBox.setItems(playerPositionsOptions);
        qualificationChoiseBox.setItems(refereeQualificationsOptions);


        //open text field after press edit button
        //validate new details
        //submit to db


    }

    public void showEmailEditor(ActionEvent actionEvent) {
        if (showEmail==false){
            emailEditor.setVisible(true);
            submitEmail.setVisible(true);
            showEmail=true;
        }else{
            emailEditor.setVisible(false);
            submitEmail.setVisible(false);
            showEmail=false;
        }


    }

    public void showFullNameEditor(ActionEvent actionEvent) {
        if (showFullName==false){
            fullNameEditor.setVisible(true);
            submitFullName.setVisible(true);
            showFullName = true;
        } else{
            fullNameEditor.setVisible(false);
            submitFullName.setVisible(false);
            showFullName = false;
        }


    }

    public void submitFullName(ActionEvent actionEvent) {
        userFullName =  fullNameEditor.getText();
        if(checkOnlyLetters()){
            fullName.setText(userFullName);
            showFullNameEditor( actionEvent);
            fullNameEditor.setText("");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Invalid full name");
            alert.setContentText("Please enter letters only!");
            alert.showAndWait();
            fullNameEditor.setText("");
        }

    }

    private boolean checkOnlyLetters() {
        return ((!userFullName.equals(""))
                && (userFullName != null)
                && (userFullName.matches("^[a-zA-Z ]*$|| ")));
    }

    public void submitEmail(ActionEvent actionEvent) {
        userEmail =  emailEditor.getText();
        if(checkEmail()){
            email.setText(userEmail);
            showEmailEditor( actionEvent);
            emailEditor.setText("");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Invalid Email");
            alert.setContentText("Please enter a valid email only! example amir@gmail.com");
            alert.showAndWait();
            fullNameEditor.setText("");
        }

    }


    public void submitCoachRole(ActionEvent actionEvent) {
        coachRole =  roleChoiseBox.getValue().toString();
        roleDetails.setText(coachRole);
        showRoleEditor(actionEvent);
    }

    public void submitPlayerPosition(ActionEvent actionEvent) {
        playerPosition = positionChoiseBox.getValue().toString();
        playerDetails.setText(playerPosition);
        showPositionEditor(actionEvent);
    }

    private boolean checkEmail() {
        return ((!userEmail.equals(""))
                && (userEmail != null)
                && (userEmail.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")));
    }

    public void showPositionEditor(ActionEvent actionEvent) {
        if (showPosition==false){
            positionChoiseBox.setVisible(true);
            submitPlayerPosition.setVisible(true);
            showPosition = true;
        } else{
            positionChoiseBox.setVisible(false);
            submitPlayerPosition.setVisible(false);
            showPosition = false;
        }
    }

    public void showRoleEditor(ActionEvent actionEvent) {
        if (showRole==false){
            roleChoiseBox.setVisible(true);
            submitCoachRole.setVisible(true);
            showRole = true;
        } else{
            roleChoiseBox.setVisible(false);
            submitCoachRole.setVisible(false);
            showRole = false;
        }

    }


    public void showQualificationEditor(ActionEvent actionEvent) {
        if (showQualification==false){
            qualificationChoiseBox.setVisible(true);
            submitRefereeQualification.setVisible(true);
            showQualification = true;
        } else{
            qualificationChoiseBox.setVisible(false);
            submitRefereeQualification.setVisible(false);
            showQualification = false;
        }
    }

    public void submitRefereeQualification(ActionEvent actionEvent) {
        refereeQualification = qualificationChoiseBox.getValue().toString();
        refereeDetails.setText(refereeQualification);
        showQualificationEditor(actionEvent);
    }
}
