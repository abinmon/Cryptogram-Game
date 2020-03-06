package main.java;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private Cryptogram currentCryptogram;
    private Scanner input;
    private String currentLetter;

    private Game() {
        input = new Scanner(System.in);
        currentLetter = null;
    }

    private void playGame() {
        int whatPlace = 0;
        if (currentCryptogram == null) {
            currentCryptogram = generateCryptogram1();
        }
        String printCrypto = currentCryptogram.generateCryptogram();
        int choice;
        try {
            while (true) {
                if(!currentCryptogram.getWorkingPhrase().contains("#")){
                    if(currentCryptogram.getWorkingPhrase().equals(currentCryptogram.getPhrase())){
                        System.out.println();
                        System.out.println("Well done");
                        break;
                    }
                    else{
                        System.out.println("You got the phrase wrong.");
                    }
                }
                System.out.println();
                System.out.println(printCrypto);
                System.out.println();
                printDisplay(currentCryptogram.getWorkingPhrase(), whatPlace);
                help();
                choice = input.nextInt();
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
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please try again!");
                        break;
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
        while(!input_done) {
            System.out.println("Do you want a number or a letter cryptogram. Enter 1 for letter, or 2 for number.");
            int userInput = input.nextInt();
            if (userInput == 1) {
                currentCryptogram = new LetterCryptogram();
                currentCryptogram.setAttempt(currentCryptogram.getPhrase().toLowerCase().replaceAll("[a-z]", "#"));
                input_done = true;
            }
            else if (userInput == 2) {
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

    private void enterLetter(int whatLetter) {
        Scanner reader = new Scanner(System.in);
        currentLetter = reader.nextLine().toLowerCase();
        System.out.println("current letter = " + currentLetter);
        if (currentLetter.length()==1) {
            currentCryptogram.changePhrase(currentLetter, whatLetter);
        }
        else{
            System.out.println("You did not enter a single letter.");
        }
    }

    private void undoLetter(int whatLetter) {
        System.out.println("Deleting letter: " + whatLetter);
        currentCryptogram.changePhrase("#", whatLetter);
    }


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

    public static void main(String[] args) {
        Game newGame = new Game();
        System.out.println("Hello");
        while(true) {
            newGame.playGame();
        }
    }
}