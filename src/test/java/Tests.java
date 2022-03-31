import java.time.Duration;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class Tests {

    private String str;

    @BeforeAll
    static void beforeAll(TestInfo info) {
        System.out.println("Getting class before tests: " + info.getTestClass());
    }

    @AfterAll
    static void afterAll(TestInfo info) {
        System.out.println("Getting class after tests: " + info.getTestClass());
    }

    @BeforeEach
    void beforeEach(TestInfo info) {
        System.out.println("Test method started: " + info.getDisplayName());
    }

    @AfterEach
    void afterEach(TestInfo info) {
        System.out.println("Test method ended: " + info.getDisplayName());
    }

    @Test
    void mathMax() {
        int a = 1;
        int b = 2;
        int c = 3;

        assertEquals(3, Math.max(a, c));

    }

    /*
     * This test has inline variable
     */
    @Test
    void toUpperCase_basic() {
        String str = "abcd";
        assertNotNull(str.toUpperCase());
        assertEquals("ABCD", str.toUpperCase());
    }

    @Test
    void contains_basic() {
        String str = "abdefgh";
        boolean result = str.contains("bde");
        assertTrue(result);
    }

    @Test
    void split_basic() {
        String str = "abc def ghi";
        String actualResult[] = str.split(" ");
        String[] expectedResult = {"abc", "def", "ghi"};
        assertArrayEquals(actualResult, expectedResult);
    }

    @Test
    void fixedIntArrayEqual() {
        int[] inte = {1, 2, 3};
        int[] asd = new int[]{1, 2, 3};
        assertArrayEquals(inte, asd);
    }

    @Test
    void length_basic() {
        int actual = "abcd".length();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @DisplayName("This is another display name")
    @Test
    void length_exception() {
        String str = null;
        assertThrows(NullPointerException.class,

                () -> {
                    str.length();
                }
        );

    }

    @RepeatedTest(10)
    void length_greater_than_zero() {
        assertTrue("ABCD".length() > 0);
        assertTrue("ABC".length() > 0);
        assertTrue("A".length() > 0);
        assertTrue("DEF".length() > 0);
    }

    @ParameterizedTest(name = "{0} length is greater than 0")
    @ValueSource(strings = {"ABCD", "ABC", "A", "DEF"})
    void length_greater_than_zero_using_parameterized(String str) {
        assertTrue(str.length() > 0);
    }

    @ParameterizedTest(name = "{0} uppercase is {1}")
    @CsvSource(value = {"abcd, ABCD", "abc, ABC", "'',''", "abcdefg, ABCDEFG"})
    void uppercase(String word, String capitalizedWord) {
        assertEquals(capitalizedWord, word.toUpperCase());
    }

    @ParameterizedTest(name = "{0} length is {1}")
    @CsvSource(value = {"abcd, 4", "abc, 3", "'',0", "abcdefg, 7"})
    void length(String word, int expectedLength) {
        assertEquals(expectedLength, word.length());
    }

    @Disabled
    @Test
    void performanceTest() {
        assertTimeout(Duration.ofSeconds(5), () -> {
                    for (int i = 0; i <= 3000000; i++) {
                        int j = i;
                        System.out.println(j);
                    }
                }
        );
    }


    @Nested
    @DisplayName("For an empty string")
    class EmptyStringTests {

        @BeforeEach
        void setToEmpty() {
            str = "";
        }

        @Test
        @DisplayName("Length should be zero")
        void lengthIsZero() {
            assertEquals(0, str.length());
        }

        @Test
        @DisplayName("Upper case is empty")
        void uppercaseUsEmpty() {
            assertEquals("", str.toUpperCase());

        }
    }

    @Nested
    @DisplayName("For large strings")
    class LargeStringTests {

        @BeforeEach
        void setToLargeString() {
            str = "sergsdtrghdrtyjhftygjhsdytgsgardghsyhsfdghadfhgfds";
        }

        @Test
        @DisplayName("Length should not be zero")
        void lengthIsNotZero() {
            assertNotEquals(0, str.length());
        }

        @Test
        @DisplayName("Upper case is not empty")
        void uppercaseUsEmpty() {
            assertNotEquals("", str.toUpperCase());

        }
    }
}