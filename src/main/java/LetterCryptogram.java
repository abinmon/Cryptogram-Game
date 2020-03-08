package main.java;
import java.util.HashMap;
import java.util.Random;

public class LetterCryptogram extends Cryptogram{
    private HashMap<Character, Character> crypto;
    private String Originalphrase;
    private String encryptedPhrase;
    private final String BASE_QUOTES_FILE = "src/resources/phrases.txt";



    public LetterCryptogram(){
        super();
        crypto = new HashMap<>();
        Originalphrase = super.getPhraseForEncryption(BASE_QUOTES_FILE).toLowerCase();
    }

    public LetterCryptogram(String fileName){
        crypto = new HashMap<>();
        Originalphrase = super.getPhraseForEncryption(fileName).toLowerCase();
    }


    @Override
    public String generateCryptogram() {
        Random rand = new Random();
        int shiftNumber = rand.nextInt(25)+1;
        for(int i = 0 ; i < Originalphrase.length(); i++){
            char characterAtPhrase = Originalphrase.charAt(i);
            if(!(crypto.containsKey(characterAtPhrase)) && characterAtPhrase >= 'a' && characterAtPhrase <= 'z'){
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
        return printMethod(Originalphrase);
    }

    public String printMethod(String phrase){
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
        setEncrypted(toPrint);
        return toPrint;
    }
    public String getPhraseForEncryption() {
        return super.getPhraseForEncryption(BASE_QUOTES_FILE);
    }

    public String getEnryptedPhrase(){
        return encryptedPhrase;
    }
    public void setEncrypted(String encrypted){
        encryptedPhrase = encrypted;
    }

    public HashMap<Character, Character> getCrypto() {
        return crypto;
    }
}