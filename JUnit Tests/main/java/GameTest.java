package main.java;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void enterLetter(){
        Game TestGame= new Game();
        boolean check = true ;
        assertTrue(check=TestGame.helpCheck(0));
    }

    @Test
    void undoLetter() {
    }
    }

