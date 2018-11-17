
import java.io.*;
import java.lang.*;

public class Board {
    public enum Color{
        BLACK, WHITE, PINK, GRAY, GREEN, RED, YELLOW, BLUE, PURPLE, ORANGE
    }
    private int level;
    private Cell[][] board_2D;
    private String textDir;
    private int[] blockNum;

    public Board(String textDir){
        board_2D = new Cell[20][20];
        for( int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                board_2D[i][j] = new Cell();
            }
        }
        blockNum = new int[10];
        this.textDir = textDir;
    }
    public void getBoardInfo() throws IOException{

        int index = 0;
        FileReader input = new FileReader(textDir+".txt");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;
        boolean isVisible;
        while ( (myLine = bufRead.readLine()) != null)
        {
            String[] info = myLine.split("\n");
            int temp = info[0].charAt(0)-'0';

            if( temp == 0)
                isVisible = false;
            else
                isVisible = true;
            int color = info[0].charAt(1)-'0';


            board_2D[index / 20][index % 20].setVisible( isVisible );

            board_2D[index / 20][index % 20].setColor( color );

            index++;
        }
        index = 0;
        FileReader input2 = new FileReader(textDir+"blocks.txt");
        BufferedReader bufRead2 = new BufferedReader(input);
        String myLine2 = null;
        while((myLine2 = bufRead.readLine()) != null ){
            String[] info = myLine2.split("\n");
            int temp = info[0].charAt(0)-'0';
            blockNum[index] = temp;
            index++;
        }
    }
    public Cell[][] getBoardCells(){
        return board_2D;
    }


}
