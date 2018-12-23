import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Time extends Application {

    private Label lb;
    private Integer seconds;
    private Random rand;
    private int r,g,b ;

    @Override
    public void start(Stage primaryStage) {
        lb= new Label();
        lb.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));

        doTime();
    }
    public void setTime(int secs)
    {
        seconds = secs;
    }
    public void addlabel (Label ege){  ege.textProperty().bind(lb.textProperty()); ege.textFillProperty().bind(lb.textFillProperty());ege.fontProperty().bind(lb.fontProperty());}
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
                    r = rand.nextInt(210)+45;
                    g = rand.nextInt(210)+45;
                    b = rand.nextInt(210)+45;
                    lb.setTextFill(Color.rgb(r, g, b));
                }
                else if (seconds >0 && seconds <=10)
                {lb.setTextFill(Color.WHITE);}
                else if(seconds<=0){
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