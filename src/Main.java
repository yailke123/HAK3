import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    Stage primaryStage;
    Timer myTimer;

    @Override
    public void start(Stage primaryStage) throws Exception{
        myTimer = new Timer();
        myTimer.startTime();
        System.out.println(myTimer.getTime());


        Parent root = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Katamino++");
        Scene primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.setMaximized(true);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
