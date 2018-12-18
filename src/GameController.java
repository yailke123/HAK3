
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class GameController {
    public GridPane boardPane;
    private Board myBoard;
    public enum Color {
        BLACK, WHITE, PINK, GRAY, GREEN, RED, YELLOW, BLUE, PURPLE, ORANGE
    }
    public Label timerLabel;
    public String boardName = "kalp";
    public Timer myTimer;
    public Button back;
    private int[] blockNumbers;
    public GridPane block0Grid , block1Grid , block2Grid , block3Grid , block4Grid , block5Grid , block6Grid , block7Grid , block8Grid , block9Grid ;
    public Label  block0Amount , block1Amount , block2Amount , block3Amount , block4Amount , block5Amount , block6Amount , block7Amount , block8Amount , block9Amount;
    private final StringProperty timeSeconds = new SimpleStringProperty("Time :");
    private Timeline timeline;



    //    public class Timer extends TimerTask{
//        private long startTime;
//        private long endTime;
//
//        public Timer(){
//            startTime = 0;
//            endTime = 0;
//        }
//
//        public void startTime(){
//            startTime = System.nanoTime();
//        }
//        public void endTime() {
//            endTime = System.nanoTime();
//        }
//        public String getTime(){
//            endTime();
//            long totalTime = endTime - startTime;
//
//            return String.format("%02d:%02d",
//                    TimeUnit.NANOSECONDS.toMinutes(totalTime) - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours(totalTime)),
//                    TimeUnit.NANOSECONDS.toSeconds(totalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(totalTime)));
//        }
//
//        @Override
//        public void run(){
//            Platform.runLater(()-> {
//                myTimer.startTime();
//                StringProperty timeSeconds = new SimpleStringProperty((myTimer.getTime()));
//                timerLabel.textProperty().bind(timeSeconds);
//                timerLabel.setText(getTime());
//                System.out.println(getTime());
//            });
//        }
//
//    }

//    private void updateTime() {
//        // increment seconds
//        String seconds = myTimer.getTime();
//        timeSeconds.set(seconds);
//    }
//
//
//    public void handle() {
//        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTime()));
//        timeline.setCycleCount(Animation.INDEFINITE); // repeat over and over again
//        timeSeconds.set("sdaflkhdsak");
//        timeline.play();
//    }

    public void initialize() throws Exception {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("");
        dialog.setHeaderText("");
        dialog.setContentText("Please enter board name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> boardName = name);

        createBoard();
        createBlocks();

//        timerLabel.textProperty().bind(timeSeconds);

    }

    public void backClicked()throws Exception{
        Parent loader = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));//Creates a Parent called loader and assign it as leaderboard.FXML
        Scene scene = new Scene(loader); //This creates a new scene called scene and assigns it as the Sample.FXML document which was named "loader"
        Stage app_stage = (Stage)back.getScene().getWindow();
        app_stage.setScene(scene); //This sets the scene as scene
        app_stage.show(); // this shows the scene
    }

    private void addEventDetectors( GridPane source, Pane target, String blockName) throws Exception{
        //Drag detected event handler is used for adding drag functionality to the boat node
        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //Drag was detected, start drap-and-drop gesture
                //Allow any transfer node
//                System.out.print(source.getId() + "DRAGDETECTED ");
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);
                //Put ImageView on dragboard
                ClipboardContent cbContent = new ClipboardContent();
                Image blockImage = new Image("blocks/"+blockName+".png");
                cbContent.putImage(blockImage);
//                cbContent.putImage;
                //cbContent.put()
                db.setContent(cbContent);
