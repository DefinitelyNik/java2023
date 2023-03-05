package education;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void mainTest() {
    }

    @Test
    void getHappyMoodTest() {
        String expected = "Student is happy";
        String actual = Main.Student.getMood(new int[] {5, 5, 4, 5, 3});
        assertEquals(expected, actual);
    }

    @Test
    void getOkMoodTest() {
        String expected = "Student is ok";
        String actual = Main.Student.getMood(new int[] {3, 3, 3, 3});
        assertEquals(expected, actual);
    }

    @Test
    void getSadMoodTest() {
        String expected = "Student is sad";
        String actual = Main.Student.getMood(new int[] {2, 2, 2, 2});
        assertEquals(expected, actual);
    }

    @Test
    void getEmptyMoodTest() {
        String expected = "Student is fine(no marks found)";
        String actual = Main.Student.getMood(new int[] {});
        assertEquals(expected, actual);
    }

    @Test
    void zeroFactTest() {
        long expected = 1;
        long actual = Main.Mathematics.fact(0);
        assertEquals(expected, actual);
    }

    @Test
    void oneFactTest() {
        long expected = 1;
        long actual = Main.Mathematics.fact(1);
        assertEquals(expected, actual);
    }

    @Test
    void negativeFactTest() {
        long expected = -1;
        long actual = Main.Mathematics.fact(-5);
        assertEquals(expected, actual);
    }

    @Test
    void positiveSignTest() {
        long expected = 1;
        long actual = Main.Mathematics.sign(100);
        assertEquals(expected, actual);
    }

    @Test
    void negativeSignTest() {
        long expected = -1;
        long actual = Main.Mathematics.sign(-100);
        assertEquals(expected, actual);
    }

    @Test
    void zeroSignTest() {
        long expected = 0;
        long actual = Main.Mathematics.sign(0);
        assertEquals(expected, actual);
    }

    @Test
    void intSignTest() {
        long expected = 1;
        long actual = Main.Mathematics.sign(34);
        assertEquals(expected, actual);
    }

    @Test
    void doubleSignTest() {
        long expected = -1;
        long actual = Main.Mathematics.sign(-13.23);
        assertEquals(expected, actual);
    }
}