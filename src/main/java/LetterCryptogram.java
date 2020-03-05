package main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LetterCryptogram extends Cryptogram{
    private HashMap<Character, Character> crypto= new HashMap<>();
    public LetterCryptogram(){
        genarateCryptogram();
    }
    private void genarateCryptogram() {
        String phrase = super.getPhrase().toLowerCase();
        StringBuilder build =  new StringBuilder();
        Random rand = new Random();
        for(int i = 0 ; i < phrase.length(); i++){
            char characterAtPhrase = phrase.charAt(i);
            if(!(crypto.containsKey(characterAtPhrase)) && characterAtPhrase >= 'a' && characterAtPhrase <= 'z'){
                int shiftNumber = rand.nextInt(26);
                char encryptedLetter = (char) (characterAtPhrase + shiftNumber);
                if (encryptedLetter > 'z'){
                    encryptedLetter = (char) (characterAtPhrase - (26 - shiftNumber));
                    crypto.put(characterAtPhrase, encryptedLetter);
                }
                else{
                    crypto.put(characterAtPhrase, encryptedLetter);
                }
            }
        }

        for (Map.Entry<Character, Character> entry : crypto.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

}