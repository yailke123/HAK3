import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Time extends Application {

    Label lb;
    //Stage windows;
    private Integer seconds;
    Random rand;
    int r,g,b ;

    @Override
    public void start(Stage primaryStage) {
        //windows= primaryStage;
        Group root= new Group();
        lb= new Label();
        lb.setFont(Font.font(30));
        lb.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        lb.setTextFill(Color.RED);

        doTime();

        //HBox layout= new HBox(5);
        //layout.getChildren().add(lb);
        //root.getChildren().add(layout);
        //Scene scene= new Scene(root, 300,70, Color.BLACK);
        //windows.setScene(scene);
        //primaryStage.setScene(scene);
        //windows.show();

    }
    public void setTime(int secs)
    {
        seconds = secs;
    }
    //public void addlabel (Pane box){ box.getChildren().add(lb); }
    public void addlabel (Label ege){  ege.textProperty().bind(lb.textProperty()); ege.textFillProperty().bind(lb.textFillProperty());ege.fontProperty().bind(lb.fontProperty());}//.textProperty().bind(); } lable1.textProperty().bind(text1.textProperty());
    public void doTime() {

        Timeline time= new Timeline();

        KeyFrame frame= new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                seconds--;
                double totalSec = TimeUnit.SECONDS.toMinutes(seconds);
                lb.setText( String.format("%02d:%02d",(int)totalSec , seconds%60 ));
                if (seconds >10) {
                    rand = new Random();
                    r = rand.nextInt(210);
                    g = rand.nextInt(210);
                    b = rand.nextInt(210);
                    lb.setTextFill(Color.rgb(r, g, b));
                }
                else if (seconds >0 && seconds <=10)
                {lb.setTextFill(Color.RED);}
                if(seconds<=0){
                    time.stop();
                    lb.setText("Time Over");
                }
            }
        });

        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(frame);
        if(time!=null){
            time.stop();
        }
        time.play();

    }
    public static void main(String[] args) {
        launch(args);
    }
}