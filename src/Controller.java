
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



public class Controller {
    public Button startButton, leaderboardButton, createBoardButton, exitButton, soundButton;


    public void startButtonClicked()throws Exception{
        Parent loader = FXMLLoader.load(getClass().getResource("fxml/game.fxml"));
        Scene scene = new Scene(loader);
        Stage app_stage = (Stage)startButton.getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    public void leaderboardClicked()throws Exception{
        Parent loader = FXMLLoader.load(getClass().getResource("fxml/leaderboard3.fxml" ));//Creates a Parent called loader and assign it as leaderboard.FXML
        Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"
        Stage app_stage = (Stage)leaderboardButton.getScene().getWindow();
        app_stage.setScene(scene); //This sets the scene as scene
        app_stage.setMaximized(true);
        app_stage.show(); // this shows the scene

    }

    public void createBoardButtonClicked()throws  Exception{
        createBoardButton.setOnAction(event -> {TextField text = new TextField("Enter Username");
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Enter Username");
            window.setMinWidth(250);
            Button saveButton = new Button ("OK");

            VBox layout = new VBox(10);
            layout.getChildren().addAll(text,saveButton);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene (layout);
            window.setScene(scene);
            window.showAndWait();

            layout.getChildren().remove(text);
            layout.getChildren().remove(saveButton);
            Text success = new Text("Saved Successfully");
            window.setTitle("Saved");
            layout.getChildren().add(success);
            window.show();
        });
        Parent loader = FXMLLoader.load(getClass().getResource("fxml/createBoardScreen.fxml"));
        Scene scene = new Scene(loader);
        Stage app_stage = (Stage)createBoardButton.getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    public void exitButtonClicked(){
        System.exit(0);
    }

    //TODO
    public void soundButtonClicked(){

        if (soundButton.getText().equals("Sound On")) {

            soundButton.setText("Sound Off");
        }else{
            soundButton.setText("Sound On");
        }
    }
}
