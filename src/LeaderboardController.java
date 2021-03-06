import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeaderboardController {

    @FXML
    private JFXTreeTableView<Person> easyLeaderboard;
    @FXML
    private JFXTreeTableView<Person> mediumLeaderboard;
    @FXML
    private JFXTreeTableView<Person> hardLeaderboard;
    public Button back;
    private Leaderboard easy;
    private Leaderboard medium;
    private Leaderboard hard;

    public void initialize() throws IOException {
//
        easy = new Leaderboard();
        medium = new Leaderboard();
        hard = new Leaderboard();

        easy.getLeaderboard(1);
        medium.getLeaderboard(2);
        hard.getLeaderboard(3);

        User[] easyArr = easy.getLeaderboardArray();
        User[] mediumArr = medium.getLeaderboardArray();
        User[] hardArr = hard.getLeaderboardArray();

       //EASY POS COLUMN
        JFXTreeTableColumn<Person, String> pos = new JFXTreeTableColumn<>("Position");
        pos.setPrefWidth(100);
        pos.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {

                return param.getValue().getValue().pos;
            }
        });
        //EASY NAME COLUMN
        JFXTreeTableColumn<Person, String> name = new JFXTreeTableColumn<>("Name");
        name.setPrefWidth(273);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {

                return param.getValue().getValue().name;
            }
        });

        //EASY SCORE COLUMN
        JFXTreeTableColumn<Person, String> score = new JFXTreeTableColumn<>("Score");
        score.setPrefWidth(273);
        score.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {

                return param.getValue().getValue().score;
            }
        });

        //EASY BOARD FILLING
        ObservableList<Person> persons = FXCollections.observableArrayList();
        for(int i = 0; i< 10; i++){
            if( easyArr[i].getScore() == 1000)
                break;
            persons.add(new Person(easyArr[i].getUsername(), easyArr[i].getScore()+"", (i+1)+"."));
        }


        final TreeItem<Person> root = new RecursiveTreeItem<Person>(persons, RecursiveTreeObject::getChildren);
        easyLeaderboard.getColumns().setAll(pos, name,score);
        easyLeaderboard.setRoot(root);
        easyLeaderboard.setShowRoot(false);

        //Medium POS COLUMN
        JFXTreeTableColumn<Person, String> pos2 = new JFXTreeTableColumn<>("Position");
        pos2.setPrefWidth(100);
        pos2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {

                return param.getValue().getValue().pos;
            }
        });
        //MEDIUM NAME COLUMN
        JFXTreeTableColumn<Person, String> name2 = new JFXTreeTableColumn<>("Name");
        name2.setPrefWidth(273);
        name2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {

                return param.getValue().getValue().name;
            }
        });

        //MEDIUM SCORE COLUMN
        JFXTreeTableColumn<Person, String> score2 = new JFXTreeTableColumn<>("Score");
        score2.setPrefWidth(273);
        score2.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {

                return param.getValue().getValue().score;
            }
        });

        //MEDIUM BOARD FILLING
        ObservableList<Person> persons2 = FXCollections.observableArrayList();
        for(int i = 0; i< 10; i++){
            if( mediumArr[i].getScore() == 1000)
                break;
            persons2.add(new Person(mediumArr[i].getUsername(), mediumArr[i].getScore()+"", (i+1)+"."));
        }

        final TreeItem<Person> root2 = new RecursiveTreeItem<Person>(persons2, RecursiveTreeObject::getChildren);
        mediumLeaderboard.getColumns().setAll(pos2, name2,score2);
        mediumLeaderboard.setRoot(root2);
        mediumLeaderboard.setShowRoot(false);;

        //HARD POS COLUMN
        JFXTreeTableColumn<Person, String> pos3 = new JFXTreeTableColumn<>("Position");
        pos3.setPrefWidth(100);
        pos3.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {

                return param.getValue().getValue().pos;
            }
        });
        //HARD NAME COLUMN
        JFXTreeTableColumn<Person, String> name3 = new JFXTreeTableColumn<>("Name");
        name3.setPrefWidth(273);
        name3.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {

                return param.getValue().getValue().name;
            }
        });

        //HARD SCORE COLUMN
        JFXTreeTableColumn<Person, String> score3 = new JFXTreeTableColumn<>("Score");
        score3.setPrefWidth(273);
        score3.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {

                return param.getValue().getValue().score;
            }
        });

        //HARD BOARD FILLING
        ObservableList<Person> persons3 = FXCollections.observableArrayList();
        for(int i = 0; i< 10; i++){
            if( hardArr[i].getScore() == 1000)
                break;
            persons3.add(new Person(hardArr[i].getUsername(), hardArr[i].getScore()+"", (i+1)+"."));
        }

        final TreeItem<Person> root3 = new RecursiveTreeItem<Person>(persons3, RecursiveTreeObject::getChildren);
        hardLeaderboard.getColumns().setAll(pos3, name3,score3);
        hardLeaderboard.setRoot(root3);
        hardLeaderboard.setShowRoot(false);
    }
//

    class Person extends RecursiveTreeObject<Person> {
        StringProperty name;
        StringProperty score;
        StringProperty pos;
        public Person( String name, String score, String pos){
            this.name = new SimpleStringProperty(name);
            this.score = new SimpleStringProperty(score);
            this.pos = new SimpleStringProperty(pos);
        }
    }
    public void fillEasyLeaderboard(){

    }

    public void backClicked()throws Exception{
        Parent loader = FXMLLoader.load(getClass().getResource("fxml/new.fxml"));//Creates a Parent called loader and assign it as leaderboard.FXML
        Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"
        Stage app_stage = (Stage)back.getScene().getWindow();
        app_stage.setScene(scene); //This sets the scene as scene
        app_stage.show(); // this shows the scene
    }

}
