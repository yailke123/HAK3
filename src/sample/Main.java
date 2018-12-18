package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Stage primaryStage;
    Timer myTimer;

    @Override
    public void start(Stage primaryStage) throws Exception{
        myTimer = new Timer();
        myTimer.startTime();
        System.out.println(myTimer.getTime());
//asdfghjk

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Katamino++");
        primaryStage.setScene(new Scene(root, 600 , 400));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
