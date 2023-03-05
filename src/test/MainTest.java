import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void readTestSuccess() throws Exception {
        StudyPlanGenerator studyPlanGenerator = new StudyPlanGenerator();

        Main.read(studyPlanGenerator, "src/test/test-input-1.txt");

        assertEquals(3, studyPlanGenerator.getM());
        assertEquals(7, studyPlanGenerator.getN());
        assertArrayEquals(new int[] {0,2,4,4}, studyPlanGenerator.getTime());
        assertArrayEquals(new int[] {0,7,8,13}, studyPlanGenerator.getQuestions());
    }

    @Test
    void readThrowsException1() {
        StudyPlanGenerator studyPlanGenerator = new StudyPlanGenerator();

        assertThrows(ParseException.class, () -> Main.read(studyPlanGenerator, "src/test/test-input-2.txt"));
    }

    @Test
    void readThrowsException2() {
        StudyPlanGenerator studyPlanGenerator = new StudyPlanGenerator();

        assertThrows(ParseException.class, () -> Main.read(studyPlanGenerator, "src/test/test-input-3.txt"));
    }

    @Test
    void readThrowsException3() {
        StudyPlanGenerator studyPlanGenerator = new StudyPlanGenerator();

        assertThrows(NumberFormatException.class, () -> Main.read(studyPlanGenerator, "src/test/test-input-4.txt"));
    }

    @Test
    void readThrowsException4() {
        StudyPlanGenerator studyPlanGenerator = new StudyPlanGenerator();

        assertThrows(ParseException.class, () -> Main.read(studyPlanGenerator, "src/test/test-input-5.txt"));
    }
}