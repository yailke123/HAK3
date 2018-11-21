package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeaderboardController {

    @FXML
    public ListView easyLeaderboard,  mediumLeaderboard, hardLeaderboard;
    public Button back;

    public void initialize(){
        fillEasyLeaderboard();
        back.setStyle(
                "-fx-background-radius: 5em; "
        );

    };

    public void fillEasyLeaderboard(){

        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> list1 = FXCollections.observableArrayList();
        ObservableList<String> list2 = FXCollections.observableArrayList();
        list.add("ego 31");
        list.add("yavuz 3");

        easyLeaderboard.setItems(list);


        list1.add("zego 20");
        list1.add("david 0");
        //easyLeaderboard.setStyle("-fx-control-inner-background: pink ; -fx-text-fill: black; -fx-alignment: center; -fx-font-family: 'Courier New' ;");

        mediumLeaderboard.setItems(list1);

        list2.add("yagiz 7");
        hardLeaderboard.setItems(list2);
        easyLeaderboard.setVisible(true);
    }

    public void backClicked()throws Exception{
        Parent loader = FXMLLoader.load(getClass().getResource("sample.fxml"));//Creates a Parent called loader and assign it as leaderboard.FXML
        Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"
        Stage app_stage = (Stage)back.getScene().getWindow();
        app_stage.setScene(scene); //This sets the scene as scene
        app_stage.show(); // this shows the scene
    }

}