//                source.setVisible(false);
                event.consume();
            }
        });

        //Drag dropped draws the image to the receiving node
        target.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                //Data dropped
                //If there is an image on the dragboard, read it and use it
                Dragboard db = event.getDragboard();
                System.out.print("DROPPED ");
                boolean success = false;
                Node node = event.getPickResult().getIntersectedNode();
                if(db.hasImage()){
                    Integer cIndex = GridPane.getColumnIndex(node);
                    Integer rIndex = GridPane.getRowIndex(node);
                    int x = cIndex == null ? 0 : cIndex;
                    int y = rIndex == null ? 0 : rIndex;
                    System.out.print(x + " ");
                    System.out.println(y + " ");
                    System.out.println(blockName);

//                    System.out.println((event.getGestureSource()).equals(block1Grid));

                    if((event.getGestureSource()).equals(block0Grid)){
                        Cell[][] boardCells = myBoard.getBoardCells();
                        if( blockNumbers[0]!=0 && boardCells[y][x].getColor() != 1 && !boardCells[y][x].getFilled()){
                            String cellColor = (Color.values()[boardCells[y][x].getColor()]).toString();
                            System.out.println(x + cellColor + y);
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        System.out.println((int)source.getHeight()+ " " +source.getWidth() );
//                        boardPane.add(canvas, x, y, (int)(source.getHeight()/19.0), (int)(source.getWidth()/19.0))
                            boardPane.add(canvas, x, y, 1, 1);
                            boardCells[y][x].setFilled(true);
                            blockNumbers[0]--;
                            block0Amount.setText("x" + blockNumbers[0]);
                            if(blockNumbers[0]==0){
                                System.out.println("NOBLOCKSLEFT");
                                ((GridPane)event.getGestureSource()).setVisible(false);
                            }
                            success = true;
                        }
                    }

                    if((event.getGestureSource()).equals(block1Grid)){
                        Cell[][] boardCells = myBoard.getBoardCells();
                        if(blockNumbers[1]!=0 && boardCells[y][x].getColor() != 1 && !boardCells[y][x].getFilled()
                                && !boardCells[y-1][x].getFilled() && boardCells[y-1][x].getColor() != 1){
                            String cellColor = (Color.values()[boardCells[y][x].getColor()]).toString();
                            System.out.println(x + cellColor + y);
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        System.out.println((int)source.getHeight()+ " " +source.getWidth() );
//                        boardPane.add(canvas, x, y, (int)(source.getHeight()/19.0), (int)(source.getWidth()/19.0))
                            boardPane.add(canvas, x, y, 1, 1);
                            Pane canvas2 = new Pane();
                            cellColor = (Color.values()[boardCells[y-1][x].getColor()]).toString();
                            canvas2.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas2.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas2, x, y-1, 1, 1);

                            boardCells[y][x].setFilled(true);
                            boardCells[y-1][x].setFilled(true);
                            blockNumbers[1] --;
                            block1Amount.setText("x" + blockNumbers[1]);

                            success = true;
                        }
                    }

                    if((event.getGestureSource()).equals(block2Grid)){
                        Cell[][] boardCells = myBoard.getBoardCells();
                        if(blockNumbers[2]!=0 && boardCells[y][x].getColor() != 1 && !boardCells[y][x].getFilled()
                                && !boardCells[y-1][x].getFilled()
                                && !boardCells[y+1][x].getFilled()
                                && !boardCells[y][x-1].getFilled()
                                && boardCells[y-1][x].getColor() != 1
                                && boardCells[y+1][x].getColor() != 1
                                && boardCells[y][x-1].getColor() != 1){

                            //------------------------
                            String cellColor = (Color.values()[boardCells[y][x].getColor()]).toString();
//                            System.out.println(x + cellColor + y);
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        System.out.println((int)source.getHeight()+ " " +source.getWidth() );
//                        boardPane.add(canvas, x, y, (int)(source.getHeight()/19.0), (int)(source.getWidth()/19.0))
                            boardPane.add(canvas, x, y, 1, 1);

                            //-----------------------
                            Pane canvas2 = new Pane();
                            cellColor = (Color.values()[boardCells[y-1][x].getColor()]).toString();
                            canvas2.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas2.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas2, x, y-1, 1, 1);

                            //-----------------------
                            Pane canvas3 = new Pane();
                            cellColor = (Color.values()[boardCells[y+1][x].getColor()]).toString();
                            canvas3.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas3.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas3, x, y+1, 1, 1);

                            //-----------------------
                            Pane canvas4 = new Pane();
                            cellColor = (Color.values()[boardCells[y][x-1].getColor()]).toString();
                            canvas4.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas4.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas4, x-1, y, 1, 1);

                            boardCells[y][x].setFilled(true);
                            boardCells[y-1][x].setFilled(true);
                            boardCells[y+1][x].setFilled(true);
                            boardCells[y][x-1].setFilled(true);

                            blockNumbers[2] --;
                            block2Amount.setText("x" + blockNumbers[2]);
                            success = true;
                        }
                    }

                    if((event.getGestureSource()).equals(block3Grid)){
                        Cell[][] boardCells = myBoard.getBoardCells();
                        if( blockNumbers[3]!=0 && boardCells[y][x].getColor() != 1 && !boardCells[y][x].getFilled()
                                && !boardCells[y-1][x].getFilled()
                                && !boardCells[y+1][x].getFilled()
                                && !boardCells[y-1][x-1].getFilled()
                                && boardCells[y-1][x].getColor() != 1
                                && boardCells[y+1][x].getColor() != 1
                                && boardCells[y-1][x-1].getColor() != 1){

                            //------------------------
                            String cellColor = (Color.values()[boardCells[y][x].getColor()]).toString();
//                            System.out.println(x + cellColor + y);
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        System.out.println((int)source.getHeight()+ " " +source.getWidth() );
//                        boardPane.add(canvas, x, y, (int)(source.getHeight()/19.0), (int)(source.getWidth()/19.0))
                            boardPane.add(canvas, x, y, 1, 1);

                            //-----------------------
                            Pane canvas2 = new Pane();
                            cellColor = (Color.values()[boardCells[y-1][x].getColor()]).toString();
                            canvas2.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas2.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas2, x, y-1, 1, 1);

                            //-----------------------
                            Pane canvas3 = new Pane();
                            cellColor = (Color.values()[boardCells[y+1][x].getColor()]).toString();
                            canvas3.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas3.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas3, x, y+1, 1, 1);

                            //-----------------------
                            Pane canvas4 = new Pane();
                            cellColor = (Color.values()[boardCells[y-1][x-1].getColor()]).toString();
                            canvas4.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas4.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas4, x-1, y-1, 1, 1);

                            boardCells[y][x].setFilled(true);
                            boardCells[y-1][x].setFilled(true);
                            boardCells[y+1][x].setFilled(true);
                            boardCells[y-1][x-1].setFilled(true);

                            blockNumbers[3] --;
                            block3Amount.setText("x" + blockNumbers[3]);
                            success = true;
                        }
                    }

                    if((event.getGestureSource()).equals(block4Grid)){
                        Cell[][] boardCells = myBoard.getBoardCells();
                        if( blockNumbers[4]!=0 && boardCells[y][x].getColor() != 1 && !boardCells[y][x].getFilled()
                                && !boardCells[y-1][x].getFilled()
                                && !boardCells[y+1][x].getFilled()
                                && boardCells[y-1][x].getColor() != 1
                                && boardCells[y+1][x].getColor() != 1){

                            //------------------------
                            String cellColor = (Color.values()[boardCells[y][x].getColor()]).toString();
//                            System.out.println(x + cellColor + y);
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        System.out.println((int)source.getHeight()+ " " +source.getWidth() );
//                        boardPane.add(canvas, x, y, (int)(source.getHeight()/19.0), (int)(source.getWidth()/19.0))
                            boardPane.add(canvas, x, y, 1, 1);

                            //-----------------------
                            Pane canvas2 = new Pane();
                            cellColor = (Color.values()[boardCells[y-1][x].getColor()]).toString();
                            canvas2.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas2.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas2, x, y-1, 1, 1);

                            //-----------------------
                            Pane canvas3 = new Pane();
                            cellColor = (Color.values()[boardCells[y+1][x].getColor()]).toString();
                            canvas3.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas3.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas3, x, y+1, 1, 1);

                            boardCells[y][x].setFilled(true);
                            boardCells[y-1][x].setFilled(true);
                            boardCells[y+1][x].setFilled(true);

                            blockNumbers[4] --;
                            block4Amount.setText("x" + blockNumbers[4]);
                            success = true;
                        }
                    }

                    if((event.getGestureSource()).equals(block5Grid)){
                        Cell[][] boardCells = myBoard.getBoardCells();
                        if( blockNumbers[5]!=0 && boardCells[y][x].getColor() != 1
                                && !boardCells[y][x+1].getFilled()
                                && !boardCells[y+1][x].getFilled()
                                && boardCells[y][x+1].getColor() != 1
                                && boardCells[y+1][x].getColor() != 1){

                            //------------------------
                            String cellColor = (Color.values()[boardCells[y][x+1].getColor()]).toString();
//                            System.out.println(x + cellColor + y);
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        System.out.println((int)source.getHeight()+ " " +source.getWidth() );
//                        boardPane.add(canvas, x, y, (int)(source.getHeight()/19.0), (int)(source.getWidth()/19.0))
                            boardPane.add(canvas, x+1, y, 1, 1);

                            //-----------------------
                            Pane canvas2 = new Pane();
                            cellColor = (Color.values()[boardCells[y+1][x].getColor()]).toString();
                            canvas2.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas2.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas2, x, y+1, 1, 1);

                            boardCells[y+1][x].setFilled(true);
                            boardCells[y][x+1].setFilled(true);

                            blockNumbers[5] --;
                            block5Amount.setText("x" + blockNumbers[5]);
                            success = true;
                        }
                    }

                    if((event.getGestureSource()).equals(block6Grid)){
                        Cell[][] boardCells = myBoard.getBoardCells();
                        if( blockNumbers[6]!=0 && boardCells[y][x].getColor() != 1 && !boardCells[y][x].getFilled()
                                && !boardCells[y-1][x].getFilled()
                                && !boardCells[y-1][x-1].getFilled()
                                && !boardCells[y][x-1].getFilled()
                                && boardCells[y-1][x].getColor() != 1
                                && boardCells[y-1][x-1].getColor() != 1
                                && boardCells[y][x-1].getColor() != 1){

                            //------------------------
                            String cellColor = (Color.values()[boardCells[y][x].getColor()]).toString();
//                            System.out.println(x + cellColor + y);
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        System.out.println((int)source.getHeight()+ " " +source.getWidth() );
//                        boardPane.add(canvas, x, y, (int)(source.getHeight()/19.0), (int)(source.getWidth()/19.0))
                            boardPane.add(canvas, x, y, 1, 1);

                            //-----------------------
                            Pane canvas2 = new Pane();
                            cellColor = (Color.values()[boardCells[y-1][x].getColor()]).toString();
                            canvas2.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas2.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas2, x, y-1, 1, 1);

                            //-----------------------
                            Pane canvas3 = new Pane();
                            cellColor = (Color.values()[boardCells[y-1][x-1].getColor()]).toString();
                            canvas3.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas3.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas3, x-1, y-1, 1, 1);

                            //-----------------------
                            Pane canvas4 = new Pane();
                            cellColor = (Color.values()[boardCells[y][x-1].getColor()]).toString();
                            canvas4.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas4.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas4, x-1, y, 1, 1);

                            boardCells[y][x].setFilled(true);
                            boardCells[y-1][x].setFilled(true);
                            boardCells[y-1][x-1].setFilled(true);
                            boardCells[y][x-1].setFilled(true);

                            blockNumbers[6] --;
                            block6Amount.setText("x" + blockNumbers[6]);
                            success = true;
                        }
                    }

                    if((event.getGestureSource()).equals(block7Grid)){
                        Cell[][] boardCells = myBoard.getBoardCells();
                        if( blockNumbers[7]!=0 && boardCells[y][x].getColor() != 1 && !boardCells[y][x].getFilled()
                                && !boardCells[y-1][x].getFilled()
                                && !boardCells[y+1][x-1].getFilled()
                                && !boardCells[y][x-1].getFilled()
                                && boardCells[y-1][x].getColor() != 1
                                && boardCells[y+1][x-1].getColor() != 1
                                && boardCells[y][x-1].getColor() != 1){

                            //------------------------
                            String cellColor = (Color.values()[boardCells[y][x].getColor()]).toString();
//                            System.out.println(x + cellColor + y);
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        System.out.println((int)source.getHeight()+ " " +source.getWidth() );
//                        boardPane.add(canvas, x, y, (int)(source.getHeight()/19.0), (int)(source.getWidth()/19.0))
                            boardPane.add(canvas, x, y, 1, 1);

                            //-----------------------
                            Pane canvas2 = new Pane();
                            cellColor = (Color.values()[boardCells[y-1][x].getColor()]).toString();
                            canvas2.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas2.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas2, x, y-1, 1, 1);

                            //-----------------------
                            Pane canvas3 = new Pane();
                            cellColor = (Color.values()[boardCells[y+1][x-1].getColor()]).toString();
                            canvas3.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas3.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas3, x-1, y+1, 1, 1);

                            //-----------------------
                            Pane canvas4 = new Pane();
                            cellColor = (Color.values()[boardCells[y][x-1].getColor()]).toString();
                            canvas4.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas4.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas4, x-1, y, 1, 1);

                            boardCells[y][x].setFilled(true);
                            boardCells[y-1][x].setFilled(true);
                            boardCells[y+1][x-1].setFilled(true);
                            boardCells[y][x-1].setFilled(true);

                            blockNumbers[7] --;
                            block7Amount.setText("x" + blockNumbers[7]);
                            success = true;
                        }
                    }

                    if((event.getGestureSource()).equals(block8Grid)){
                        Cell[][] boardCells = myBoard.getBoardCells();
                        if( blockNumbers[8]!=0 && boardCells[y][x].getColor() != 1 && !boardCells[y][x].getFilled()
                                && !boardCells[y-1][x].getFilled()
                                && !boardCells[y+1][x].getFilled()
                                && !boardCells[y][x-1].getFilled()
                                && !boardCells[y][x+1].getFilled()
                                && boardCells[y-1][x].getColor() != 1
                                && boardCells[y+1][x].getColor() != 1
                                && boardCells[y][x-1].getColor() != 1
                                && boardCells[y][x+1].getColor() != 1){

                            //------------------------
                            String cellColor = (Color.values()[boardCells[y][x].getColor()]).toString();
//                            System.out.println(x + cellColor + y);
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        System.out.println((int)source.getHeight()+ " " +source.getWidth() );
//                        boardPane.add(canvas, x, y, (int)(source.getHeight()/19.0), (int)(source.getWidth()/19.0))
                            boardPane.add(canvas, x, y, 1, 1);

                            //-----------------------
                            Pane canvas2 = new Pane();
                            cellColor = (Color.values()[boardCells[y-1][x].getColor()]).toString();
                            canvas2.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas2.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas2, x, y-1, 1, 1);

                            //-----------------------
                            Pane canvas3 = new Pane();
                            cellColor = (Color.values()[boardCells[y+1][x].getColor()]).toString();
                            canvas3.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas3.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas3, x, y+1, 1, 1);

                            //-----------------------
                            Pane canvas4 = new Pane();
                            cellColor = (Color.values()[boardCells[y][x-1].getColor()]).toString();
                            canvas4.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas4.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas4, x-1, y, 1, 1);

                            //-----------------------
                            Pane canvas5 = new Pane();
                            cellColor = (Color.values()[boardCells[y][x+1].getColor()]).toString();
                            canvas5.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas5.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas5, x+1, y, 1, 1);

                            boardCells[y][x].setFilled(true);
                            boardCells[y-1][x].setFilled(true);
                            boardCells[y+1][x].setFilled(true);
                            boardCells[y][x-1].setFilled(true);
                            boardCells[y][x+1].setFilled(true);

                            blockNumbers[8] --;
                            block8Amount.setText("x" + blockNumbers[8]);
                            success = true;
                        }
                    }

                    if((event.getGestureSource()).equals(block9Grid)){
                        Cell[][] boardCells = myBoard.getBoardCells();
                        if( blockNumbers[9]!=0 && boardCells[y][x].getColor() != 1 && !boardCells[y][x].getFilled()
                                && !boardCells[y-1][x].getFilled()
                                && !boardCells[y+1][x].getFilled()
                                && !boardCells[y-1][x-1].getFilled()
                                && !boardCells[y+1][x-1].getFilled()
                                && boardCells[y-1][x].getColor() != 1
                                && boardCells[y+1][x].getColor() != 1
                                && boardCells[y-1][x-1].getColor() != 1
                                && boardCells[y+1][x-1].getColor() != 1){

                            //------------------------
                            String cellColor = (Color.values()[boardCells[y][x].getColor()]).toString();
//                            System.out.println(x + cellColor + y);
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                        System.out.println((int)source.getHeight()+ " " +source.getWidth() );
//                        boardPane.add(canvas, x, y, (int)(source.getHeight()/19.0), (int)(source.getWidth()/19.0))
                            boardPane.add(canvas, x, y, 1, 1);

                            //-----------------------
                            Pane canvas2 = new Pane();
                            cellColor = (Color.values()[boardCells[y-1][x].getColor()]).toString();
                            canvas2.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas2.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas2, x, y-1, 1, 1);

                            //-----------------------
                            Pane canvas3 = new Pane();
                            cellColor = (Color.values()[boardCells[y+1][x].getColor()]).toString();
                            canvas3.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas3.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas3, x, y+1, 1, 1);

                            //-----------------------
                            Pane canvas4 = new Pane();
                            cellColor = (Color.values()[boardCells[y-1][x-1].getColor()]).toString();
                            canvas4.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas4.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas4, x-1, y-1, 1, 1);

                            //-----------------------
                            Pane canvas5 = new Pane();
                            cellColor = (Color.values()[boardCells[y+1][x-1].getColor()]).toString();
                            canvas5.setStyle("-fx-background-color: " + cellColor);
