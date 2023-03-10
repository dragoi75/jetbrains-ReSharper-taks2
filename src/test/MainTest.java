import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void readTestSuccess() throws Exception {
        DPStudyPlanGenerator DPStudyPlanGenerator = new DPStudyPlanGenerator();

        Main.read(DPStudyPlanGenerator, "src/test/test-input-1.txt");

        assertEquals(3, DPStudyPlanGenerator.getM());
        assertEquals(7, DPStudyPlanGenerator.getN());
        assertArrayEquals(new int[] {0,2,4,4}, DPStudyPlanGenerator.getTime());
        assertArrayEquals(new int[] {0,7,8,13}, DPStudyPlanGenerator.getQuestions());
    }

    @Test
    void readThrowsException1() {
        DPStudyPlanGenerator DPStudyPlanGenerator = new DPStudyPlanGenerator();

        assertThrows(ParseException.class, () -> Main.read(DPStudyPlanGenerator, "src/test/test-input-2.txt"));
    }

    @Test
    void readThrowsException2() {
        DPStudyPlanGenerator DPStudyPlanGenerator = new DPStudyPlanGenerator();

        assertThrows(ParseException.class, () -> Main.read(DPStudyPlanGenerator, "src/test/test-input-3.txt"));
    }

    @Test
    void readThrowsException3() {
        DPStudyPlanGenerator DPStudyPlanGenerator = new DPStudyPlanGenerator();

        assertThrows(NumberFormatException.class, () -> Main.read(DPStudyPlanGenerator, "src/test/test-input-4.txt"));
    }

    @Test
    void readThrowsException4() {
        DPStudyPlanGenerator DPStudyPlanGenerator = new DPStudyPlanGenerator();

        assertThrows(ParseException.class, () -> Main.read(DPStudyPlanGenerator, "src/test/test-input-5.txt"));
    }
}