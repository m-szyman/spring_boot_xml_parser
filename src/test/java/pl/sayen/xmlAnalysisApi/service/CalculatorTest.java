package pl.sayen.xmlAnalysisApi.service;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Mariusz Szymanski
 */
public class CalculatorTest {

    private List<Integer> scores;

    @Before
    public void setUp() throws Exception {
        scores = Arrays.asList(-5, -3, 0, 3, 5);
    }

    @Test
    public void shouldCalculateCorrectAvgScore() {
//        Before
        int expected = 0;
//        When
        int result = Calculator.calculateAvgScore(scores);
//        Then
        assertEquals(expected, result);
    }

    @Test
    public void shouldCalculateCorrectMinScore() {
//        Before
        int expected = -5;
//        When
        int result = Calculator.calculateMinScore(scores);
//        Then
        assertEquals(expected, result);
    }

    @Test
    public void shouldCalculateCorrectMaxScore() {
//        Before
        int expected = 5;
//        When
        int result = Calculator.calculateMaxScore(scores);
//        Then
        assertEquals(expected, result);
    }
}