//                        canvas.setShape();
                            canvas5.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            boardPane.add(canvas5, x-1, y+1, 1, 1);

                            boardCells[y][x].setFilled(true);
                            boardCells[y-1][x].setFilled(true);
                            boardCells[y+1][x].setFilled(true);
                            boardCells[y-1][x-1].setFilled(true);
                            boardCells[y+1][x-1].setFilled(true);

                            blockNumbers[9] --;
                            block9Amount.setText("x" + blockNumbers[9]);
                            if(blockNumbers[9]==0){
                                ((GridPane)event.getGestureSource()).setVisible(false);
                            }
                            success = true;
                        }
                    }

                    //target.setText(db.getImage()); --- must be changed to target.add(source, col, row)
                    //target.add(source, 5, 5, 1, 1);
                    //Places at 0,0 - will need to take coordinates once that is implemented

//                    ImageView image = new ImageView(db.getImage());
//                    boardPane.add(image, x, y, 1, 1);

                }
//                Node currentBlockCell = getNodeFromGridPane(source, 0, 0);

//                ArrayList al = new ArrayList();
//                for(int i =0 ; i < 3; i++){
//                    for(int j =0 ; j < 3; i++){
//                        if(currentBlockCell != null){
//                            int[] myIntArray = {i,j};
//                            al.add(myIntArray);
//                        }
//                    }
//                }
//                System.out.println(al.toString());

                //let the source know whether the image was successfully transferred and used

                if (isGameOver()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Game Over");
                    alert.setHeaderText("YOU WON :)");
                    alert.setContentText("Congratulations!");

                    alert.showAndWait();
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        //Drag Done on Source
        source.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.print("DRAGDONE ");
                if(event.getTransferMode() == TransferMode.MOVE){
                    source.setVisible(true);
                }
                event.consume();
            }
        });

        //Drag over event handler is used for the receiving node to allow movement
        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.print("DRAGOVER ");

                //data is dragged over to target
                //accept it only if it is not dragged from the same node
                //and if it has image data
                if(event.getGestureSource() != target){
                    //allow for moving
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });

        //Drag entered changes the appearance of the receiving node to indicate to the player that they can place there
        target.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.print("DRAGENTERED ");

                //The drag-and-drop gesture entered the target
                //show the user that it is an actual gesture target
                if(event.getGestureSource() != target){
//                    source.setVisible(false);
                    target.setOpacity(0.7);
                }
                event.consume();
            }
        });

        //Drag exited reverts the appearance of the receiving node when the mouse is outside of the node
        target.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.print("DRAGEXITED ");

                //mouse moved away, remove graphical cues
