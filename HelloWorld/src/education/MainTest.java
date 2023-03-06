package education;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Student student = new Student(
            "Непочатый Никита Владимирович",
            21,
            new int[] {5, 5, 5},
            new String[]{""});

    @Test
    void getHappyMoodTest() {
        String expected = "Student is happy";
        String actual = Student.getMood(new int[] {5, 5, 4, 5, 3});
        assertEquals(expected, actual);
    }

    @Test
    void getOkMoodTest() {
        String expected = "Student is ok";
        String actual = Student.getMood(new int[] {3, 3, 3, 3});
        assertEquals(expected, actual);
    }

    @Test
    void getSadMoodTest() {
        String expected = "Student is sad";
        String actual = Student.getMood(new int[] {2, 2, 2, 2});
        assertEquals(expected, actual);
    }

    @Test
    void getEmptyMoodTest() {
        String expected = "Student is fine(no marks found)";
        String actual = Student.getMood(new int[] {});
        assertEquals(expected, actual);
    }

    @Test
    void emptySubjectTaskTest() {
        double expected = 2.4;
        double actual = Task.taskDifficulty("", 2, student, "выиграть игру в CS:GO");
        assertEquals(expected, actual);
    }

    @Test
    void nonExistingSubjectTaskTest() {
        double expected = 2.4;
        double actual = Task.taskDifficulty("wtf", 2, student, "выиграть игру в Dota 2");
        assertEquals(expected, actual);
    }

    @Test
    void positiveDeadlineTaskTest() {
        double expected = 3.0;
        double actual = Task.taskDifficulty("IT", 8, student, "выиграть игру в LoL");
        assertEquals(expected, actual);
    }

    @Test
    void zeroDeadlineTaskTest() {
        double expected = 18.0;
        double actual = Task.taskDifficulty("IT", 0, student, "выиграть игру в PUBG");
        assertEquals(expected, actual);
    }

    @Test
    void negativeDeadlineTaskTest() {
        double expected = 18.0;
        double actual = Task.taskDifficulty("IT", -15, student, "выиграть игру в Valorant");
        assertEquals(expected, actual);
    }

    @Test
    void emptyMarksTaskTest() {
        Student anotherStudent = new Student(
                "Акакиев Акакий Акакиевич",
                21,
                new int[] {},
                new String[]{""});
        double expected = 9.0;
        double actual = Task.taskDifficulty("IT", 7, anotherStudent, "выиграть игру в Brawl Stars");
        assertEquals(expected, actual);
    }

    @Test
    void emptySubtaskTaskTest() {
        double expected = 6.0;
        double actual = Task.taskDifficulty("IT", -15, student, "");
        assertEquals(expected, actual);
    }

    @Test
    void deservedMarkTest() {
        String expected = "Deserved :)";
        String actual = Mark.isDeserved(5, student);
        assertEquals(expected, actual);
    }

    @Test
    void unDeservedMarkTest() {
        String expected = "Undeserved :(";
        String actual = Mark.isDeserved(2, student);
        assertEquals(expected, actual);
    }

    @Test
    void maybeDeservedMarkTest() {
        Student otherStudent = new Student(
                "Непочатый Никита Владимирович",
                21,
                new int[] {},
                new String[]{""});
        String expected = "We cannot tell whether the mark is deserved or not";
        String actual = Mark.isDeserved(2, otherStudent);
        assertEquals(expected, actual);
    }

    @Test
    void zeroFactTest() {
        long expected = 1;
        long actual = Math.fact(0);
        assertEquals(expected, actual);
    }

    @Test
    void oneFactTest() {
        long expected = 1;
        long actual = Math.fact(1);
        assertEquals(expected, actual);
    }

    @Test
    void negativeFactTest() {
        long expected = -1;
        long actual = Math.fact(-5);
        assertEquals(expected, actual);
    }

    @Test
    void positiveSignTest() {
        long expected = 1;
        long actual = Math.sign(100);
        assertEquals(expected, actual);
    }

    @Test
    void negativeSignTest() {
        long expected = -1;
        long actual = Math.sign(-100);
        assertEquals(expected, actual);
    }

    @Test
    void zeroSignTest() {
        long expected = 0;
        long actual = Math.sign(0);
        assertEquals(expected, actual);
    }

    @Test
    void intSignTest() {
        long expected = 1;
        long actual = Math.sign(34);
        assertEquals(expected, actual);
    }

    @Test
    void doubleSignTest() {
        long expected = -1;
        long actual = Math.sign(-13.23);
        assertEquals(expected, actual);
    }
}