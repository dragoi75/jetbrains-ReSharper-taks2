import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChapterTest {

    @Test
    void testEquals() {
        Chapter c1 = new Chapter(4, 8, 8.2);
        Chapter c2 = new Chapter(4, 8, 8.2);
        assertEquals(c1, c2);
        assertEquals(c1,c1);
        assertNotEquals(c1, null);
        assertNotEquals(c1, new Object());
    }

    @Test
    void compareToTest() {
        Chapter c1 = new Chapter(4, 8, 8.2);
        Chapter c2 = new Chapter(4, 5, 8.2);

        assertEquals(-1, c1.compareTo(c2));
    }
}