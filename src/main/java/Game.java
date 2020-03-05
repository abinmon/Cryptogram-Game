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
    private Cryptogram currentCryptogram;
    Scanner reader;
    String currentLetter;

    public Game() {
        reader = new Scanner(System.in);
        currentLetter = null;
    }

    public void playGame() {
        String r = "";
        int whatPlace = 0;
        if (currentCryptogram == null)
            currentCryptogram = generateCryptogram1();
        String print = currentCryptogram.generateCryptogram();
        String x = currentCryptogram.getAttempt();

        int choice;
        try {
            while (true) {
                System.out.println();
                System.out.println(print);
                System.out.println();
                printDisplay(currentCryptogram.getWorkingPhrase(), whatPlace);
                System.out.println("Type 1 to enter a letter");
                System.out.println("Type 2 to delete a letter");
                System.out.println("Type 3 for help");
                System.out.println("Type 4 to move forward");
                System.out.println("Type 5 to move backward");
                System.out.print("Which option would you like? ");

                choice = reader.nextInt();
                System.out.println("You have chosen " + choice);
                switch (choice) {
                    case 1:
                        this.enterLetter(whatPlace);
                        break;
                    case 2:
                        undoLetter();
                        break;
                    case 3:
                        help();
                    case 4: // Move the cursor Forward
                        if (whatPlace >= currentCryptogram.getWorkingPhrase().length() - 1) {
                            whatPlace = 0;
                        } else {
                            whatPlace = currentCryptogram.getWorkingPhrase().charAt(whatPlace + 1) == ' ' ?
                                    whatPlace + 2 : whatPlace + 1;
                        }
                        break;
                    case 5: // Move the cursor backwards
                        if (whatPlace == 0) {
                            whatPlace = (currentCryptogram.getWorkingPhrase().length() - 1);
                        } else {
                            whatPlace = currentCryptogram.getWorkingPhrase().charAt(whatPlace - 1) == ' ' ?
                                    whatPlace - 2 : whatPlace - 1;
                        }
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Invalid option. Please try again!");
                        break;
                }


            }
        }
        catch (InputMismatchException e) {
        System.out.println(e);
        }
    }

    private static void printDisplay(String phrase, int whatPlace) {
        System.out.println(phrase);
        for (int i = 0; i < whatPlace; i++) {
            System.out.print(" ");
        }
        System.out.println("^");
    }

    private Cryptogram generateCryptogram1() {
        boolean input_done = false;
        String s = "";
        while(!input_done) {
            System.out.println("Do you want a number or a letter cryptogram. Enter 1 for letter, or 2 for number.");
            int input = reader.nextInt();
            if (input == 1) {
                currentCryptogram = new LetterCryptogram();
                currentCryptogram.setAttempt(currentCryptogram.getPhrase().toLowerCase().replaceAll("[a-z]", "#"));
                input_done = true;
            }
            else if (input == 2) {
                currentCryptogram = new NumberCryptogram();
                currentCryptogram.setAttempt(currentCryptogram.getPhrase().toLowerCase().replaceAll("[a-z]", "#"));
                input_done = true;
            }
            else {
                System.out.println("What you entered was neither 1 or 2, please input a valid answer.");
                return generateCryptogram1();
            }
        }
        return currentCryptogram;
    }

    public void enterLetter(int whatLetter) {
        Scanner input = new Scanner(System.in);
        currentLetter = input.nextLine();
        System.out.println("current letter = " + currentLetter);
        if (currentLetter.length()==1) {
            currentCryptogram.changePhrase(currentLetter, whatLetter);
        }
    }

    public void undoLetter() {

    }


    public void help() {
        System.out.println("Help Section");
            System.out.println("Number description: ");
            System.out.println("Typing 1 - to enter a letter.");
            System.out.println("Typing 2 - to removing a letter.");
            System.out.println("Typing 3 - for help.");
            System.out.println("Typing 4 - to move forward");
            System.out.println("Typing 5 - to move backwards.");
    }
    public static void main(String[] args) {
        boolean exit = false;
        Game newGame = new Game();
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello");
        while(true) {
            newGame.playGame();
        }
    }


}