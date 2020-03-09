package main.java;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private Cryptogram currentCryptogram;
    private Scanner input;
    private String currentLetter;
    private ArrayList<String> alreadyInput;
    private Player p;
    private final String BASE_QUOTES_FILE = "src/resources/phrases.txt";


    public Game() {
        input = new Scanner(System.in);
        currentLetter = null;
        alreadyInput = new ArrayList<>();
        p = new Player("Player1");
    }

    public Game(String playerName , String fileName){
        input = new Scanner(System.in);
        currentLetter = null;
        alreadyInput = new ArrayList<>();
        p = new Player(playerName);
    }

    public Game(Player player){
        input = new Scanner(System.in);
        currentLetter = null;
        alreadyInput = new ArrayList<>();
        p = player;
    }

    public void playGame(int type) {
        int whatPlace = 0;
        if (currentCryptogram == null)
            currentCryptogram = cryptogramChoice(type, BASE_QUOTES_FILE);
        String printCrypto = currentCryptogram.generateCryptogram();
        int choice;
        while (true) {
            try {
                if(!currentCryptogram.getWorkingPhrase().contains("#")){
                    if(currentCryptogram.getWorkingPhrase().equals(currentCryptogram.getPhrase())){
                        System.out.println();
                        System.out.println("Well done");
                        p.incrementNumberCompleted();
                        break;
                    }
                    else{
                        System.out.println("You got the phrase wrong.");
                    }
                }
                System.out.println(printCrypto);
                System.out.println("=======================");
                printDisplay(currentCryptogram.getWorkingPhrase(), whatPlace);
                help();
                Scanner reader = new Scanner(System.in);
                choice = reader.nextInt();
                System.out.println("You have chosen " + choice);

                switch (choice) {
                    case 1:
                        this.enterLetter(whatPlace);
                        break;
                    case 2:
                        undoLetter(whatPlace);
                        break;
                    case 3:
                        help();
                    case 4: // Move the cursor Forward
                        whatPlace = moveCursorForward(whatPlace);
                        break;
                    case 5: // Move the cursor backwards
                       whatPlace = moveCursorBackward(whatPlace);
                        break;
                    case 6:
                        System.out.println("Thanks for playing! GoodBye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please try again!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("=================Invalid option. Please try again!==============================");
            }

        }
        System.out.println();
        System.out.println("Would you like to play another game. Enter 1 for yes, anything else for no.");
        Scanner reader = new Scanner(System.in);
        if(reader.nextInt() != 1){
            System.exit(0);
        }
        currentCryptogram = null;
    }

    public static void printDisplay(String phrase, int whatPlace) {
        System.out.println(phrase);
        for (int i = 0; i < whatPlace; i++) {
            System.out.print(" ");
        }
        System.out.println("^");
    }

    public int moveCursorForward(int whatPlace){
        int place;
        if (whatPlace >= currentCryptogram.getWorkingPhrase().length() - 1) {
            place = 0;
        }
        else {
            if(currentCryptogram.getWorkingPhrase().charAt(whatPlace + 1) == ' '){
                place = whatPlace + 2;
            }
            else{
                place = whatPlace + 1;
            }
        }
        return place;
    }

    public int moveCursorBackward(int whatPlace){
        int place;
        if (whatPlace == 0) {
            place = (currentCryptogram.getWorkingPhrase().length() - 1);
        }
        else {
            if(currentCryptogram.getWorkingPhrase().charAt(whatPlace - 1) == ' '){
                place = whatPlace - 2;
            }
            else{
                place = whatPlace - 1;
            }
        }
        return place;
    }

    // Gives the user the choice between either a number or a letter cryptogram
    public Cryptogram cryptogramChoice(int userInput, String fileName) {
        boolean input_done = false;
        while(!input_done) {
            try {
                if (userInput == 1) {
                    currentCryptogram = new LetterCryptogram(fileName);
                    currentCryptogram.setAttempt(currentCryptogram.getPhrase().toLowerCase().replaceAll("[a-z]", "#"));
                    input_done = true;
                } else if (userInput == 2) {
                    currentCryptogram = new NumberCryptogram(fileName);
                    currentCryptogram.setAttempt(currentCryptogram.getPhrase().toLowerCase().replaceAll("[a-z]", "#"));
                    input_done = true;
                } else {
                    System.out.println("What you entered was neither 1 or 2, please input a valid answer.");
                    return cryptogramChoice(userInput,fileName);
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("=================Invalid option. Please try again!==============================");
                break;

            }
        }
        return currentCryptogram;
    }

    //Lets the user choose which letter in the cryptogram they would like to overwrite
    public void enterLetter(int whatLetter) {
        if(((currentCryptogram.getWorkingPhrase().charAt(whatLetter) >'z') || (currentCryptogram.getWorkingPhrase().charAt(whatLetter) <'a'))&&(!(currentCryptogram.getWorkingPhrase().charAt(whatLetter) == '#'))){
            System.out.println("------------------");
            System.out.println("Keep moving along....Nothing to see here");
            System.out.println();
        }
        else {
            boolean check = helpCheck(whatLetter);
            if (check) {
                System.out.println("What would you like to overwrite with?");
                enterLetterHelper(whatLetter);
                checkCorrect(whatLetter);
            } else if ((currentCryptogram.getWorkingPhrase().charAt(whatLetter) == '#')) {
                System.out.println("What letter would you like to enter?");
                enterLetterHelper(whatLetter);
                checkCorrect(whatLetter);
            }
        }
    }
    public void checkCorrect(int whatLetter){
        if((currentCryptogram.getWorkingPhrase().charAt(whatLetter))== currentCryptogram.getPhrase().charAt(whatLetter)){
            p.incrementNumberOfCorrectGuesses();
        }
        else if((currentCryptogram.getWorkingPhrase().contains(currentLetter))){
            p.incrementTotalGuesses();
        }
    }
    //Overwrites the chosen letter with a new letter chosen by the user
    public void enterLetterHelper(int whatLetter){
        Scanner reader = new Scanner(System.in);
        currentLetter = reader.next().toLowerCase();
        char c = currentLetter.charAt(0);
        //Entered letter is c
        System.out.println("current letter = " + currentLetter);
        if (currentCryptogram.getCrypto().containsKey(c)) {
            if (currentLetter.length() == 1) {
                if (!letterCheck()) {
                    checkCorrect(whatLetter);
                    alreadyInput.add(currentLetter);
                    currentCryptogram.changePhrase(currentLetter, whatLetter);
                }
            } else {
                System.out.println("You did not enter a single letter.");
            }
        }
        else {
            System.out.println();
            System.out.println("That letter is not in the phrase! Try Again!");
            System.out.println();
        }
    }

    //Removes a chosen already guessed letter and replaced it with a #
    public void undoLetter(int whatLetter) {
        currentCryptogram.changePhrase("#", whatLetter);
        alreadyInput.remove(currentLetter);
    }
    //
    public Boolean helpCheck(int whatLetter){
        boolean check = false;
        try {
            if (!(currentCryptogram.getWorkingPhrase().charAt(whatLetter) == '#')) {
                System.out.println("Would you like to overwrite? type 1 for yes or 2 for no");
                Scanner i = new Scanner(System.in);
                int a = Integer.parseInt(i.nextLine());
                if (a == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }

        }
        catch (NumberFormatException e){
            System.out.println("Enter 1 or 2 please");
            return helpCheck(whatLetter);
        }
        return check;
    }

    //Gives the user help by showing them the options they have
    private void help() {
        System.out.println("Help Section");
        System.out.println("Number description: ");
        System.out.println("Typing 1 - to enter a letter.");
        System.out.println("Typing 2 - to removing a letter.");
        System.out.println("Typing 4 - to move forward");
        System.out.println("Typing 5 - to move backwards.");
        System.out.println("Typing 6 - to quit.");
        System.out.println("Which option would you like.");
    }

    //Used to check if the user has already entered a specific letter so that they can't enter that letter again
    public boolean letterCheck(){
        boolean alreadyEntered = false;
        for(int i = 0; i < alreadyInput.size(); i++){
            if(alreadyInput.get(i).contains(currentLetter)){
                alreadyEntered = true;
                System.out.println("You have already entered this letter, please try again");
            }
            else{
                alreadyEntered = false;
            }
        }
        return alreadyEntered;
    }

    public ArrayList<String> getAlreadyInput() {
        return alreadyInput;
    }

    public Cryptogram getCurrentCryptogram(){
        return currentCryptogram;
    }

    public Player getP() {
        return p;
    }

    //runs the Game
    public static void main(String[] args) {
        Game newGame = new Game("John", "src/resources/phrases.txt" );
        System.out.println("Hello");
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        int i;

            while (!exit) {
                System.out.println("Do you want a number or a letter cryptogram. Enter 1 for letter, or 2 for number.");
                if (input.hasNextInt()) {
                    i = input.nextInt();
                    if (i == 1 || i == 2) {
                        newGame.playGame(i);
                    }
                }
                else {
                    input.next();
                }
            }



    }
}