package main.java;
import java.util.HashMap;
import java.util.Random;

public class NumberCryptogram extends Cryptogram{
    private HashMap<Character, Integer> crypto;
    private String Originalphrase;
    private String encryptedPhrase;

    public NumberCryptogram(){
        crypto = new HashMap<>();
        Originalphrase = super.getPhrase().toLowerCase();
    }
    @Override
    public String generateCryptogram() {
        Random rand = new Random();
        for (int i = 0; i < Originalphrase.length(); i++) {
            char characterAtPhrase = Originalphrase.charAt(i);
            if (!(crypto.containsKey(characterAtPhrase)) && characterAtPhrase >= 'a' && characterAtPhrase <= 'z') {
                int encryptNumber = rand.nextInt(25)+1;
                while(crypto.containsValue(encryptNumber)){
                    encryptNumber = rand.nextInt(25)+1;
                }
                crypto.put(characterAtPhrase, encryptNumber);
            }
        }
        return printMethod(Originalphrase);
    }
    @Override
    public String printMethod(String phrase){
        System.out.println();
        String toPrint = "";
        phrase = phrase.toLowerCase();
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                toPrint = toPrint.concat("  ");
            }else if(!(phrase.charAt(i) >= 'a' && phrase.charAt(i)<= 'z')){
                toPrint = toPrint.concat(phrase.charAt(i)+"");
            }
            else {
                if((i+1) >= phrase.length()){
                    toPrint = toPrint.concat(crypto.get(phrase.charAt(i)) + "");
                }
                else {
                    if (!(phrase.charAt(i + 1) >= 'a' && phrase.charAt(i) <= 'z')) {
                        toPrint = toPrint.concat(crypto.get(phrase.charAt(i)) + "");
                    } else {
                        toPrint = toPrint.concat(crypto.get(phrase.charAt(i)) + "-");
                    }
                }
            }
        }
        setEncrypted(toPrint);
        return toPrint;
    }
    @Override
    public String getPhraseForEncryption() {
        return super.getPhraseForEncryption();
    }

    public String getEnryptedPhrase(){
        return encryptedPhrase;
    }
    public void setEncrypted(String encrypted){
        encryptedPhrase = encrypted;
    }
}