import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
    /**
     * @author Christopher Sequeira
     *
     * LOGICAL ERROR
     * VBox layout = new VBox(10);
     *         layout.getChildren().addAll(label); //does not add the buttons to the scene layout
     *         layout.setAlignment(Pos.CENTER);
     *
     * LOGICAL ERROR FIX
     * Intent: create simple scene with String text and two buttons, to call any time a confirmation box is needed
     * Problem: label was defined, buttons were defined, but the layout did not include the buttons
     * Fix: added the buttons to the layout in the line layout.getChildren().addAll(label, yesButton, noButton);
     *
     * FUTURE ENHANCEMENT
     * the width of the window could be set to dynamic in case the String parameter entered is longer than the default width
     */

    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        window.setOnCloseRequest(e-> {
            e.consume();
            ConfirmBox.display("Confirm", "Are you sure?");
            boolean result = ConfirmBox.answer;
            if (result)
                window.close();
        });

        Button yesButton = new Button("Yes");
            yesButton.setOnAction(e-> {
                answer = true;
                window.close();
            });

        Button noButton = new Button("No");
            noButton.setOnAction(e-> {
                answer = false;
                window.close();
            });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}