import java.util.HashMap;
import java.io.*;
import java.lang.*;

public class Leaderboard {
    public static final int MAX_SIZE = 10;
    private User[] leaderboard;

    public Leaderboard(){
        leaderboard = new User[MAX_SIZE];
        for(int i = 0; i< 10; i++){
            leaderboard[i] = new User(null, -1);
        }
    }
    public void getLeaderboard(int level)throws IOException{
        String myLine = null;
        String dir = System.getProperty("user.dir");
        FileReader input = new FileReader(dir + "/src/leaderboards/leaderboard" + level + ".txt");
        BufferedReader bufRead = new BufferedReader(input);
        int i = 0;
        while ( (myLine = bufRead.readLine()) != null )
        {
            String[] info = myLine.split("\n");
            myLine = bufRead.readLine();
            String[] info2 = myLine.split("\n");
            int score = Integer.parseInt(info2[0]);
            leaderboard[i].setUsername(info[0]);
            leaderboard[i].setScore(score);
            System.out.println(leaderboard[i].getUsername() + " " + leaderboard[i].getScore());
            i++;
        }
    }
    public User[] getLeaderboardArray(){
        return leaderboard;
    }

}