import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateBoard {
    public enum Color {
        BLACK, WHITE, DEEPPINK, GRAY, SPRINGGREEN, RED, YELLOW, DEEPSKYBLUE, PURPLE, ORANGE
    }

    public Button back, test;
    public GridPane block0Grid, block1Grid, block2Grid, block3Grid, block4Grid, block5Grid, block6Grid, block7Grid, block8Grid, block9Grid;
    private String userBoardName;
    private String dir;
    private String choosenColor = "DEEPPINK"; // inital color is pink

    private  String [] hints ={"Get Better","E = mc^2", "Use Shift and hover over cells to paint", "zego adam"};
    //private ChoiceBox<Integer> blockNum1 = new ChoiceBox<>();//=  new ChoiceBox(FXCollections.observableArrayList(hints));
    public void hintClicked() throws Exception {
        Random rand  = new Random();
        int randomNum = rand.nextInt((hints.length));
        String hint = hints[randomNum];
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hint");
        alert.setHeaderText(null);
        alert.setContentText(hint);
        alert.showAndWait();
    }

    public void backClicked() throws Exception {
        Parent loader = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));//Creates a Parent called loader and assign it as leaderboard.FXML
        Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"
        Stage app_stage = (Stage)back.getScene().getWindow();
        app_stage.setScene(scene); //This sets the scene as scene

        app_stage.show(); // this shows the scene
    }

    public void blackClicked() throws Exception {
        choosenColor = "BLACK";
    }

    public void whiteClicked() throws Exception {
        choosenColor = "WHITE";
    }

    public void pinkClicked() throws Exception {
        choosenColor = "DEEPPINK";
    }

    public void grayClicked() throws Exception {
        choosenColor = "GRAY";
    }

    public void greenClicked() throws Exception {
        choosenColor = "SPRINGGREEN";
    }

    public void redClicked() throws Exception {
        choosenColor = "RED";
    }

    public void yellowClicked() throws Exception {
        choosenColor = "YELLOW";
    }

    public void blueClicked() throws Exception {
        choosenColor = "DEEPSKYBLUE";
    }

    public void purpleClicked() throws Exception {
        choosenColor = "PURPLE";
    }

    public void orangeClicked() throws Exception {
        choosenColor = "ORANGE";
    }

    public void testClicked() throws Exception {
    }

    public void clearClicked()throws Exception{
         clear();
    }


    @FXML
    private GridPane grid;
    @FXML
    private ChoiceBox<Integer> blockNum0,blockNum1,blockNum2,blockNum3,blockNum4,blockNum5,blockNum6,blockNum7,blockNum8,blockNum9;


    private void takeSnapShot(Scene scene, String board) {
        WritableImage writableImage = new WritableImage((int)scene.getWidth(),(int)scene.getHeight());
        scene.snapshot(writableImage);

        File file = new File(
                dir   + "/board.png");

        try{
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage,null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        blockNum0.getItems().addAll(0,1,2,3,4,5,6,7,8,9); blockNum0.setValue(0);
        blockNum1.getItems().addAll(0,1,2,3,4,5,6,7,8,9); blockNum1.setValue(0);
        blockNum2.getItems().addAll(0,1,2,3,4,5,6,7,8,9); blockNum2.setValue(0);
        blockNum3.getItems().addAll(0,1,2,3,4,5,6,7,8,9); blockNum3.setValue(0);
        blockNum4.getItems().addAll(0,1,2,3,4,5,6,7,8,9); blockNum4.setValue(0);
        blockNum5.getItems().addAll(0,1,2,3,4,5,6,7,8,9); blockNum5.setValue(0);
        blockNum6.getItems().addAll(0,1,2,3,4,5,6,7,8,9); blockNum6.setValue(0);
        blockNum7.getItems().addAll(0,1,2,3,4,5,6,7,8,9); blockNum7.setValue(0);
        blockNum8.getItems().addAll(0,1,2,3,4,5,6,7,8,9); blockNum8.setValue(0);
        blockNum9.getItems().addAll(0,1,2,3,4,5,6,7,8,9); blockNum9.setValue(0);



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
          //System.out.println(text.getText());

          userBoardName = text.getText();
          dir =System.getProperty("user.dir");
          dir = dir  + "//src//boards//" + userBoardName;
          File f = new File(dir);
          int i = 0;
          int len = dir.length();
          while(f.exists() ) {
                i++;
                if(i>1)
                    dir =dir.substring(0,len);
                dir = dir +"("+i+")";

                f = new File(dir);
          }
          new File(dir).mkdirs();

          createCustom(dir,userBoardName,i);
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
        window.showAndWait();
        //window.addEventHandler();
          BorderPane bp = new BorderPane();
          bp.setCenter(grid);
          Scene scene2 = new Scene(bp, 400, 400);

         takeSnapShot(scene2, dir);
         try {
            backClicked();
        } catch (Exception e) {
            e.printStackTrace();
        }
            //clear();

        }
        });

        //Adding white panes to every grid
        int numCols = 20;
        int numRows = 20;
        createBlocks();

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

    private void createCustom(String directory,String name,int nameInt)
    {
        ObservableList<Node> childrens = grid.getChildren();
        String style,color,lastValue;
        int colorNum;
        try {
            PrintWriter writer;
            PrintWriter infowriter;
            PrintWriter blockwriter;
            if(nameInt != 0) {
                writer= new PrintWriter(directory + "//" + name + "(" + nameInt + ")" + ".txt", "UTF-8");
                infowriter = new PrintWriter(directory + "//" + name + "(" + nameInt + ")" + "Info.txt", "UTF-8");
                blockwriter = new PrintWriter(directory + "//" + name + "(" + nameInt + ")" + "blocks.txt", "UTF-8");
            }
            else {
                writer = new PrintWriter(directory + "//" + name + ".txt", "UTF-8");
                infowriter = new PrintWriter(directory + "//" + name + "Info.txt", "UTF-8");
                blockwriter = new PrintWriter(directory + "//" + name + "blocks.txt", "UTF-8");

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
            }Node node;
            //writing cell info
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    node = childrens.get(i + (20 * j) + 1);
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

           //writing block info
            blockwriter.println(blockNum0.getValue());
            blockwriter.println(blockNum1.getValue());
            blockwriter.println(blockNum2.getValue());
            blockwriter.println(blockNum3.getValue());
            blockwriter.println(blockNum4.getValue());
            blockwriter.println(blockNum5.getValue());
            blockwriter.println(blockNum6.getValue());
            blockwriter.println(blockNum7.getValue());
            blockwriter.println(blockNum8.getValue());
            blockwriter.println(blockNum9.getValue());

            blockwriter.close();
        } catch (IOException e) {
            //do something
        }
    }


    private void createBlocks() {
        //Get gui grids
        GridPane[] guiBlockGrids = {block0Grid, block1Grid, block2Grid, block3Grid, block4Grid, block5Grid, block6Grid, block7Grid, block8Grid, block9Grid};
        //Label[] guiBlockAmounts = {block0Amount , block1Amount , block2Amount , block3Amount , block4Amount , block5Amount , block6Amount , block7Amount , block8Amount , block9Amount, moveCountLabel};
        //Get blocks
        int[] blockNumbers = {1, 1, 1, 1, 1, 1, 1, 1, 1,1};//myBoard.getBoardBlocks();

        for (int blockIndex = 0; blockIndex < 10; blockIndex++) {
            if (blockNumbers[blockIndex] != 0) {
                Block myBlock = new Block(Block.BlockShape.values()[blockIndex]);
                Cell[][] blockGrid = myBlock.getBlockShape();
                List<ObjectProperty<EventHandler<? super MouseEvent>>> myList = new ArrayList<>();

//                guiBlockGrids[blockIndex].setGridLinesVisible(true);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (blockGrid[i][j].getVisible()) {
                            System.out.print(blockGrid[i][j].getVisible() + ",");
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: RED;");
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            //guiBlockAmounts[blockIndex].setText("x" + blockNumbers[blockIndex]);
                            guiBlockGrids[blockIndex].setConstraints(canvas, i, j);
                            guiBlockGrids[blockIndex].getChildren().addAll(canvas);
                        }
                    }
                }
            }
        }


    }
}
