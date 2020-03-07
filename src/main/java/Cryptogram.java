package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public abstract class Cryptogram {

    private String phrase;
    private  String attempt;

    public Cryptogram() {
        this.phrase = getPhraseForEncryption();
        attempt = "";
    }


    public String getPhraseForEncryption() {
        ArrayList<String> numberOfStrings = new ArrayList<>();
        Random rand = new Random();
        try {
            File myObj = new File("src/resources/phrases.txt");
            if(myObj.length()==0){
                System.out.println("No phrases");
                System.exit(0);
            }
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                numberOfStrings.add(myReader.nextLine());
            }
            int size = rand.nextInt(numberOfStrings.size());
            phrase = numberOfStrings.get(size);
        } catch (FileNotFoundException e) {
            System.out.println("File Not found!");
        }
        return phrase;
    }

    public String getPhrase(){
        return phrase;
    }

    public void changePhrase(String input, int whatPlace) {
        String change = "";
        for (int i = 0; i < this.getPhrase().length(); i++) {
            if (this.getPhrase().charAt(i) == this.getPhrase().charAt(whatPlace)) {
                change = change.concat(input);
            } else {
                change = change.concat(getWorkingPhrase().charAt(i) + "");
            }
        }
        this.setAttempt(change);
    }

    public String getWorkingPhrase() {
        return this.getAttempt();
    }

    public String getAttempt() {
        return attempt;
    }
    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }

    public abstract String generateCryptogram();
    public abstract String printMethod(String phrase);
    public abstract HashMap<?,?> getCrypto();
}