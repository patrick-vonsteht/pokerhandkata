package io.github.patrick_vonsteht;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HighCardRuleTest {

    private final PokerHand mockedHand1 = mock(PokerHand.class);
    private final PokerHand mockedHand2 = mock(PokerHand.class);

    @Test
    void HighCardRuleReturnsHand1WhenHand1HasHigherHighestValue() {
        assertHighCardRuleResult(
                Stream.of(7, 6, 5, 4, 3),
                Stream.of(6, 5, 4, 3, 2),
                RuleResultType.WINNER_MATCH,
                mockedHand1);
    }

    @Test
    void HighCardRuleReturnsHand2WhenHand2HasHigherHighestValue() {
        assertHighCardRuleResult(
                Stream.of(6, 5, 4, 3, 2),
                Stream.of(7, 6, 5, 4, 3),
                RuleResultType.WINNER_MATCH,
                mockedHand2);
    }

    @Test
    void HighCardRuleReturnsHand1WhenHandsHaveSameHighestAndHand1HasHigherSecondHighestValue() {
        assertHighCardRuleResult(
                Stream.of(7, 6, 5, 4, 3),
                Stream.of(7, 5, 4, 3, 2),
                RuleResultType.WINNER_MATCH,
                mockedHand1);

    }

    @Test
    void HighCardRuleReturnsHand2WhenHandsHaveSameHighestAndHand2HasHigherSecondHighestValue() {
        assertHighCardRuleResult(
                Stream.of(7, 5, 4, 3, 2),
                Stream.of(7, 6, 5, 4, 3),
                RuleResultType.WINNER_MATCH,
                mockedHand2);
    }

    @Test
    void HighCardRuleReturnsHand1WhenHandsHaveSameFourHighestValuesAndHand1HasHigherSmallestValue() {
        assertHighCardRuleResult(
                Stream.of(7, 7, 7, 7, 3),
                Stream.of(7, 7, 7, 7, 2),
                RuleResultType.WINNER_MATCH,
                mockedHand1);
    }

    @Test
    void HighCardRuleReturnsHand2WhenHandsHaveSameFourHighestValuesAndHand2HasHigherSmallestValue() {
        assertHighCardRuleResult(
                Stream.of(7, 7, 7, 7, 2),
                Stream.of(7, 7, 7, 7, 3),
                RuleResultType.WINNER_MATCH,
                mockedHand2);
    }

    @Test
    void HighCardRuleReturnsDrawWhenBothHandsHaveAllTheSameValues() {
        assertHighCardRuleResult(
                Stream.of(7, 5, 4, 3, 2),
                Stream.of(7, 5, 4, 3, 2),
                RuleResultType.DRAW_MATCH,
                null);
    }


    private void assertHighCardRuleResult(final Stream<Integer> scoresForHand1, final Stream<Integer> scoresForHand2,
                                          final RuleResultType expectedResultType, final PokerHand expectedWinner) {
        PokerHandMatcher mockedXOfAKindMatcher = mock(PokerHandMatcher.class);
        when(mockedXOfAKindMatcher.matches(mockedHand1)).thenReturn(true);
        when(mockedXOfAKindMatcher.matches(mockedHand2)).thenReturn(true);

        PokerHandScorer mockedHighCardScorer = mock(PokerHandScorer.class);
        when(mockedHighCardScorer.score(mockedHand1)).thenReturn(scoresForHand1);
        when(mockedHighCardScorer.score(mockedHand2)).thenReturn(scoresForHand2);

        PokerHandComparisonRule rule = new MatcherRuleWithScorer(mockedXOfAKindMatcher, mockedHighCardScorer);
        RuleResult result = rule.compare(mockedHand1, mockedHand2);

        assertEquals(expectedResultType, result.getType());
        assertEquals(expectedWinner, result.getWinner());
    }
}