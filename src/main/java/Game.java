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
        if (currentCryptogram == null)
            currentCryptogram = generateCryptogram();
        int choice;
        while (true) {

            System.out.println();
            System.out.println("Type 1 to enter a letter");
            System.out.println("Type 2 to delete a letter");
            System.out.println("Type 3 for help");
            System.out.print("Which option would you like? ");
            choice = reader.nextInt();
            System.out.println("You have chosen "+ choice);
            switch (choice) {
                case 1:
                    this.enterLetter();
                case 2:
                    undoLetter();
                case 3:
                    help();
                default:
                    System.out.println("Invalid option. Please try again!");

            }
        }
    }

    private Cryptogram generateCryptogram() { // method to do- shoudl give users 2 options 1 for letter and 2 for numbers based on the option generated the corresponding cryptogram.
        boolean input_done = false;
        while(input_done == false) {
            System.out.println("Do you want a number or a letter cryptogram. Enter 1 for letter, or 2 for number.");
            int input = reader.nextInt();
            if (input == 1) {
                currentCryptogram = new LetterCryptogram();
                input_done = true;
            }
            else if (input == 2) {
                currentCryptogram = new NumberCryptogram();
                input_done = true;
            }
            else {
                System.out.println("What you entered was neither 1 or 2, please input a valid answer.");
                return generateCryptogram();
            }
        }
        return currentCryptogram;
    }

    public void enterLetter() {
    playGame();
    }

    public void undoLetter() {
playGame();
    }


    public void help() {
        System.out.println("Help Section");
            System.out.println("Number description: ");
            System.out.println("Typing 1 - to enter a letter.");
            System.out.println("Typing 2 - to removing a letter.");
            return;
    }
    public static void main(String[] args) {
        boolean exit = false;
        int choice = 0;
        Game newGame = new Game();
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello");
        while(!exit) {
                    newGame.playGame();
        }
    }
}