package sample;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class CreateBoard {
    public enum Color {
        BLACK, WHITE, DEEPPINK, GRAY, SPRINGGREEN, RED, YELLOW, DEEPSKYBLUE, PURPLE, ORANGE
    }
    public Button back,test;
    private String userBoardName;
    private String choosenColor = "DEEPPINK"; // inital color is pink
    public void backClicked()throws Exception{
        Parent loader = FXMLLoader.load(getClass().getResource("sample.fxml"));//Creates a Parent called loader and assign it as leaderboard.FXML
        Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"
        Stage app_stage = (Stage)back.getScene().getWindow();
        app_stage.setScene(scene); //This sets the scene as scene
        app_stage.show(); // this shows the scene
    }

    public void blackClicked()throws Exception{ choosenColor = "BLACK"; }
    public void whiteClicked()throws Exception{ choosenColor = "WHITE"; }
    public void pinkClicked()throws Exception{ choosenColor = "DEEPPINK"; }
    public void grayClicked()throws Exception{ choosenColor = "GRAY"; }
    public void greenClicked()throws Exception{ choosenColor = "SPRINGGREEN"; }
    public void redClicked()throws Exception{ choosenColor = "RED"; }
    public void yellowClicked()throws Exception{ choosenColor = "YELLOW"; }
    public void blueClicked()throws Exception{ choosenColor = "DEEPSKYBLUE"; }
    public void purpleClicked()throws Exception{ choosenColor = "PURPLE"; }
    public void orangeClicked()throws Exception{ choosenColor = "ORANGE"; }

    public void testClicked()throws Exception{ }

    public void clearClicked()throws Exception{
         clear();
    }


    @FXML
    private GridPane grid ;

    public void initialize() {

        //Save board button and its function
        test.setOnAction(event -> {TextField text = new TextField("Enter Level Name");
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Save Board");
        window.setMinWidth(450);
        window.setResizable(false);
        Button saveButton = new Button ("Save");
            final boolean[] saved = {false};
        saveButton.setOnAction(e -> {
          System.out.println(text.getText());
          userBoardName = text.getText();
          String dir =System.getProperty("user.dir");
          dir = dir  + "\\src\\boards\\" + userBoardName;
          File f = new File(dir);
          int i = 1;
          int len = dir.length();
          while(f.exists() ) {
                if(i>1)
                    dir =dir.substring(0,len);
                dir = dir +"("+i+")";
                i++;
                f = new File(dir);
          }
          new File(dir).mkdirs();
          createCustom(dir,userBoardName);
          saved[0] =true;
          window.close();

        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(text,saveButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene (layout);
        window.setScene(scene);
        window.showAndWait();

        layout.getChildren().remove(text);
        layout.getChildren().remove(saveButton);
        if(saved[0])
        {
        Text success = new Text("Saved Successfully");
        window.setTitle("Saved");
        layout.getChildren().add(success);
        window.show();
        //window.addEventHandler();
        clear();
        }
        });

        //Adding white panes to every grid
        int numCols = 20 ;
        int numRows = 20 ;

        for (int i = 0 ; i < numCols ; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.SOMETIMES);
            grid.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0 ; i < numRows ; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.SOMETIMES);
            grid.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0 ; i < numCols ; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(i, j);
            }
        }
        clear();
    }

    @FXML
    private void clear(){
        ObservableList<Node> childrens = grid.getChildren();
        for (Node node : childrens) {
            node.setStyle("-fx-background-color:WHITE");
            node.setStyle("-fx-border-color: BLACK" );//-fx-border-insets: BLACK;");
        }
    }

    private void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> {
            String color = "-fx-background-color:" + choosenColor + ";";
            pane.setStyle(color);
            if(choosenColor.equals("WHITE"))
                pane.setStyle("-fx-border-color: BLACK" );//-fx-border-insets: BLACK;");
           // System.out.println(pane.getStyle());
            //System.out.printf("Mouse enetered cell [%d, %d]%n", colIndex, rowIndex);
        });

        pane.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
           // if( e.isPrimaryButtonDown() && e.isSecondaryButtonDown()) {
            //    System.out.println( "Both down");
           // } else
             if( e.isShiftDown()) {
                String color = "-fx-background-color:" + choosenColor + ";";
                pane.setStyle(color);
                 //System.out.println(colIndex + " " + rowIndex + " " + grid.getChildren().get(rowIndex+20*colIndex).getStyle());
                 if(choosenColor.equals("WHITE"))
                     pane.setStyle("-fx-border-color: BLACK" );//-fx-border-insets: BLACK;");
            }
        });

        grid.add(pane, colIndex, rowIndex);
    }

    private void createCustom(String directory,String name)
    {
        ObservableList<Node> childrens = grid.getChildren();
        String style,color,lastValue;
        int colorNum;
        try {
           PrintWriter writer = new PrintWriter(directory +"//"+ name + ".txt", "UTF-8");
            PrintWriter infowriter = new PrintWriter(directory +"//"+ name + "Info.txt", "UTF-8");
            PrintWriter blockwriter = new PrintWriter(directory +"//"+ name + "blocks.txt", "UTF-8");
            /* for (Node node : childrens) {
                style = node.getStyle();
                if(style.length() > 20 && style.substring(0, 20).equals("-fx-background-color")) {
                    color = style.substring(21,style.length()-1);
                    colorNum = Color.valueOf(color).ordinal();
                    if (colorNum != 1) {
                        lastValue = "1" + colorNum;
                        writer.println(lastValue);
                    }
                    else
                        writer.println("01");
                }
                else
                    writer.println("01");
            }*/
           Node node;

            for (int i =0;i<20;i++)
            {
                for (int j =0;j<20;j++) {
                    node  = childrens.get(i+(20*j));
                    //System.out.print("j is "+j+", i is " +i+ " ");
                    style = node.getStyle();
                    //System.out.println(style);
                    if (style.length() > 20 && style.substring(0, 20).equals("-fx-background-color")) {
                        color = style.substring(21, style.length() - 1);
                        colorNum = Color.valueOf(color).ordinal();
                        if (colorNum != 1) {
                            lastValue = "1" + colorNum;
                            //System.out.println(j+20*i  +" "+lastValue);
                            writer.println(lastValue);
                        }
                        else
                            writer.println("01");
                    }
                    else
                        writer.println("01");
                }
            }
            writer.close();
        }catch (IOException e){
        //do something 
        }
    }
}
