package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    private final Question q1 = new Question("Java", "Language");
    private final Question q2 = new Question("Java", "Language");
    private final Question q3 = new Question("Python", "Language");

    @Test
    void testGetters() {
        assertEquals("Java", q1.getQuestion());
        assertEquals("Language", q1.getAnswer());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(q1, q2);
        assertEquals(q1.hashCode(), q2.hashCode());
        assertNotEquals(q1, q3);
        assertNotEquals(q1.hashCode(), q3.hashCode());
        assertNotEquals(null, q1);
        assertNotEquals("строка", q1);
    }

    @Test
    void testToString() {
        String result = q1.toString();
        assertTrue(result.contains("Java"));
        assertTrue(result.contains("Language"));
    }

}