package first_task;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompetitionTest {
    @Test
    void testEmptyList() {
        // arrange
        List<String> emptyList = new ArrayList<>();

        // act & assert
        assertThrows(NoSuchElementException.class, () -> Competition.findWinner(emptyList));
    }

    @Test
    void testSingleElementList() {
        // arrange
        List<String> singleCompetitor = List.of("Ivan 7");

        // act & assert
        assertEquals("Ivan 7", Competition.findWinner(singleCompetitor));
    }

    @Test
    void testMultipleElementsClearWinner() {
        // arrange
        List<String> competitors = List.of("Ivan 10", "Petr 5", "Alex 3");

        // act & assert
        assertEquals("Ivan", Competition.findWinner(competitors));
    }

    @Test
    void testProvidedExample() {
        // arrange
        List<String> example = List.of(
                "Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6",
                "Alex 5", "Ivan 1", "Petr 5", "Alex 1"
        );

        // act & assert
        assertEquals("Petr", Competition.findWinner(example));
    }
}
