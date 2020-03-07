package main.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CryptogramTest {

    @Before
    public void setUp() {
        Cryptogram crypto = new Cryptogram();
    }

    @Test
    void generateCryptogram() {
        //Tests to see if the program will stop if the phrases file does not exits
        assertnull(crypto.getPhraseForEncryption());
    }

    @Test
    void getPhraseForEncryptionTest(){
        //Tests to see if a random phrase is chosen when the phrases file isnt empty
        assertNotNull(crypto.getPhraseForEncryption());
    }

    @Test
    void changePhraseTest(){
        String phrase = crypto.getPhraseForEncryption();


    }


}