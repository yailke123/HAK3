import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;
import java.net.URL;

public class Main extends Application {
    Stage primaryStage;




    @Override
    public void start(Stage primaryStage) throws Exception{

        Stage test = new Stage();
      //  MusicPlayer ege = new MusicPlayer();
       // ege.start(test);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        Parent root = FXMLLoader.load(getClass().getResource("fxml/new.fxml"));
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Katamino++");
        Scene primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.setMaximized(true);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.show();//
    }

    public static void main(String[] args) {
        launch(args);
    }
}
