package UILayer;


import UILayer.Controllers.LandingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
    private static Stage primaryStage;
    Parent root;
    //private static MyViewModel viewModel;

    //public static MyViewModel getViewModel(){return viewModel;}




    @Override
    public void start(Stage primaryStage) throws Exception{

        setStage(primaryStage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Landing.fxml"));
        root = fxmlLoader.load();
        primaryStage.setTitle("SOCCER SYSTEM 2020");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(700);

        LandingController lc = fxmlLoader.getController();
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void setStage(Stage stage) {
        Main.primaryStage = stage;
    }

    public static Stage getStage() {
        return primaryStage;
    }
}