//                source.setVisible(true);
                target.setOpacity(1);

                event.consume();
            }
        });
    }

    private void createBoard() throws Exception {
        String dir = System.getProperty(("user.dir")) + "/src/boards/";
        myBoard = new Board(dir + boardName + "/" + boardName);
        Cell[][] boardCells = myBoard.getBoardCells();

        for( int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
//                String cellColor = "-fx-background-color: " +(Color.values()[boardCells[i][j].getColor()]).toString() + ";";
                String cellColor;
                if ((Color.values()[boardCells[i][j].getColor()]).toString().equals("WHITE"))
                    cellColor= "-fx-background-color: WHITE;";
                else
                    cellColor= "-fx-background-color: GRAY;";
                System.out.println(i + cellColor + j);
                Pane canvas = new Pane();
                canvas.setStyle(cellColor);
                canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                boardPane.setConstraints(canvas, j, i);
                boardPane.getChildren().addAll(canvas);
            }
        }
        addEventDetectors(block0Grid, boardPane,"block0");
        addEventDetectors(block1Grid, boardPane,"block1");
        addEventDetectors(block2Grid, boardPane,"block2");
        addEventDetectors(block3Grid, boardPane,"block3");
        addEventDetectors(block4Grid, boardPane,"block4");
        addEventDetectors(block5Grid, boardPane,"block5");
        addEventDetectors(block6Grid, boardPane,"block6");
        addEventDetectors(block7Grid, boardPane,"block7");
        addEventDetectors(block8Grid, boardPane,"block8");
        addEventDetectors(block9Grid, boardPane,"block9");

    }

    private void createBlocks() throws Exception{
        //Get gui grids
        GridPane[] guiBlockGrids = {block0Grid , block1Grid , block2Grid , block3Grid , block4Grid , block5Grid , block6Grid , block7Grid , block8Grid , block9Grid};
        Label[] guiBlockAmounts = {block0Amount , block1Amount , block2Amount , block3Amount , block4Amount , block5Amount , block6Amount , block7Amount , block8Amount , block9Amount};
        //Get blocks
        blockNumbers = myBoard.getBoardBlocks();

        for (int blockIndex=0; blockIndex<blockNumbers.length; blockIndex++)
        {
            if (blockNumbers[blockIndex] != 0){
                Block myBlock = new Block( Block.BlockShape.values()[blockIndex] );
                Cell[][] blockGrid = myBlock.getBlockShape();
                List<ObjectProperty<EventHandler<? super MouseEvent>>> myList = new ArrayList<>();

//                guiBlockGrids[blockIndex].setGridLinesVisible(true);
                for (int i = 0; i < 3 ; i++){
                    for (int j = 0; j < 3 ; j++){
                        if (blockGrid[i][j].getVisible()){
                            System.out.print(blockGrid[i][j].getVisible() + ",");
                            Pane canvas = new Pane();
                            canvas.setStyle("-fx-background-color: RED;");
                            canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            guiBlockAmounts[blockIndex].setText("x" + blockNumbers[blockIndex]);
                            guiBlockGrids[blockIndex].setConstraints(canvas, i,j);
                            guiBlockGrids[blockIndex].getChildren().addAll(canvas);
                        }
                    }
                }
//                for (int i = 0; i < guiBlockGrids[blockIndex].getChildren().size(); i ++){
//                    myList.add(guiBlockGrids[blockIndex].getChildren().get(i).onDragDetectedProperty());
//                }

//                for(int i = 1; i< myList.size(); i++){
//                    myList.get(0).bind(myList.get(i));
//                }
//                System.out.println(Arrays.deepToString(blockGrid));
//                for (Cell[] currentCells : blockGrid)
//                {
//                    for(Cell currentCell : currentCells){
//                        System.out.print(currentCell.getVisible() + ",");
//                    }
//
//                }
                System.out.println();
            }
        }

//        for( int i = 0; i < 20; i++) {
//            for (int j = 0; j < 20; j++) {
////                String cellColor = (Color.values()[boardCells[i][j].getColor()]).toString();
//                String cellColor;
//                if ((Color.values()[boardCells[i][j].getColor()]).toString().equals("WHITE"))
//                    cellColor= "-fx-background-color: WHITE;";
//                else
//                    cellColor= "-fx-background-color: GRAY;";
//                System.out.println(i + cellColor + j);
//                Pane canvas = new Pane();
//                canvas.setStyle(cellColor);
//                canvas.setBorder(new Border(new BorderStroke(javafx.scene.paint.Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//                addEventDetectors(galatasaray, canvas, boardCells[i][j]);
//                boardPane.setConstraints(canvas, j, i);
//                boardPane.getChildren().addAll(canvas);
//            }
//        }


    }

    public boolean isGameOver(){
        Cell[][] allCells =myBoard.getBoardCells();
        for(int i = 0; i < 20; i++){
            for(int j = 0; j <20; j++){
                if (allCells[i][j].getVisible()){
                    if( !allCells[i][j].getFilled())
                        return false;
                }
            }
        }
        return true;
    }

}
