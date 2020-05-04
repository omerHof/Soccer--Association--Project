package UILayer.Controllers;

import UILayer.Main;
import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LandingController extends Controller {

    @FXML
    JFXButton mainLabel;

    @FXML
    Screen screen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Stage s = Main.getStage();
        s.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });
        screen = Screen.getPrimary();
    }



}
