import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;

public class CreateBoard {
    public enum Color {
        BLACK, WHITE, DEEPPINK, GRAY, SPRINGGREEN, RED, YELLOW, DEEPSKYBLUE, PURPLE, ORANGE
    }
    public Pane blackButton,pinkButton,grayButton,greenButton,redButton,yellowButton,blueButton,purpleButton,orangeButton;
    public Button back, test;
    public GridPane block0Grid, block1Grid, block2Grid, block3Grid, block4Grid, block5Grid, block6Grid, block7Grid, block8Grid, block9Grid;
    private String userBoardName,dir;
    private String choosenColor = "DEEPPINK"; // inital color is pink

    private  String [] hints ={"Get Better","E = mc^2", "Use Shift and hover over cells to paint", "zego adam"};
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
        Parent loader = FXMLLoader.load(getClass().getResource("fxml/new.fxml"));//Creates a Parent called loader and assign it as sample.FXML
        Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"
        Stage app_stage = (Stage)back.getScene().getWindow();
        app_stage.setScene(scene); //This sets the scene as scene

        app_stage.show(); // this shows the scene
    }

    public void blackClicked() throws Exception {
        choosenColor = "BLACK";
        clearPanes();
        blackButton.setStyle("-fx-background-color: BLACK;-fx-border-color: BLUE;-fx-border-width: 8px;");

    }

    public void whiteClicked() throws Exception {
        choosenColor = "WHITE";
        clearPanes();
    }

    public void pinkClicked() throws Exception {
        choosenColor = "DEEPPINK";
        clearPanes();
        pinkButton.setStyle("-fx-background-color: DEEPPINK;-fx-border-color: BLUE;-fx-border-width: 8px;");
    }

    public void grayClicked() throws Exception {
        choosenColor = "GRAY";
        clearPanes();
        grayButton.setStyle("-fx-background-color: GRAY;-fx-border-color: BLUE;-fx-border-width: 8px;");
    }

    public void greenClicked() throws Exception {
        choosenColor = "SPRINGGREEN";
        clearPanes();
        greenButton.setStyle("-fx-background-color: SPRINGGREEN;-fx-border-color: BLUE;-fx-border-width: 8px;");
    }

    public void redClicked() throws Exception {
        choosenColor = "RED";
        clearPanes();
        redButton.setStyle("-fx-background-color: RED;-fx-border-color: BLUE;-fx-border-width: 8px;");
    }

    public void yellowClicked() throws Exception {
        choosenColor = "YELLOW";
        clearPanes();
        yellowButton.setStyle("-fx-background-color: YELLOW;-fx-border-color: BLUE;-fx-border-width: 8px;");
    }

    public void blueClicked() throws Exception {
        choosenColor = "DEEPSKYBLUE";
        clearPanes();
        blueButton.setStyle("-fx-background-color: DEEPSKYBLUE;-fx-border-color: BLUE;-fx-border-width: 8px;");
    }

    public void purpleClicked() throws Exception {
        choosenColor = "PURPLE";
        clearPanes();
        purpleButton.setStyle("-fx-background-color: PURPLE;-fx-border-color: BLUE;-fx-border-width: 8px;");
    }

    public void orangeClicked() throws Exception {
        choosenColor = "ORANGE";
        clearPanes();
        orangeButton.setStyle("-fx-background-color: ORANGE;-fx-border-color: BLUE;-fx-border-width: 8px;");
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
//    @FXML
//    private ChoiceBox<String> levelSelection;

    //This method is used for creating level thumbnails by taking snapshots of the grid
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
        //initializing choiceboxes for blcoks
        blockNum0.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30); blockNum0.setValue(0);
        blockNum1.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30); blockNum1.setValue(0);
        blockNum2.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30); blockNum2.setValue(0);
        blockNum3.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30); blockNum3.setValue(0);
        blockNum4.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30); blockNum4.setValue(0);
        blockNum5.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30); blockNum5.setValue(0);
        blockNum6.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30); blockNum6.setValue(0);
        blockNum7.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30); blockNum7.setValue(0);
        blockNum8.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30); blockNum8.setValue(0);
        blockNum9.getItems().addAll(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30); blockNum9.setValue(0);
       // levelSelection.getItems().addAll("Easy", "Medium","Hard");levelSelection.setValue("Easy");


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
        BorderPane bp = new BorderPane();
        bp.setCenter(grid);
        Scene scene2 = new Scene(bp, 600, 600);

         takeSnapShot(scene2, dir);
         try {
            backClicked();
        } catch (Exception e) {
            e.printStackTrace();
        }
            //clear();
        }
        });

        //drawing the blocks on the right side of the screen
        createBlocks();

        //Adding white panes to every grid in gridppane
        int numCols = 20;
        int numRows = 20;

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
            node.setStyle("-fx-border-color: BLACK" );
        }
    }
    //method used for coloring the board, the user color determines the color of the pane
    private void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> {
            String color = "-fx-background-color:" + choosenColor + ";";
            pane.setStyle(color);
            if(choosenColor.equals("WHITE"))
                pane.setStyle("-fx-border-color: BLACK" );
        });

        pane.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
             if( e.isShiftDown()) { //if shift is down the user can hover over multiple panes to color multiple panes
                String color = "-fx-background-color:" + choosenColor + ";";
                pane.setStyle(color);
                 if(choosenColor.equals("WHITE"))
                     pane.setStyle("-fx-border-color: BLACK" );
            }
        });
        grid.add(pane, colIndex, rowIndex);
    }

    //method for generating the files for custom boards
    private void createCustom(String directory,String name,int nameInt)
    {
        ObservableList<Node> childrens = grid.getChildren();
        String style,color,lastValue;
        int colorNum;
        int noOfBlocks = 0;
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
            }Node node;
            //writing cell info
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    node = childrens.get(i + (20 * j) + 1);
                    style = node.getStyle();
                    if (style.length() > 20 && style.substring(0, 20).equals("-fx-background-color")) {
                        color = style.substring(21, style.length() - 1);
                        colorNum = Color.valueOf(color).ordinal();
                        if (colorNum != 1) {
                            lastValue = "1" + colorNum;
                            writer.println(lastValue);
                            noOfBlocks++;
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
            int noOfCells=blockNum0.getValue()+blockNum1.getValue()*2+ blockNum2.getValue()*4+ blockNum3.getValue()*4+ blockNum4.getValue()*3+blockNum5.getValue()*2+blockNum6.getValue()*4+blockNum7.getValue()*4+blockNum8.getValue()*5+blockNum9.getValue()*5;
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

            if (noOfBlocks>noOfCells)
                System.out.println("Not possible");

            if (noOfBlocks<25)
            {infowriter.print(1);}
            else if (noOfBlocks < 50)
            {infowriter.print(2);}
            else
                infowriter.print(3);
            infowriter.close();
        } catch (IOException e) {
            //do something
        }
    }


    private void createBlocks() {
        //Get gui grids
        GridPane[] guiBlockGrids = {block0Grid, block1Grid, block2Grid, block3Grid, block4Grid, block5Grid, block6Grid, block7Grid, block8Grid, block9Grid};

        int[] blockNumbers = {1, 1, 1, 1, 1, 1, 1, 1, 1,1};

        for (int blockIndex = 0; blockIndex < 10; blockIndex++) {
            if (blockNumbers[blockIndex] != 0) {
                Block myBlock = new Block(Block.BlockShape.values()[blockIndex]);
                Cell[][] blockGrid = myBlock.getBlockShape();

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (blockGrid[i][j].getVisible()) {
                            System.out.print(blockGrid[i][j].getVisible() + ",");
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: SPRINGGREEN;");
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            guiBlockGrids[blockIndex].setConstraints(canvas, i, j);
                            guiBlockGrids[blockIndex].getChildren().addAll(canvas);
                        }
                    }
                }
            }
        }
    }

    private void clearPanes()
    {
        blackButton.setStyle("-fx-background-color: BLACK");
        pinkButton.setStyle("-fx-background-color: DEEPPINK" );
        grayButton.setStyle("-fx-background-color: GRAY");
        greenButton.setStyle("-fx-background-color: SPRINGGREEN");
        redButton.setStyle("-fx-background-color: RED");
        yellowButton.setStyle("-fx-background-color: YELLOW");
        blueButton.setStyle("-fx-background-color: DEEPSKYBLUE");
        purpleButton.setStyle("-fx-background-color: PURPLE");
        orangeButton.setStyle("-fx-background-color: ORANGE");
    }
}
