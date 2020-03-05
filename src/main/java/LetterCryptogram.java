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
                int shiftNumber = rand.nextInt(25)+1;
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
        printMethod(phrase);
    }

    public void printMethod(String phrase){
        System.out.println();
        String toPrint = "";
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                toPrint = toPrint.concat(" ");
            }else if(!(phrase.charAt(i) >= 'a' && phrase.charAt(i)<= 'z')){
                toPrint = toPrint.concat(phrase.charAt(i)+"");
            }
            else {
                toPrint = toPrint.concat(crypto.get(phrase.charAt(i)) + "");
            }
        }
        System.out.println(toPrint);
    }

}