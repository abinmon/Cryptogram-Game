package main.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Cryptogram {

    private String phrase;
    private HashMap<?,?> symbolMappings;
    private HashMap<Character, String> progressMap;
    private HashMap<Character, Integer> frequencies;

    public Cryptogram(String encryptedPhrase,HashMap<?,?> mappings, HashMap<Character, String> progress) {
        phrase = encryptedPhrase;
        symbolMappings = mappings;
        progressMap = progress;
    }

    public HashMap<Character, Integer> getFrequencies() { //method to do- get each character and their frequencies
        return frequencies;
    }

    public char getLetter(int i) {
        return phrase.charAt(i);
    }

    public HashMap<Character, String> getProgressMap() {
        return progressMap;
    }
    public String getPhrase()
    {
        return phrase;
    }
    public String getEncryptedPhrase() {//method to do- Returns the encrypted phrase.
        return "";
    }
    public abstract void updateProgress(String currentLetter, String currentNumber);
    public abstract void resetProgress();
    public abstract void undo(char c);
    public abstract void getOneHint();
}