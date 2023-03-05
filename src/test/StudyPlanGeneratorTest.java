import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudyPlanGeneratorTest {

    private static StudyPlanGenerator studyPlanGenerator;
    @BeforeAll
    static void init() {
        studyPlanGenerator = new StudyPlanGenerator();
    }

    @Test
    void setN() {
        studyPlanGenerator.setN(42);
        assertEquals(42, studyPlanGenerator.getN());
    }

    @Test
    void setM() {
        studyPlanGenerator.setM(25);
        assertEquals(25, studyPlanGenerator.getM());
        assertThrows(RuntimeException.class, () -> studyPlanGenerator.setM(-4));
    }

    @Test
    void setTime() {
        int[] expected = new int[]{0,2,1,3};
        studyPlanGenerator.setTime(new String[] {"2", "1", "3"});
        assertArrayEquals(expected, studyPlanGenerator.getTime());
    }

    @Test
    void setQuestions() {
        int[] expected = new int[]{0,2,1,3};
        studyPlanGenerator.setQuestions(new String[] {"2", "1", "3"});
        assertArrayEquals(expected, studyPlanGenerator.getQuestions());
    }

    @Test
    void calculateOptimalPlan() {
        studyPlanGenerator = new StudyPlanGenerator(8, 3, new int[]{0,2,4,5}, new int[] {0,7,8,13});

        studyPlanGenerator.createChapters();

        studyPlanGenerator.calculateOptimalPlan();

        assertEquals(List.of(1,3), studyPlanGenerator.getToBeStudied());

        assertEquals(2, studyPlanGenerator.getPartial());
    }

    @Test
    void calculateOptimalPlanPerfectFit() {
        studyPlanGenerator = new StudyPlanGenerator(7, 3, new int[]{0,2,4,5}, new int[] {0,7,8,13});

        studyPlanGenerator.createChapters();

        studyPlanGenerator.calculateOptimalPlan();

        assertEquals(List.of(1,3), studyPlanGenerator.getToBeStudied());

        assertEquals(-1, studyPlanGenerator.getPartial());
    }

    @Test
    void calculateOptimalPlanOnlyPartial() {
        studyPlanGenerator = new StudyPlanGenerator(3, 3, new int[]{0,4,4,4}, new int[] {0,7,8,13});

        studyPlanGenerator.createChapters();

        studyPlanGenerator.calculateOptimalPlan();

        assertEquals(List.of(), studyPlanGenerator.getToBeStudied());

        assertEquals(3, studyPlanGenerator.getPartial());
    }

    @Test
    void printOptimalPlan() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        studyPlanGenerator = new StudyPlanGenerator(7, 3, new int[]{0,2,4,4}, new int[] {0,7,8,13});

        studyPlanGenerator.createChapters();

        studyPlanGenerator.calculateOptimalPlan();

        studyPlanGenerator.printOptimalPlan();

        assertTrue(outContent.toString().contains("Chapter 1"));
        assertTrue(outContent.toString().contains("Chapter 3"));
        assertTrue(outContent.toString().contains("Part of chapter 2"));

        System.setOut(System.out);
    }

    @Test
    void printOptimalPlanNull() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        studyPlanGenerator = new StudyPlanGenerator(7, 3, new int[]{0,2,4,4}, new int[] {0,7,8,13});

        studyPlanGenerator.createChapters();

        studyPlanGenerator.printOptimalPlan();

        assertTrue(outContent.toString().contains("Plan was not calculated."));

        System.setOut(System.out);
    }
}