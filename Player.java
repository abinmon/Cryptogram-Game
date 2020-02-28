public class Player{
    private String username;
    private int score;
    private int totalNumGuesses;
    private int numberCompleted;
    private int numberPlayed;
    private int numCorrectGuesses;

    public Player(String name){
        username = name;
    }

    public String getUsername(){return username;}
    public void setUsername(String name) {username = name;}
    public int getNumberCompleted(){return numberCompleted;}
    public void incrementNumberCompleted(){numberCompleted++;}
    public int getNumberPlayed(){return numberPlayed;}
    public void incrementNumberPlayed(){numberPlayed++;}
    public void incrementTotalGuesses(){totalNumGuesses++;}
    public int getNumberOfCorrectGuesses(){return numCorrectGuesses;}
    public void incrementNumberOfCorrectGuesses(){numCorrectGuesses++;}

    public double getAccuracy(){
        return ((numCorrectGuesses / totalNumGuesses) * 100);
    }

    public void displayStats(){
        System.out.println("Username: " + username);
        System.out.println("Score: " + score);
        System.out.println("Number of guesses: " + totalNumGuesses);
        System.out.println("Number of correct guesses: " + numCorrectGuesses);
        System.out.println("Number of games played: " + numberPlayed);
        System.out.println("Number of games completed: " + numberCompleted);
    }

}