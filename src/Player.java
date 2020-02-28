public class Player{
    private String username;
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
    public int getNumberPlayed(){return numberPlayed;}
    public void incrementNumberPlayed(){numberPlayed++;}
    public void incrementTotalGuesses(){totalNumGuesses++;}
    public int getTotalNumGuesses(){return totalNumGuesses;}
    public int getNumberOfCorrectGuesses(){return numCorrectGuesses;}

    public void incrementNumberCompleted(){
        numberCompleted++;
        numberPlayed++;
    }
    public void incrementNumberOfCorrectGuesses(){
        numCorrectGuesses++;
        totalNumGuesses++;
    }

    public double getAccuracy(){
        if(totalNumGuesses == 0){
            return 0;
        }
        return ((numCorrectGuesses / totalNumGuesses) * 100);
    }

    public void displayStats(){
        System.out.println("Username: " + getUsername());
        System.out.println("Number of guesses: " + getTotalNumGuesses());
        System.out.println("Number of correct guesses: " + getNumberOfCorrectGuesses());
        System.out.println("Accuracy: " + getAccuracy());
        System.out.println("Number of games played: " + getNumberPlayed());
        System.out.println("Score : " + getNumberCompleted());
    }

    public static void main(String[] args) {
        Player p = new Player("Big John");
        p.incrementNumberCompleted();
        p.incrementNumberOfCorrectGuesses();
        p.displayStats();
    }
}