package main.java;
import java.io.*;
import java.util.Random;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Timer;

public class Game {
    private HashMap<Player, Cryptogram> playerGameMapping;
    private Cryptogram currentCryptogram;
    private Player currentPlayer;
    Scanner reader;
    String currentPlayerName;
    String currentLetter;

    public Game() {
        playerGameMapping = new HashMap<Player, Cryptogram>();
        currentPlayer = new Player("John");
        reader = new Scanner(System.in);
        currentPlayerName = null;
        currentLetter = null;
        // currentCryptogram = new Cryptogram();
    }

    public void playGame() {
        if (currentCryptogram == null)
            currentCryptogram = generateCryptogram();
        int choice;
        while (true) {
            System.out.println("Type 1 to enter a letter");
            System.out.println("Type 2 to delete a letter");
            System.out.println("Type 3 for help");
            System.out.print("Which option would you like? ");
            choice = reader.nextInt();
            System.out.println("You have chosen "+ choice);
            switch (choice) {
                case 1:
                    this.enterLetter();
                    break;
                case 2:
                    undoLetter();
                    break;
                case 3:
                    help();
                default:
                    System.out.println("Invalid option. Please try again!");
                    break;
            }
        }
    }

    private Cryptogram generateCryptogram() { // method to do- shoudl give users 2 options 1 for letter and 2 for numbers based on the option generated the corresponding cryptogram.

            return generateCryptogram();

    }

    public void enterLetter() {
        try {
            System.out.println("Enter a number in the cryptogram");
            String currentNumber = reader.next();
            System.out.println("Enter a letter: ");
            currentLetter = reader.next();
            if (currentCryptogram.getPhrase().contains(currentLetter)) {
                // find index of all of these letters in the cryptogram and
                // replace the instances of it
                currentCryptogram.updateProgress(currentLetter, currentNumber);
            } else {
                System.out.println("This letter is not present!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        }

    }

    public void undoLetter() {
        System.out.println("Which letter would you like to Undo: ");
        currentLetter = reader.next();
        currentCryptogram.undo(currentLetter.charAt(0));
    }

    public void viewFrequencies() {
        System.out.println("Frequencies: " + currentCryptogram.getFrequencies());
    }

    public void help() {
        System.out.println("Help Section");
            System.out.println("Number description: ");
            System.out.println("Typing 1 - to enter a letter.");
            System.out.println("Typing 2 - to removing a letter.");
    }
    public static void main(String[] args) {
        boolean exit = false;
        int choice = 0;
        Game newGame = new Game();
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello");
        while(!exit) {
            System.out.println("MAIN MENU");
            System.out.println("Please choose an option: ");
            System.out.println("Type 1 to start a game");
            System.out.println("Type 2 to exit");
            System.out.println("Type 3 for help");
            choice = reader.nextInt();
            switch (choice) {
                case 1:
                    newGame.playGame();
                    break;
                case 2:
                    System.exit(0);
                case 3:
                    newGame.help();
                default:
                    System.out.println("Option " + choice + " is invalid. Please try again!");
                    break;
            }
        }
    }
}