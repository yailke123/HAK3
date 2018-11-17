import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private Board gameBoard;
    private Timer gameTime;
    private String username;
    private Leaderboard leaderboard;
    private ArrayList<String> boardlist =  new ArrayList<>();
    public Game(){
        gameBoard = null;
        gameTime = null;
        username = null;
        leaderboard = new Leaderboard();
    }
    public void getBoardList(){
        File folder = new File("boards");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                boardlist.add(listOfFiles[i].getName());
            }
        }
    }
    public void setBoard( String dir) throws IOException{
        gameBoard = new Board(dir);
        gameBoard.getBoardInfo();
    }
    public boolean isGameOver(){
        Cell[][] allCells =gameBoard.getBoardCells();
        for(int i = 0; i < 20; i++){
            for(int j = 0; j <20; j++){
                if (allCells[i][j].getVisible() && !allCells[i][j].getFilled()) {
                    return false;
                }
            }
        }
        return true;
    }
    public void updateLeaderboard( String time) throws IOException{


    }





}
