package main.java;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CryptogramTest {

    @Test
    void generateCryptogram() {
        Cryptogram crypto = new LetterCryptogram();
        //Tests to see if the program will stop if the phrases file does not exist
        assertNotNull(crypto.getPhraseForEncryption("src/resources/testPhraseFile.txt"));

        //The file only contains one sentence , so this test checks if the phrase that is returned from the getPhraseForEncryption is the same as the test file.
        assertEquals(crypto.getPhrase(), "test file with words");


    }


    @Test
    void changePhraseTest(){
        Cryptogram crypto = new LetterCryptogram("src/resources/testPhraseFile.txt");
        //This test is to see if the change Phrase method works... this is done by having getting the current phrase and chaning the second character to a and seeing if the character after at position 2 is the same as the changed letter.
        crypto.setAttempt(crypto.getPhrase());
        crypto.changePhrase("a", 2);

        assertEquals(String.valueOf(crypto.getAttempt().charAt(2)),"a");


    }


}