import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DPStudyPlanGeneratorTest {

    private static DPStudyPlanGenerator DPStudyPlanGenerator;
    @BeforeAll
    static void init() {
        DPStudyPlanGenerator = new DPStudyPlanGenerator();
    }

    @Test
    void setN() {
        DPStudyPlanGenerator.setN(42);
        assertEquals(42, DPStudyPlanGenerator.getN());
    }

    @Test
    void setM() {
        DPStudyPlanGenerator.setM(25);
        assertEquals(25, DPStudyPlanGenerator.getM());
        assertThrows(RuntimeException.class, () -> DPStudyPlanGenerator.setM(-4));
    }

    @Test
    void setTime() {
        int[] expected = new int[]{0,2,1,3};
        DPStudyPlanGenerator.setTime(new String[] {"2", "1", "3"});
        assertArrayEquals(expected, DPStudyPlanGenerator.getTime());
    }

    @Test
    void setQuestions() {
        int[] expected = new int[]{0,2,1,3};
        DPStudyPlanGenerator.setQuestions(new String[] {"2", "1", "3"});
        assertArrayEquals(expected, DPStudyPlanGenerator.getQuestions());
    }

    @Test
    void calculateOptimalPlan() {
        DPStudyPlanGenerator = new DPStudyPlanGenerator(8, 3, new int[]{0,2,4,5}, new int[] {0,7,8,13});

        DPStudyPlanGenerator.calculateOptimalPlan();

        assertEquals(List.of(1,3), DPStudyPlanGenerator.getToBeStudied());
    }

    @Test
    void calculateOptimalPlan2() {
        DPStudyPlanGenerator = new DPStudyPlanGenerator(6, 3, new int[]{0,2,4,5}, new int[] {0,4,8,10});

        DPStudyPlanGenerator.calculateOptimalPlan();

        assertEquals(List.of(1,2), DPStudyPlanGenerator.getToBeStudied());
    }

    @Test
    void calculateOptimalPlan3() {
        DPStudyPlanGenerator = new DPStudyPlanGenerator(6, 3, new int[]{0,2,4,5}, new int[] {0,4,8,11});

        DPStudyPlanGenerator.calculateOptimalPlan();

        assertEquals(List.of(1,2), DPStudyPlanGenerator.getToBeStudied());
    }

    @Test
    void calculateOptimalPlan4() {
        DPStudyPlanGenerator = new DPStudyPlanGenerator(6, 5, new int[]{0,2,2,2,1,5}, new int[] {0,4,8,8,1,18});

        DPStudyPlanGenerator.calculateOptimalPlan();

        assertEquals(List.of(1,2,3), DPStudyPlanGenerator.getToBeStudied());
    }

    @Test
    void calculateOptimalPlan5() {
        DPStudyPlanGenerator = new DPStudyPlanGenerator(6, 5, new int[]{0,2,2,2,1,5}, new int[] {0,4,8,8,2,19});

        DPStudyPlanGenerator.calculateOptimalPlan();

        assertEquals(List.of(4,5), DPStudyPlanGenerator.getToBeStudied());
    }

    @Test
    void calculateOptimalPlanPerfectFit() {
        DPStudyPlanGenerator = new DPStudyPlanGenerator(7, 3, new int[]{0,2,4,5}, new int[] {0,7,8,13});

        DPStudyPlanGenerator.calculateOptimalPlan();

        assertEquals(List.of(1,3), DPStudyPlanGenerator.getToBeStudied());
    }

    @Test
    void calculateOptimalPlanOnlyPartial() {
        DPStudyPlanGenerator = new DPStudyPlanGenerator(3, 3, new int[]{0,4,4,4}, new int[] {0,7,8,13});

        DPStudyPlanGenerator.calculateOptimalPlan();

        assertEquals(List.of(), DPStudyPlanGenerator.getToBeStudied());
    }

    @Test
    void printOptimalPlan() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DPStudyPlanGenerator = new DPStudyPlanGenerator(7, 3, new int[]{0,2,4,4}, new int[] {0,7,8,13});

        DPStudyPlanGenerator.calculateOptimalPlan();

        DPStudyPlanGenerator.printOptimalPlan();

        assertTrue(outContent.toString().contains("Chapter 1"));
        assertTrue(outContent.toString().contains("Chapter 3"));

        System.setOut(System.out);
    }

    @Test
    void printOptimalPlanNull() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DPStudyPlanGenerator = new DPStudyPlanGenerator(7, 3, new int[]{0,2,4,4}, new int[] {0,7,8,13});

        DPStudyPlanGenerator.printOptimalPlan();

        assertTrue(outContent.toString().contains("Plan was not calculated."));

        System.setOut(System.out);
    }
}