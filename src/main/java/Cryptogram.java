package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public abstract class Cryptogram {

    private String phrase;
    private HashMap<Character, String> progressMap;

    public Cryptogram(String encryptedPhrase, HashMap<Character, String> progress) {
        phrase = encryptedPhrase;
        progressMap = progress;
    }

    public Cryptogram() {

    }

    public char getLetter(int i) {
        return phrase.charAt(i);
    }

    public HashMap<Character, String> getProgressMap() {
        return progressMap;
    }

    public String getPhrase() {
        ArrayList<String> numberOfStrings = new ArrayList<>();
        Random rand = new Random();
        try {
            File myObj = new File("src/resources/phrases.txt");
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

}