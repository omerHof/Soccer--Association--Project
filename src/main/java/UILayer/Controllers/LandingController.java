package UILayer.Controllers;

import UILayer.Main;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class LandingController extends Controller {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
    }
}
