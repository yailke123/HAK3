import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Board board = new Board("kalp");
        board.getBoardInfo();
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.getLeaderboard();
        //System.out.println(0 % 20);
        //System.out.println(0 / 20);
    }
}
