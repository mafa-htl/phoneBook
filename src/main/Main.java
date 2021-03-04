/**class Main
 * @author Matteo Falkenberg
 * @version 1.6, 04.03.2021
 */

package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/viewcontroller/PhonebookV.fxml"));
        primaryStage.setTitle("Phonebook");
        primaryStage.setScene(new Scene(root, 320, 340));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
