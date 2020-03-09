package main.java;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.*;

class GameTest {


    @Test
    public void enterLetter() {
        Game TestGame = new Game();
        boolean check = true;
        assertTrue(check = TestGame.helpCheck(0));
    }

    @Test
    public void undoLetter() {
        Game TestGame = new Game();
        assertNotEquals();
        TestGame.undoLetter();
        assertEquals();
    }



}


