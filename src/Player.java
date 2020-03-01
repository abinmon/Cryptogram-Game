public class Player{
    private String username;
    private int totalNumGuesses;
    private int numberCompleted;
    private int numberPlayed;
    private double accuracy;
    private int numCorrectGuesses;

    public Player(String name){
        username = name;
        totalNumGuesses = 0;
        numberCompleted = 0;
        numberPlayed = 0;
        numCorrectGuesses = 0;
    }

    public Player(String name, int totalGuesses, int numberC, int numberP, int numCorrectGs){
        username = name;
        totalNumGuesses = totalGuesses;
        numberCompleted = numberC;
        numberPlayed = numberP;
        numCorrectGuesses = numCorrectGs;
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

    public void updateAccuracy(double lastRecAccuracy){
        if(numberPlayed == 1)
        {
            accuracy = lastRecAccuracy;
            return;
        }
        accuracy = (accuracy + lastRecAccuracy) / numberPlayed;
    }
    public double getAccuracy()
    {
        return accuracy;
    }

    public void displayStats(){
        System.out.println("Username: " + getUsername());
        System.out.println("Number of guesses: " + getTotalNumGuesses());
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