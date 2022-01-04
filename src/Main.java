import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    /**
     * @author Christopher Sequeira
     *
     * LOGICAL ERROR
     * Window size: stage size is defined in both Controller.fxml and Main.java
     *      Main.java explicit declaration of stage size takes precedence
     *
     * LOGICAL ERROR FIX
     * Intent: size stage properly for displaying data
     * Problem: size is defined in two locations, causing confusion for any future programmers
     * Fix: added comment to the line where the scene is set so future programmers know that there are two declarations
     *
     * FUTURE ENHANCEMENT
     * the width of the window could be set to dynamic in case the data displayed needs more or less room in the future
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Controller.fxml")));
        primaryStage.setTitle("C. Sequeira C482 PA Task");
        primaryStage.setScene(new Scene(root, 950, 625)); // size also defined in Controller.fxml
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
