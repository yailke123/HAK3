public class User {
    String username;
    String score;

    public User(String username, String score){
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public String getScore() {
        return score;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setScore(String score){
        this.score = score;
    }
}
