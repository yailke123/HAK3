import java.util.HashMap;
import java.io.*;
import java.lang.*;

public class Leaderboard {
	public static final int MAX_SIZE = 10;
    private User[] leaderboard;

    public Leaderboard(){
        leaderboard = new User[10];
        for(int i = 0; i< 10; i++){
            leaderboard[i] = new User(null, null);
        }
    }
    public void getLeaderboard()throws IOException{
        String myLine = null;
        FileReader input = new FileReader("leaderboard.txt");
        BufferedReader bufRead = new BufferedReader(input);
        int i = 0;
        while ( (myLine = bufRead.readLine()) != null )
        {
            String[] info = myLine.split("\n");
            myLine = bufRead.readLine();
            String[] info2 = myLine.split("\n");
            leaderboard[i].setUsername(info[0]);
            leaderboard[i].setScore(info2[0]);
            i++;
        }
    }
    public User[] getLeaderboardHash(){
        return leaderboard;
    }
}
